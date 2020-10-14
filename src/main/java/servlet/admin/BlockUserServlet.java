package servlet.admin;

import db.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Block user by admin servlet
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "BlockUserServlet", urlPatterns = "/app/admin/block_user")
public class BlockUserServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(BlockUserServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("operation")==null||request.getParameter("id")==null){
            LOG.warn("Error operation or user id null");
            request.setAttribute("error", rb.getString("error.operation.or.user.id.null"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else if(request.getParameter("operation").equals("block")||request.getParameter("operation").equals("unblock")){
            String operation = request.getParameter("operation");
            String userId = request.getParameter("id");
            UserDao userDao = new UserDao();
            //if operation 'block' - then block user
            if(operation.equals("block")){
                userDao.blockUserById(Integer.parseInt(userId));
                doGet(request,response);

            }else{//else - unblock user
                userDao.unblockUserById(Integer.parseInt(userId));
                doGet(request,response);
            }
        }else{
            LOG.warn("Wrong operation");
            request.setAttribute("error", "Wrong operation");
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/admin/users");
    }
}
