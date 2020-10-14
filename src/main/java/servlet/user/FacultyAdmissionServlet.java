package servlet.user;

import beans.Faculty;
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
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Realisation if user able to apply.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "FacultyAdmissionServlet", urlPatterns = "/app/faculty")
public class FacultyAdmissionServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(FacultyAdmissionServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FacultyDao facultyDao = new FacultyDao();
        UserDao userDao = new UserDao();

        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        String id = request.getParameter("id");
        if(id.isEmpty()||id.equals("")){
            LOG.warn("Wrong id faculty");
            request.setAttribute("error", rb.getString("error.wrong.id.of.faculty"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            Faculty faculty = facultyDao.findFacultyById(id, locale);
            if(faculty==null){
                LOG.warn("No such faculty");
                request.setAttribute("error", rb.getString("error.no.such.faculty"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }else{
                Set<Integer> facultyDemends = facultyDao.getFacultyDemends(id);

                Set<Integer> userSubjects = userDao.getUserSubjects((String)request.getSession().getAttribute("email"));
                faculty = facultyDao.findFacultyById(id,locale);

                request.setAttribute("faculty", faculty);
                //user can pass more than 3 exams, check if user exams is good for faculty demends
                if(userSubjects.containsAll(facultyDemends)){
                    request.setAttribute("able to apply", true);
                    request.getRequestDispatcher("/app/faculty.jsp")
                            .forward(request, response);
                } else{
                    request.setAttribute("able to apply", false);
                    request.getRequestDispatcher("/app/faculty.jsp")
                            .forward(request, response);
                }

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
