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

@WebServlet(name = "RemoveUserMarkServlet" , urlPatterns = "/app/mark_del")
public class RemoveUserMarkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("subjectid");
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        Locale current = new Locale(locale);
        ResourceBundle rb = ResourceBundle.getBundle("resource", current);

        if(userid.isEmpty()|| userid.equals("")){
            request.setAttribute("error", rb.getString("error.wrong.id.or.subject.exam"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String userEmail = (String)request.getSession().getAttribute("email");
            UserDao userDao = new UserDao();
            User user = userDao.findUser(userEmail);

            userDao.removeUserResults(user.getId(), Integer.parseInt(userid));

            //Если юзер удаляет свою оценку, то все поданыне заявки на вступление удаляются
            userDao.removeUserAdmissions(user.getId());
            request.getSession().removeAttribute("admissions map");
            request.getRequestDispatcher("/app/marks").forward(request, response);

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/marks.jsp");
    }
}
