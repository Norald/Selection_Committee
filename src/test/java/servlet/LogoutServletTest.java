package servlet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Logout servlet test
 */
public class LogoutServletTest {
    private final static String path = "/start.jsp";
    final LogoutServlet logoutServlet = new LogoutServlet();
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);
    final HttpSession session = mock(HttpSession.class);

    @Test
    public void testLogoutServletPost() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("language")).thenReturn("uk");
        logoutServlet.doPost(request,response);
        verify(session).invalidate();
        verify(response,times(1)).sendRedirect(path);
    }

    @Test
    public void testLogoutServletGet() throws IOException, ServletException {
        logoutServlet.doGet(request,response);
        verify(response,times(1)).sendRedirect(path);
    }
}
