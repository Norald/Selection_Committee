package servlet.user;

import path.PathApp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Realisation of showing all statements for users.
 * Statements contains in 'out/statements/'
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "ShowAllStatementsServlet", urlPatterns = "/app/statements")
public class ShowAllStatementsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = PathApp.STATEMENTS_FOLDER;
        File folder = new File(path);
        //getting list of files and showing them to users
        File[] listOfFiles  = folder.listFiles();

        request.setAttribute("files", listOfFiles);
        request.getRequestDispatcher("/app/all_statements.jsp")
                .forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
