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
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Servlet for changing personal info
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "ChangePersonalInfoServlet", urlPatterns = "/app/changeinfo")
public class ChangePersonalInfoServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ChangePersonalInfoServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting parameters
        String email = request.getParameter("email");
        String pass1 = request.getParameter("pass1");
        String idn = request.getParameter("idn");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String school_name = request.getParameter("school_name");
        String average_certificate_point = request.getParameter("average_certificate_point");
        String name_ua = request.getParameter("name_ua");
        String surname_ua = request.getParameter("surname_ua");
        String patronymic_ua = request.getParameter("patronymic_ua");
        String city_ua = request.getParameter("city_ua");
        String region_ua = request.getParameter("region_ua");
        String school_name_ua = request.getParameter("school_name_ua");

        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));


        //check if empty
        if(email.isEmpty()||pass1.isEmpty()||idn.isEmpty()||
                name.isEmpty()||surname.isEmpty()|| patronymic.isEmpty()||city.isEmpty()||
                region.isEmpty()||school_name.isEmpty()|| average_certificate_point.isEmpty()||name_ua.isEmpty()||
                surname_ua.isEmpty()||patronymic_ua.isEmpty()|| city_ua.isEmpty()||region_ua.isEmpty()||
                school_name_ua.isEmpty()){
            LOG.warn("Empty parameters");
            request.setAttribute("error", rb.getString("error.registration.empty.parameters"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            UserDao userDao = new UserDao();
            //find user by email, show us if such email exists
            User user = userDao.findUser(email);

            //get user from session
            User sessionUser = userDao.findUser((String)request.getSession().getAttribute("email"));

            //if email exists
            if(user!=null&&!user.getEmail().equals(sessionUser.getEmail())){
                LOG.warn("Email already exists");
                request.setAttribute("error", rb.getString("error.such.email.exists"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }
            user = userDao.findUserByIdn(idn);

            //if identification number exists
            if(user!=null&&user.getIdn()!=sessionUser.getIdn()){
                LOG.warn("IDN already exists");
                request.setAttribute("error", rb.getString("error.such.idn.exists"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }else {


                //if user changed email, then change email in session
                request.getSession().removeAttribute("email");
                request.getSession().setAttribute("email", email);


                //updating user and user details
                userDao.updateUser(email, Long.parseLong(idn), pass1, sessionUser.getId());
                userDao.updateDetails(name, surname, patronymic, city, region, school_name, Integer.parseInt(average_certificate_point), name_ua, surname_ua, patronymic_ua, city_ua, region_ua, school_name_ua, sessionUser.getId());
                //if user changed his details - all user admission will delete
                userDao.removeUserAdmissions(sessionUser.getId());
                request.getRequestDispatcher("/app/personalInfo")
                        .forward(request, response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/info.jsp");
    }
}
