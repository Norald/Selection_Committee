package servlet;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

/**
 * Realisation of logout.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "LogoutServlet", urlPatterns = "/app/logout")
public class LogoutServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(LogoutServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getting locale
        String locale = (String)request.getSession().getAttribute("language");
        response.setContentType("text/html;charset=UTF-8");

        LOG.info(request.getSession().getAttribute("email")+" success logout");
        HttpSession session=request.getSession();
        //kill session
        session.invalidate();
        response.sendRedirect("/start.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/start.jsp");
    }
}
