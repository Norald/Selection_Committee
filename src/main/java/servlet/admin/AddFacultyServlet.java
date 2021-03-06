package servlet.admin;

import db.dao.FacultyDao;
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
 * Add faculty by admin servlet
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "AddFacultyServlet", urlPatterns = "/app/admin/add_faculty")
public class AddFacultyServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(AddFacultyServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("name")==null||request.getParameter("name_ua")==null||
                request.getParameter("description")==null||request.getParameter("description_ua")==null||
        request.getParameter("budget_amount")==null||request.getParameter("total_amount")==null){
            LOG.warn("Empty parameter");
            request.setAttribute("error", rb.getString("error.empty.parameter"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String name = request.getParameter("name");
            String name_ua = request.getParameter("name_ua");
            String description = request.getParameter("description");
            String description_ua = request.getParameter("description_ua");
            String budget_amount = request.getParameter("budget_amount");
            String total_amount = request.getParameter("total_amount");
            FacultyDao facultyDao = new FacultyDao();
            //add new faculty
            facultyDao.addFaculty(name, Integer.parseInt(budget_amount), Integer.parseInt(total_amount),description,name_ua,description_ua);
            doGet(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG realisation
        response.sendRedirect("/app/admin/all_faculties");
    }
}
