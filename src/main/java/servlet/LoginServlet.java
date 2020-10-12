package servlet;

import beans.Admission;
import beans.User;
import beans.UserRole;
import db.dao.UserDao;

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

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
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

            //проверка заблокирован ли юзер
            if(user.isBlocked()){
                request.setAttribute("error", rb.getString("error.blocked"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }else {


                HttpSession session = request.getSession();

                session.setAttribute("email", user.getEmail());

                session.setAttribute("auth", "authorised");

                session.setAttribute("role", UserRole.getRole(user));


                request.getRequestDispatcher("/app/home.jsp")
                        .forward(request, response);
                //если юзер уже авторизовался и есть сессия
            }
        }else{
            request.setAttribute("error", rb.getString("error.wrong.email.or.password"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/home.jsp");
    }
}
