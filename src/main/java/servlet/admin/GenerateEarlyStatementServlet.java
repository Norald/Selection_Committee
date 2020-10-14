package servlet.admin;

import beans.Faculty;
import beans.UserFinalStatementResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pdf.CreateStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

/**
 * Generate early PDF statement servlet.
 * @author Vladislav Prokopenko
 */
@WebServlet(name = "GenerateEarlyStatementServlet", urlPatterns = "/app/admin/generate_early_statement")
public class GenerateEarlyStatementServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(GenerateEarlyStatementServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String)request.getSession().getAttribute("language");
        //getting values from session
        Faculty faculty = (Faculty) request.getSession().getAttribute("facultyStatement");
        ArrayList<UserFinalStatementResult> results = (ArrayList<UserFinalStatementResult>) request.getSession().getAttribute("results");

        String name = faculty.getName();
        //change name, delete spaces
        name = name.replaceAll(" ","_");
        //creating name of file
        String fileName = "Generate_Early_Report_"+name+"_"+new java.sql.Date(System.currentTimeMillis())+"_"+System.currentTimeMillis()+".pdf";


        //generating pdf statement
        try {
            CreateStatement statement = new CreateStatement();
            statement.createPDF("C:/Users/Влад/Desktop/Selectioncommittee/out/statements/"+fileName,faculty,results,locale);
        } catch (Exception e1) {
            LOG.error(e1.getMessage(),e1);
        }

        request.getRequestDispatcher("/app/admin/admin_home.jsp")
                .forward(request, response);
    }

}

