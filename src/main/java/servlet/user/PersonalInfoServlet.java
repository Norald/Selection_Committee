package servlet.user;

import beans.User;
import beans.UserDetails;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "PersonalInfoServlet", urlPatterns = "/app/personalInfo")
public class PersonalInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale

        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        UserDao userDao = new UserDao();
        User user = userDao.findUser((String) request.getSession().getAttribute("email"));

        //getting details for both locales: uk amd en
        UserDetails userDetails1 = userDao.findUserDetails(user, "uk");
        UserDetails userDetails2 = userDao.findUserDetails(user, "en");

        if(userDetails1!=null&&userDetails2!=null){
            request.setAttribute("user", user);
            request.setAttribute("details1", userDetails1);
            request.setAttribute("details2", userDetails2);
            request.getRequestDispatcher("/app/info.jsp")
                    .forward(request, response);
        }else{
            request.setAttribute("error", rb.getString("error.there.are.not.such.user"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
    }
}
