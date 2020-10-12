package servlet.admin;

import beans.Faculty;
import beans.User;
import db.dao.FacultyDao;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "UserListServlet", urlPatterns = "/app/admin/users")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        int pageFaculty;
        int facultyCountOnPage = 10;
        int startValue;
        if(!request.getParameterMap().containsKey("page")){
            pageFaculty = 1;
            startValue = 0;
        }else {
            pageFaculty = Integer.parseInt(request.getParameter("page"));
            startValue = (pageFaculty-1)*facultyCountOnPage;
        }

        UserDao userDao = new UserDao();
        int rows = userDao.getTotalCountOfUsers();
        int nOfPages = rows / facultyCountOnPage;

        if (nOfPages % facultyCountOnPage > 0) {
            nOfPages++;
        }
        if(rows==facultyCountOnPage){
            nOfPages=0;
        }

        List<User> users = null;
        users = userDao.findAllUsersWithLimit(startValue,facultyCountOnPage);

        //System.out.println(Arrays.asList(faculties));
        if(!users.isEmpty()) {
            request.setAttribute("usersList", users);
            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("pageFaculty", pageFaculty);
            request.getRequestDispatcher("/app/admin/all_users.jsp")
                    .forward(request, response);

        }else{
            request.setAttribute("error", rb.getString("error.cant.get.users"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
