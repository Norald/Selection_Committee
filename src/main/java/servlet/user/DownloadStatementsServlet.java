package servlet.user;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import path.PathApp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Show early or final statement to user
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "DownloadStatementsServlet", urlPatterns = "/app/download_statement")
public class DownloadStatementsServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(DownloadStatementsServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale

        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        //if there are no parameter 'name'
        if(request.getParameter("name")==null){
            LOG.warn("Empty parameter");
            request.setAttribute("error", rb.getString("error.empty.parameter"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String filename = request.getParameter("name");

            //find file with this name and return it to user
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(PathApp.STATEMENTS_FOLDER+"/"+filename);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
        }
    }
}
