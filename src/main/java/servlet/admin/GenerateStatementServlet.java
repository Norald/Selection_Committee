package servlet.admin;

import beans.Admission;
import beans.Faculty;
import beans.UserFinalStatementResult;
import comparator.TotalResultComparator;
import db.dao.FacultyDao;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@WebServlet(name = "GenerateStatementServlet", urlPatterns = "/app/admin/generate_statement")
public class GenerateStatementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        if(request.getParameter("id") ==null||request.getParameter("date")==null){
            request.setAttribute("error", rb.getString("error.id.or.date.null"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            String idFaculty = request.getParameter("id");
            String date = request.getParameter("date");



            FacultyDao facultyDao = new FacultyDao();
            Faculty faculty = facultyDao.findFacultyById(idFaculty,locale);
            UserDao userDao = new UserDao();
            List<Admission> admissionList = userDao.getAllUsersAdmissionsForFacultyWithDate(Integer.parseInt(idFaculty), getDateFromString(date));
            ArrayList<UserFinalStatementResult> results = new ArrayList<>();
            for (int i = 0; i < admissionList.size(); i++) {
                results.add(userDao.getFinalStatementResultForFaculty(admissionList.get(i).getUser_id(), faculty.getId(),locale));
            }
            Collections.sort(results, new TotalResultComparator());
            request.getSession().setAttribute("results", results);
            request.getSession().setAttribute("facultyStatement", faculty);
            request.getRequestDispatcher("/app/admin/statement.jsp")
                    .forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public Date getDateFromString(String dateString){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date = new Date(parsed.getTime());
        return date;
    }
}
