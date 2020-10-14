package servlet.admin;

import db.dao.FacultyDao;
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

/**
 * Delete faculty by admin servlet
 */
@WebServlet(name = "DeleteFacultyServlet", urlPatterns = "/app/admin/delete_faculty")
public class DeleteFacultyServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(DeleteFacultyServlet.class.getName());

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
            //DELETE ALL ADMISSIONS AND DEMENDS IF FACULTY DELETED!
            facultyDao.deleteFacultyById(Integer.parseInt(id));
            facultyDao.deleteExamDemndsFacultyFacultyById(Integer.parseInt(id));
            facultyDao.deleteAllFacultyAdmissions(Integer.parseInt(id));
            response.sendRedirect("/app/admin/all_faculties");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/admin/all_faculties.jsp");
    }
}
