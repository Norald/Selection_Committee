package servlet.admin;

import beans.Faculty;
import beans.SubjectExam;
import db.dao.FacultyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "ChangeFacultyAdmissionsServlet", urlPatterns = "/app/admin/faculty_admissions")
public class ChangeFacultyAdmissionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));


        if(request.getParameter("id")==null){
            request.setAttribute("error", rb.getString("error.empty.id"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String idFaculty = request.getParameter("id");
            FacultyDao facultyDao = new FacultyDao();
            List<SubjectExam> examList = facultyDao.getFacultyDemendsWithName(idFaculty, locale);
            List<SubjectExam> examFullList = facultyDao.getAllSubjectExams(locale);
            Faculty faculty = facultyDao.findFacultyById(idFaculty, locale);
            examFullList.removeAll(examList);
            request.setAttribute("faculty", faculty);
            request.setAttribute("examList", examList);
            request.setAttribute("examAvailableList", examFullList);
            request.getRequestDispatcher("/app/admin/faculty_demends.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/admin/faculty_demends.jsp");

    }
}
