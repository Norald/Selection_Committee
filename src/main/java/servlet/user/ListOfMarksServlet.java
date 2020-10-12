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

@WebServlet(name = "ListOfMarksServlet", urlPatterns = "/app/marks")
public class ListOfMarksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //getting locale
        String locale = (String)request.getSession().getAttribute("language");

        UserDao userDao = new UserDao();
        FacultyDao facultyDao = new FacultyDao();
        List<UserResult> results = userDao.findUserResult((String) request.getSession().getAttribute("email"), locale);
        List<SubjectExam> exams = facultyDao.getAllSubjectExams(locale);

        //удаляем выбор у пользователя, чтобы он не мог повторно выбрать такой же предмет, по которому уже вносил баллы
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
