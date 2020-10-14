package servlet.user;

import beans.User;
import db.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Realisation of deleting admission by user.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "DeleteAdmissionServlet", urlPatterns = "/app/admission_del")
public class DeleteAdmissionServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(DeleteAdmissionServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String faculty_Name = request.getParameter("faculty_name");
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        //check if faculty name is empty
        if(!faculty_Name.isEmpty()){
            UserDao userDao = new UserDao();
            User user = userDao.findUser((String)request.getSession().getAttribute("email"));
            //deleting admission
            userDao.deleteUserAdmission(user.getId(), faculty_Name, locale);

            //update all user admissions, user admissions contains in session
            Map<String, Date> mapOfAdmissions = userDao.findUserAdmissions(user, locale);
            request.getSession().removeAttribute("admissions map");

            request.getSession().setAttribute("admissions map", mapOfAdmissions);
            request.getRequestDispatcher("/app/admissions.jsp")
                    .forward(request, response);
        }else{
            LOG.warn("Wrong faculty");
            request.setAttribute("error", rb.getString("error.wrong.faculty"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/home.jsp");
    }
}
