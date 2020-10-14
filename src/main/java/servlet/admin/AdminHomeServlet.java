package servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Redirect to admin home page servlet
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "AdminHomeServlet", urlPatterns = "/app/admin/home")
public class AdminHomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/app/admin/admin_home.jsp")
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/admin/admin_home.jsp");
    }
}
