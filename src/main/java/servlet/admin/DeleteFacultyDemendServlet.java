package servlet.admin;

import beans.Faculty;
import beans.SubjectExam;
import db.dao.FacultyDao;
import db.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Delete faculty demends servlet by admin
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "DeleteFacultyDemendServlet", urlPatterns = "/app/admin/delete_demend")
public class DeleteFacultyDemendServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(DeleteFacultyDemendServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));


        if(request.getParameter("idFaculty")==null||request.getParameter("idExam")==null){
            LOG.warn("Empty faculty id or exam id");
            request.setAttribute("error", rb.getString("error.empty.faculty.or.exam.id"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String idFaculty = request.getParameter("idFaculty");
            String idExam = request.getParameter("idExam");

            FacultyDao facultyDao = new FacultyDao();
            //getting faculty
            Faculty faculty = facultyDao.findFacultyById(idFaculty, locale);
            //if deleting demend, all admissions will be deleted too
            facultyDao.deleteExamDemendForFaculty(Integer.parseInt(idExam), Integer.parseInt(idFaculty));
            facultyDao.deleteAllFacultyAdmissions(Integer.parseInt(idFaculty));
            //remove values from dropdown list
            List<SubjectExam> examFullList = facultyDao.getAllSubjectExams(locale);
            List<SubjectExam> examList = facultyDao.getFacultyDemendsWithName(idFaculty, locale);
            examFullList.removeAll(examList);
            request.setAttribute("faculty", faculty);
            request.setAttribute("examAvailableList", examFullList);
            request.setAttribute("examList", examList);
            request.getRequestDispatcher("/app/admin/faculty_demends.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/admin/faculty_demends.jsp");
    }
}
