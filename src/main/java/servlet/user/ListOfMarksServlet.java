package servlet.user;

import beans.Faculty;
import beans.SubjectExam;
import beans.UserResult;
import db.dao.FacultyDao;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Realisation of showing user marks.
 * @author Vladislav Prokopenko.
 */
@WebServlet(name = "ListOfMarksServlet", urlPatterns = "/app/marks")
public class ListOfMarksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getting locale
        String locale = (String)request.getSession().getAttribute("language");

        UserDao userDao = new UserDao();
        FacultyDao facultyDao = new FacultyDao();
        //getting user exams
        List<UserResult> results = userDao.findUserResult((String) request.getSession().getAttribute("email"), locale);
        //getting all exams
        List<SubjectExam> exams = facultyDao.getAllSubjectExams(locale);


        //we delete the user's choice so that he cannot re-select the same subject for which he has already contributed points
        List<SubjectExam> usersExams = new ArrayList<SubjectExam>();
        for (int i = 0; i < results. size(); i++) {
            usersExams.add(results.get(i).getSubject_exam());
        }
        exams.removeAll(usersExams);

        request.setAttribute("results", results);
        request.setAttribute("exams", exams);
        request.getRequestDispatcher("/app/marks.jsp")
                    .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
