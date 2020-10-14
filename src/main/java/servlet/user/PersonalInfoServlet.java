package servlet.user;

import beans.User;
import beans.UserDetails;
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

/**
 * Realisation of showing personal user info.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "PersonalInfoServlet", urlPatterns = "/app/personalInfo")
public class PersonalInfoServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(PersonalInfoServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale

        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        UserDao userDao = new UserDao();
        User user = userDao.findUser((String) request.getSession().getAttribute("email"));

        //getting details for both locales: uk and en
        //we show all user details
        UserDetails userDetails1 = userDao.findUserDetails(user, "uk");
        UserDetails userDetails2 = userDao.findUserDetails(user, "en");

        //check if we have such user
        if(userDetails1!=null&&userDetails2!=null){
            request.setAttribute("user", user);
            request.setAttribute("details1", userDetails1);
            request.setAttribute("details2", userDetails2);
            request.getRequestDispatcher("/app/info.jsp")
                    .forward(request, response);
        }else{
            LOG.warn("No such user");
            request.setAttribute("error", rb.getString("error.there.are.not.such.user"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
