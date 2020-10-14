package servlet.admin;

import beans.Faculty;
import beans.SubjectExam;
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
 * Show all exams to admin servlet.
 * @author Vladislav Prokopenko.
 */
@WebServlet(name = "ListOfSubjectServlet", urlPatterns = "/app/admin/subject_exams")
public class ListOfSubjectServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(ListOfSubjectServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        //realisation of pagination
        int pageSubjectExam;
        int facultyCountOnPage = 5;
        int startValue;
        if(!request.getParameterMap().containsKey("page")){
            pageSubjectExam = 1;
            startValue = 0;
        }else {
            pageSubjectExam = Integer.parseInt(request.getParameter("page"));
            startValue = (pageSubjectExam-1)*facultyCountOnPage;
        }

        FacultyDao facultyDao = new FacultyDao();
        int rows = facultyDao.getTotalCountOfSubjectExams();
        int nOfPages = rows / facultyCountOnPage;

        if (nOfPages % facultyCountOnPage > 0) {
            nOfPages++;
        }
        if(rows%facultyCountOnPage==0){
            nOfPages--;
        }

        List<SubjectExam> subjectExams = null;
        subjectExams = facultyDao.getSubjectExamsWithLimit(startValue , facultyCountOnPage, locale);

        if(!subjectExams.isEmpty()) {
            request.setAttribute("subjectExams", subjectExams);
            request.setAttribute("noOfPages", nOfPages);
            request.setAttribute("pageSubjectExam", pageSubjectExam);
            request.getRequestDispatcher("/app/admin/subject_exams.jsp")
                    .forward(request, response);
        }else{
            LOG.warn("Cant get subject exam");
            request.setAttribute("error", rb.getString("error.cant.get.subject.exam"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
