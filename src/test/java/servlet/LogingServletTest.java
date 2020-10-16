package servlet;


import beans.User;
import db.DBManager;
import db.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class LogingServletTest {


    @Test
    public void testLoginPost() throws ServletException, IOException, SQLException {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        try {
//            RegistrationServlet loginServlet = new RegistrationServlet();
////            loginServlet.doPost(request,response);
//            loginServlet.service(request,response);
//        } catch (Exception e) {
//            Assert.fail(e.getMessage());
//        }
        LoginServlet loginServlet = new LoginServlet();
        String f= loginServlet.getServletName();
        Assert.assertEquals(f, "LoginServlet");
    }

}
