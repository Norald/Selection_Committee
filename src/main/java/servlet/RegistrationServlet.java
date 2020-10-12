package servlet;

import beans.User;
import db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo СЕРВЛЕТИК ДЛЯ РЕГИСТРАЦИИ!
        String email = request.getParameter("email");
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        String idn = request.getParameter("idn");
        String english_name = request.getParameter("name");
        String english_surname = request.getParameter("surname");
        String english_patronymic = request.getParameter("patronymic");
        String english_city = request.getParameter("city");
        String english_region = request.getParameter("region");
        String english_school_name = request.getParameter("school_name");
        String average_certificate_point = request.getParameter("average_certificate_point");
        String ukrainian_name = request.getParameter("name_ua");
        String ukrainian_surname = request.getParameter("surname_ua");
        String ukrainian_patronymic = request.getParameter("patronymic_ua");
        String ukrainian_city = request.getParameter("city_ua");
        String ukrainian_region = request.getParameter("region_ua");
        String ukrainian_school_name = request.getParameter("school_name_ua");

        String locale = (String)request.getSession().getAttribute("language");
        Locale current = new Locale(locale);
        ResourceBundle rb = ResourceBundle.getBundle("resource", current);


        //check if we got empty parameters
        if(email.isEmpty()||pass1.isEmpty()||pass2.isEmpty()||idn.isEmpty()||
                english_name.isEmpty()||english_surname.isEmpty()|| english_patronymic.isEmpty()||english_city.isEmpty()||
                english_region.isEmpty()||english_school_name.isEmpty()|| average_certificate_point.isEmpty()||ukrainian_name.isEmpty()||
                ukrainian_surname.isEmpty()||ukrainian_patronymic.isEmpty()|| ukrainian_city.isEmpty()||ukrainian_region.isEmpty()||
                ukrainian_school_name.isEmpty()){

            request.setAttribute("error", rb.getString("error.registration.empty.parameters"));
            request.getRequestDispatcher("/error.jsp")
                    .forward(request, response);
        }else{
            UserDao userDao = new UserDao();
            User user = userDao.findUser(email);
            if(user!=null){
                request.setAttribute("error", rb.getString("error.such.email.exists"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }
            user = userDao.findUserByIdn(idn);

            if(user!=null){
                request.setAttribute("error", rb.getString("error.such.idn.exists"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            }else if (!pass1.equals(pass2)){
                request.setAttribute("error", rb.getString("error.passwords.should.match"));
                request.getRequestDispatcher("/error.jsp")
                        .forward(request, response);
            } else {
                userDao.addUser(email, Long.parseLong(idn), pass1);
                user = userDao.findUser(email);
                userDao.addUserDetails(user.getId(), english_name, english_surname, english_patronymic, english_city, english_region, english_school_name, Integer.parseInt(average_certificate_point), ukrainian_name, ukrainian_surname, ukrainian_patronymic, ukrainian_city, ukrainian_region, ukrainian_school_name);
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();

                out.print(rb.getString("registration.successfull"));
                request.getRequestDispatcher("/start.jsp")
                        .include(request, response);

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/registration.jsp");

    }
}
