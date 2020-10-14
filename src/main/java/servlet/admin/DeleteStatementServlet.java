package servlet.admin;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import path.PathApp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Delete statement by admin servlet
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "DeleteStatementServlet", urlPatterns = "/app/admin/delete_statement")
public class DeleteStatementServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(DeleteStatementServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale

        String locale = (String) request.getSession().getAttribute("language");
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        if (request.getParameter("name") == null) {
            LOG.warn("Empty parameter");
            request.setAttribute("error", rb.getString("error.empty.parameter"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        } else {
            //get name from request
            String filename = request.getParameter("name");

            //delete file
            File file = new File(PathApp.STATEMENTS_FOLDER + "/" + filename);
            file.delete();
            request.getRequestDispatcher("/app/statements")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/all_statements.jsp");
    }
}
