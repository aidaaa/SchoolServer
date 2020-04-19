package model.bl.className;

import model.hibernate.HibernateUtil;
import model.to.className.ClassNameTO;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class ClassNameDAO
{
    public Session session;
    public Transaction transaction;

    public int registerClass(String className)
    {
        try
        {
         session= HibernateUtil.getSession();
         transaction=session.beginTransaction();

            ClassNameTO classNameTO=new ClassNameTO();
            classNameTO.setClass_name(className);
            session.saveOrUpdate(classNameTO);
            transaction.commit();
            session.close();
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public String showClass()
    {
        try
        {
            session= HibernateUtil.getSession();
            Iterator<ClassNameTO>iterator=session.createQuery("from ClassNameTO ").iterate();
            JSONArray jsonArray=new JSONArray();
            while (iterator.hasNext())
            {
                JSONObject jsonObject=new JSONObject();
                ClassNameTO nameTO=iterator.next();
                jsonObject.put("class_name",nameTO.getClass_name());
                jsonArray.add(jsonObject);
            }
            session.close();
            return jsonArray.toJSONString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        new ClassNameDAO().registerClass("1");
        String  name=new ClassNameDAO().showClass();
        System.out.println(name);
    }
}
