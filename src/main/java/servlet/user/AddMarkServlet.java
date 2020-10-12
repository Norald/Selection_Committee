package servlet.user;

import beans.User;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "AddMarkServlet", urlPatterns = "/app/add_mark")
public class AddMarkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("mark")!=null||request.getParameter("marksSelect")!=null) {
            String mark = request.getParameter("mark");
            String examId = request.getParameter("marksSelect");
            String userEmail = (String) request.getSession().getAttribute("email");

            UserDao userDao = new UserDao();
            User user = userDao.findUser(userEmail);

            userDao.addUserMark(user.getId(), Integer.parseInt(examId), Integer.parseInt(mark));

            request.getRequestDispatcher("/app/marks").forward(request, response);
        }else{
            request.setAttribute("error", rb.getString("error.registration.empty.parameters"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/marks.jsp");
    }
}
