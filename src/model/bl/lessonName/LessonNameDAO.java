package model.bl.lessonName;

import model.hibernate.HibernateUtil;
import model.to.lessonName.LessonNameTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

public class LessonNameDAO {
    Session session;
    Transaction transaction;

    public int registerLesson(String lesson) {
        try {
            session = HibernateUtil.getSession();
            transaction = session.beginTransaction();

            LessonNameTO lessonNameTO = new LessonNameTO();
            lessonNameTO.setLessons(lesson);
            session.saveOrUpdate(lessonNameTO);
            transaction.commit();
            session.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String showLesson()
    {
        session=HibernateUtil.getSession();
        Iterator<LessonNameTO> iterator=session.createQuery("from LessonNameTO ").iterate();
        JSONArray jsonArray=new JSONArray();
        while (iterator.hasNext())
        {
            JSONObject jsonObject=new JSONObject();
            LessonNameTO lessonNameTO=iterator.next();
            jsonObject.put("lessons",lessonNameTO);
            jsonArray.add(jsonObject);
        }
        session.close();
        return jsonArray.toJSONString();
    }

    public static void main(String[] args) {
        new LessonNameDAO().registerLesson("riazi");
        String  name=new LessonNameDAO().showLesson();
        System.out.println(name);
    }
}
