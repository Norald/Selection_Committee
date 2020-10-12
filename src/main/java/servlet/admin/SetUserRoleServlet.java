package servlet.admin;

import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "SetUserRoleServlet", urlPatterns = "/app/admin/user_set_role")
public class SetUserRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("operation")==null||request.getParameter("id")==null){
            request.setAttribute("error", rb.getString("error.operation.or.user.id.null"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else if(request.getParameter("operation").equals("makeUser")||request.getParameter("operation").equals("makeAdmin")){
            String operation = request.getParameter("operation");
            String userId = request.getParameter("id");
            UserDao userDao = new UserDao();
            if(operation.equals("makeAdmin")){
                userDao.makeUserAdmin(Integer.parseInt(userId));
                response.sendRedirect("/app/admin/users");

            }else{
                userDao.makeAdminUser(Integer.parseInt(userId));
                response.sendRedirect("/app/admin/users");
            }
        }else{
            request.setAttribute("error", rb.getString("error.wrong.operation"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/admin/users");
    }
}
