package servlet.admin;

import beans.Faculty;
import beans.SubjectExam;
import db.dao.FacultyDao;
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
 * Add faculty demend by admin servlet
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "AddFacultyDemendServlet", urlPatterns = "/app/admin/faculty_demend")
public class AddFacultyDemendServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(AddFacultyDemendServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        if(request.getParameter("idFaculty")==null||request.getParameter("demendSelect")==null){
            LOG.warn("Empty faculty id or exam id");
            request.setAttribute("error", rb.getString("error.empty.faculty.or.exam.id"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String examId = request.getParameter("demendSelect");
            String facultyId = request.getParameter("idFaculty");
            FacultyDao facultyDao = new FacultyDao();
            //add exam demend for faculty
            facultyDao.addExamDemendForFaculty(Integer.parseInt(examId), Integer.parseInt(facultyId));
            //if demends changed - all admissions should be deleted
            facultyDao.deleteAllFacultyAdmissions(Integer.parseInt(facultyId));
            Faculty faculty = facultyDao.findFacultyById(facultyId, locale);
            List<SubjectExam> examList = facultyDao.getFacultyDemendsWithName(facultyId, locale);
            List<SubjectExam> examFullList = facultyDao.getAllSubjectExams(locale);
            examFullList.removeAll(examList);
            doGet(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/admin/faculty_demends.jsp");
    }
}
