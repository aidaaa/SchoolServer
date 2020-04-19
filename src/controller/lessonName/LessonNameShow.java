package controller.lessonName;

import model.bl.lessonName.LessonNameDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LessonNameShow extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LessonNameDAO lessonNameDAO=new LessonNameDAO();
        String resault=lessonNameDAO.showLesson();
        PrintWriter writer=resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        writer.write(resault);
        writer.flush();
        writer.close();
    }
}
