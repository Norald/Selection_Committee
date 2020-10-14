package servlet.admin;

import beans.Faculty;
import db.dao.FacultyDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Show all faculties to admin servlet.
 * @author Vladislav Prokopenko.
 */
@WebServlet(name = "ShowAllFacultiesServlet", urlPatterns = "/app/admin/all_faculties")
public class ShowAllFacultiesServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ShowAllFacultiesServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        //realisation of pagination
        int pageFaculty;
        int facultyCountOnPage = 5;
        int startValue;
        if(!request.getParameterMap().containsKey("page")){
            pageFaculty = 1;
            startValue = 0;
        }else {
            pageFaculty = Integer.parseInt(request.getParameter("page"));
            startValue = (pageFaculty-1)*facultyCountOnPage;
        }

        FacultyDao facultyDao = new FacultyDao();
        int rows = facultyDao.getTotalCountOfFaculty();
        int nOfPages = rows / facultyCountOnPage;

        if (nOfPages % facultyCountOnPage > 0) {
            nOfPages++;
        }
        if(rows%facultyCountOnPage==0){
            nOfPages--;
        }

        List<Faculty> faculties = null;
        faculties = facultyDao.getFacultiesWithLimitOrderAZ(startValue , facultyCountOnPage, locale);

        if(!faculties.isEmpty()) {
            //attributes for pagination
            request.setAttribute("facultiesList", faculties);
            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("pageFaculty", pageFaculty);
            request.getRequestDispatcher("/app/admin/all_faculties.jsp")
                    .forward(request, response);
        }else{
            LOG.warn("Cant get faculties");
            request.setAttribute("error", rb.getString("error.cant.get.faculties"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
