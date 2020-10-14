package servlet;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Realisation of changing language.
 * Getting value from request and changing session attribute value.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "ChangeLanguageServlet" , urlPatterns = "/changeLang")
public class ChangeLanguageServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ChangeLanguageServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("lang");

        if(!language.isEmpty()){
            LOG.info("Changing language");
            request.getSession().removeAttribute("language");
            request.getSession().setAttribute("language", language);
            response.sendRedirect(request.getHeader("referer"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
