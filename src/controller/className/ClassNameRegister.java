package controller.className;

import model.bl.className.ClassNameDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClassNameRegister extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String className=req.getParameter("ClassName");
        ClassNameDAO classNameDAO=new ClassNameDAO();
        int resault=classNameDAO.registerClass(className);

        PrintWriter writer=resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (resault==1)
        {
            writer.write("SET");
        }
        else
        {
            writer.write("NOT SET");
        }
        writer.flush();
        writer.close();
    }
}
