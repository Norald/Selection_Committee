package servlet;

import beans.Faculty;
import db.dao.FacultyDao;
import db.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Showing all faculties.
 * Servlet got pagination, sorting.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "ListFacultiesServlet", urlPatterns = "/app/faculties")
public class ListFacultiesServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ListFacultiesServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort;


        //Logic of working with sorting
        if(request.getSession().getAttribute("sort")==null){
            //default sorting - by alphabet
            sort = "sortAZ";
        } else if(request.getParameterMap().containsKey("sort")){
            sort = request.getParameter("sort");
        } else{
            sort = (String) request.getSession().getAttribute("sort");
        }



        //getting locale
        String locale = (String)request.getSession().getAttribute("language");
        //getting locale for errors
        Locale current = new Locale(locale);
        ResourceBundle rb = ResourceBundle.getBundle("resource", current);

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
        if(sort.equals("sortAZ")){
            faculties = facultyDao.getFacultiesWithLimitOrderAZ(startValue , facultyCountOnPage, locale);
        } else if(sort.equals("sortZA")){
            faculties = facultyDao.getFacultiesWithLimitOrderZA(startValue , facultyCountOnPage, locale);
        } else if(sort.equals("sortBudget")){
            faculties = facultyDao.getFacultiesWithLimitOrderBugdet(startValue , facultyCountOnPage, locale);
        } else{
            faculties = facultyDao.getFacultiesWithLimitOrderTotal(startValue , facultyCountOnPage, locale);
        }
        if(!faculties.isEmpty()) {
            LOG.info("Getting faculties successful");
            request.setAttribute("facultiesList", faculties);
            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("pageFaculty", pageFaculty);
            //update sort attribute in session
            request.getSession().removeAttribute("sort");
            request.getSession().setAttribute("sort", sort);
            request.getRequestDispatcher("/app/faculties.jsp?redirected=redirected")
                    .forward(request, response);
        }else{
            LOG.warn("Can`t get faculties");
            request.setAttribute("error", rb.getString("error.cant.get.faculties"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
