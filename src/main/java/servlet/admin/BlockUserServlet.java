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

@WebServlet(name = "BlockUserServlet", urlPatterns = "/app/admin/block_user")
public class BlockUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("operation")==null||request.getParameter("id")==null){
            request.setAttribute("error", rb.getString("error.operation.or.user.id.null"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else if(request.getParameter("operation").equals("block")||request.getParameter("operation").equals("unblock")){
            String operation = request.getParameter("operation");
            String userId = request.getParameter("id");
            UserDao userDao = new UserDao();
            if(operation.equals("block")){
                userDao.blockUserById(Integer.parseInt(userId));
                response.sendRedirect("/app/admin/users");

            }else{
                userDao.unblockUserById(Integer.parseInt(userId));
                response.sendRedirect("/app/admin/users");
            }
        }else{
            request.setAttribute("error", "Wrong operation");
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/admin/users");
    }
}
