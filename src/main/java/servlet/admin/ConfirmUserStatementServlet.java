package servlet.admin;

import beans.Admission;
import beans.Faculty;
import beans.UserFinalStatementResult;
import comparator.TotalResultComparator;
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
 * Confirm user admission servlet.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "ConfirmUserStatementServlet", urlPatterns = "/app/admin/confirm_user_for_statement")
public class ConfirmUserStatementServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ConfirmUserStatementServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));
        if(request.getParameter("idAdmission") == null||request.getParameter("operation")==null){
            LOG.warn("Empty value");
            request.setAttribute("error", rb.getString("error.empty.parameter"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String idAdmission = request.getParameter("idAdmission");
            String operation= request.getParameter("operation");
            FacultyDao facultyDao = new FacultyDao();
            if(operation.equals("approve")){
                facultyDao.approveAdmission(Integer.parseInt(idAdmission));
            }else{
                facultyDao.disapproveAdmission(Integer.parseInt(idAdmission));
            }

            String date = (String) request.getSession().getAttribute("date");

            Faculty faculty = (Faculty) request.getSession().getAttribute("facultyStatement");
            //find faculty
            UserDao userDao = new UserDao();
            //find all admissions for faculty
            List<Admission> admissionList = userDao.getAllUsersAdmissionsForFacultyWithDate(faculty.getId(), GenerateStatementServlet.getDateFromString(date));
            ArrayList<UserFinalStatementResult> results = new ArrayList<>();
            //getting final results for users
            for (int i = 0; i < admissionList.size(); i++) {
                results.add(userDao.getFinalStatementResultForFaculty(admissionList.get(i).getUser_id(), faculty.getId(),locale));
            }
            //sort list by total result
            Collections.sort(results, new TotalResultComparator());
            //set values to session
            request.getSession().setAttribute("date", date);
            request.getSession().setAttribute("results", results);
            request.getSession().setAttribute("facultyStatement", faculty);
            doGet(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/admin/statement.jsp");
    }
}
