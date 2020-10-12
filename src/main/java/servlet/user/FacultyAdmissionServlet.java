package servlet.user;

import beans.Faculty;
import db.dao.FacultyDao;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

@WebServlet(name = "FacultyAdmissionServlet", urlPatterns = "/app/faculty")
public class FacultyAdmissionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FacultyDao facultyDao = new FacultyDao();
        UserDao userDao = new UserDao();

        //getting locale
        String locale = (String) request.getSession().getAttribute("language");
        //getting locale for errors
        ResourceBundle rb = ResourceBundle.getBundle("resource", new Locale(locale));

        String id = request.getParameter("id");
        if(id.isEmpty()||id.equals("")){
            request.setAttribute("error", rb.getString("error.wrong.id.of.faculty"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            Faculty faculty = facultyDao.findFacultyById(id, locale);
            if(faculty==null){
                request.setAttribute("error", rb.getString("error.no.such.faculty"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }else{
                //todo ЗАКОНЧИТЬ ПРОВЕРКУ ВОЗМОЖНОСТИ ПОДАЧИ ЗАЯВКИ
                Set<Integer> facultyDemends = facultyDao.getFacultyDemends(id);

                Set<Integer> userSubjects = userDao.getUserSubjects((String)request.getSession().getAttribute("email"));
                faculty = facultyDao.findFacultyById(id,locale);

                request.setAttribute("faculty", faculty);
                //пользователь может сдать больше екзаменов чем требований(обычно 3)
                if(userSubjects.containsAll(facultyDemends)){
                    request.setAttribute("able to apply", true);
                    request.getRequestDispatcher("/app/faculty.jsp")
                            .forward(request, response);
                } else{
                    request.setAttribute("able to apply", false);
                    request.getRequestDispatcher("/app/faculty.jsp")
                            .forward(request, response);
                }

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/app/faculty.jsp");
    }
}
