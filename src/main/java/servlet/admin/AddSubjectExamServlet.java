package servlet.admin;

import db.dao.FacultyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "AddSubjectExamServlet", urlPatterns = "/app/admin/add_subject_exam")
public class AddSubjectExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("name")==null||request.getParameter("name_ua")==null||
                request.getParameter("description")==null||request.getParameter("description_ua")==null){
            request.setAttribute("error", rb.getString("error.empty.parameter"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String name = request.getParameter("name");
            String name_ua = request.getParameter("name_ua");
            String description = request.getParameter("description");
            String description_ua = request.getParameter("description_ua");
            FacultyDao facultyDao = new FacultyDao();
            facultyDao.addSubjectExam(name, name_ua, description, description_ua);
            response.sendRedirect("/app/admin/subject_exams");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/admin/subject_exams.jsp");

    }
}
