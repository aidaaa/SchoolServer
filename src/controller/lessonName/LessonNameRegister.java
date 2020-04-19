package controller.lessonName;

import model.bl.lessonName.LessonNameDAO;
import model.to.lessonName.LessonNameTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LessonNameRegister extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lesson=req.getParameter("lesson");

        LessonNameDAO lessonNameDAO=new LessonNameDAO();
        int resault=lessonNameDAO.registerLesson(lesson);

        PrintWriter writer=resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        if (resault==1){
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
