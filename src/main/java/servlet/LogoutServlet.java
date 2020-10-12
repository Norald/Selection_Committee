package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "LogoutServlet", urlPatterns = "/app/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getting locale
        String locale = (String)request.getSession().getAttribute("language");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session=request.getSession();
        session.invalidate();
        response.sendRedirect("/start.jsp");

        //request.getRequestDispatcher("/start.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/start.jsp");
    }
}
