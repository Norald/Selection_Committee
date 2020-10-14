package servlet;

import beans.Admission;
import beans.User;
import beans.UserRole;
import db.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Realisation of login.
 * @author Vladislav Prokopenko.
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(LoginServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");



        //getting locale
        String locale = (String)request.getSession().getAttribute("language");
        //getting locale for errors
        Locale current = new Locale(locale);
        ResourceBundle rb = ResourceBundle.getBundle("resource", current);

        //get user and check if exists
        UserDao userDao = new UserDao();
        User user = userDao.findUser(login, password);


        if(user!=null){

            //check if user blocked
            if(user.isBlocked()){
                LOG.warn(login+" is blocked. Can`t login");
                request.setAttribute("error", rb.getString("error.blocked"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }else {

                LOG.info("Success login");

                HttpSession session = request.getSession();

                //set to session attributes: email, auth and role

                session.setAttribute("email", user.getEmail());

                session.setAttribute("auth", "authorised");

                session.setAttribute("role", UserRole.getRole(user));


                request.getRequestDispatcher("/app/home.jsp")
                        .forward(request, response);
            }
        }else{//if wrong email or password
            LOG.warn(login+" wrong email or password");
            request.setAttribute("error", rb.getString("error.wrong.email.or.password"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/home.jsp");
    }
}
