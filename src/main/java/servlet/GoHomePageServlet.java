package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Realisation of redirecting on home page.
 */
@WebServlet(name = "GoHomePageServlet", urlPatterns = "/app/home")
public class GoHomePageServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String locale = (String)request.getSession().getAttribute("language");
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("/app/home.jsp")
                .forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
