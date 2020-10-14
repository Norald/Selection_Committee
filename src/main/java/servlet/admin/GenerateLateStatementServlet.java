package servlet.admin;

import beans.Admission;
import beans.Faculty;
import beans.UserFinalStatementResult;
import comparator.TotalResultComparator;
import db.dao.UserDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pdf.CreateStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Generate late PDF statement servlet.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "GenerateLateStatementServlet", urlPatterns = "/app/admin/generate_late_statement")
public class GenerateLateStatementServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(GenerateLateStatementServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String)request.getSession().getAttribute("language");
        Faculty faculty = (Faculty) request.getSession().getAttribute("facultyStatement");

        String name = faculty.getName();
        name = name.replaceAll(" ","_");
        String fileName = "Generate_Late_Report_"+name+"_"+new java.sql.Date(System.currentTimeMillis())+"_"+System.currentTimeMillis()+".pdf";


        String date = (String) request.getSession().getAttribute("date");

        UserDao userDao = new UserDao();
        //find all applied admissions for faculty
        List<Admission> admissionList = userDao.getAllAppliedUsersAdmissionsForFacultyWithDate(faculty.getId(), GenerateStatementServlet.getDateFromString(date));
        ArrayList<UserFinalStatementResult> results = new ArrayList<>();
        //getting final results for applied user statements
        for (int i = 0; i < admissionList.size(); i++) {
            results.add(userDao.getLateFinalStatementResultForFaculty(admissionList.get(i).getUser_id(), faculty.getId(),locale));
        }
        //sort list by total result
        Collections.sort(results, new TotalResultComparator());


        try {
            CreateStatement statement = new CreateStatement();
            statement.createPDF("C:/Users/Влад/Desktop/Selectioncommittee/out/statements/"+fileName,faculty,results,locale);
        } catch (Exception e1) {
            LOG.warn(e1.getMessage(),e1);
        }
        request.getRequestDispatcher("/app/admin/admin_home.jsp")
                .forward(request, response);
    }
}
