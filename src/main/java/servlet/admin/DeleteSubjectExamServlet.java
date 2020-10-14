package servlet.admin;

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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Delete exam by admin servlet.
 * @author Vladislav Prokopenko.
 */
@WebServlet(name = "DeleteSubjectExamServlet", urlPatterns = "/app/admin/delete_subject_exam")
public class DeleteSubjectExamServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(DeleteSubjectExamServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("id")==null){
            LOG.warn("Empty id");
            request.setAttribute("error", rb.getString("error.empty.id"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String id = request.getParameter("id");
            FacultyDao facultyDao = new FacultyDao();
            //DELETE admissions if subject exam deleted
            facultyDao.deleteSubjectExamById(Integer.parseInt(id));
            UserDao userDao = new UserDao();
            userDao.deleteAllResultsBySubjectExamId(Integer.parseInt(id));
            Set<Integer> facultiesId = facultyDao.getFacultyDemends(id);
            for (Integer i: facultiesId) {
                facultyDao.deleteAllFacultyAdmissions(i);
            }
            response.sendRedirect("/app/admin/subject_exams");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/admin/subject_exams.jsp");
    }
}
