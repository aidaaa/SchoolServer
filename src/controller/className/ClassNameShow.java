package controller.className;

import model.bl.className.ClassNameDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ClassNameShow extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClassNameDAO classNameDAO=new ClassNameDAO();
        String resualt=classNameDAO.showClass();
        PrintWriter writer=resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.write(resualt);
        writer.flush();
        writer.close();
    }
}
