package servlet.user;

import beans.Admission;
import beans.SubjectExam;
import beans.User;
import db.dao.FacultyDao;
import db.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import servlet.ChangeLanguageServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

/**
 * Realisation of sending admission to faculty.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "SendAdmissionServlet", urlPatterns = "/app/participate")
public class SendAdmissionServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(SendAdmissionServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String faculty_id = request.getParameter("faculty_id");
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        Locale current = new Locale(locale);
        ResourceBundle rb = ResourceBundle.getBundle("resource", current);

        //checking if values are empty
        if(faculty_id.equals("")||faculty_id.isEmpty()){
            LOG.warn("faculty_id is empty");
            request.setAttribute("error", "faculty_id is empty");
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{

            UserDao userDao = new UserDao();
            User user = userDao.findUser((String)request.getSession().getAttribute("email"));

            List<Admission> listAdmission = userDao.getUserAdmissionForFaculty(user.getId(), Integer.parseInt(faculty_id));
            FacultyDao facultyDao = new FacultyDao();
            Set<Integer> list = facultyDao.getFacultyDemends(faculty_id);


            //If faculty have no demends, user can`t apply
            if(list.size()<3) {
                LOG.warn("Faculty have no demends");
                request.setAttribute("error", rb.getString("error.cant.apply"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);

            }else if(listAdmission.isEmpty()) {

                userDao.addUserAdmissionToFaculty(user.getId(), Integer.parseInt(faculty_id));
                Map<String, Date> mapOfAdmissions = userDao.findUserAdmissions(user, locale);
                //update map of admissions in session
                request.getSession().removeAttribute("admissions map");
                request.getSession().setAttribute("admissions map", mapOfAdmissions);
                response.sendRedirect("/app/admissions");

            }else{//if user already send admission
                LOG.warn(request.getSession().getAttribute("email")+" Already send admission");
                request.setAttribute("error", rb.getString("error.already.send.admission"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/home.jsp");
    }
}
