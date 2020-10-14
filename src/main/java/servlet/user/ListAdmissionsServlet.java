package servlet.user;

import beans.User;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

/**
 * Realisation of showing users admissions for faculty.
 * @author Vladislav Prokopenko.
 */
@WebServlet(name = "ListAdmissionsServlet", urlPatterns = "/app/admissions")
public class ListAdmissionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String)request.getSession().getAttribute("language");

        //get user
        UserDao userDao = new UserDao();
        User user = userDao.findUser((String)request.getSession().getAttribute("email"));

        //getting all user admissions
        Map<String, Date> mapOfAdmissions = userDao.findUserAdmissions(user, locale);

        HttpSession session = request.getSession();

        session.setAttribute("admissions map", mapOfAdmissions);


        request.getRequestDispatcher("/app/admissions.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
