package servlet.admin;

import beans.Faculty;
import beans.UserFinalStatementResult;
import pdf.CreateStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

@WebServlet(name = "GenerateEarlyStatementServlet", urlPatterns = "/app/admin/generate_early_statement")
public class GenerateEarlyStatementServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale = (String)request.getSession().getAttribute("language");
        Faculty faculty = (Faculty) request.getSession().getAttribute("facultyStatement");
        ArrayList<UserFinalStatementResult> results = (ArrayList<UserFinalStatementResult>) request.getSession().getAttribute("results");

        System.out.println(faculty);
        System.out.println(results);
        String name = faculty.getName();
        name = name.replaceAll(" ","_");
        String fileName = "Generate_Report_"+name+"_"+new java.sql.Date(System.currentTimeMillis())+"_"+System.currentTimeMillis()+".pdf";


        try {
            System.out.println("1111");
            CreateStatement statement = new CreateStatement();
            statement.createPDF("C:/Users/Влад/Desktop/Selectioncommittee/web/app/statements/"+fileName,faculty,results,locale);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        request.getSession().removeAttribute("facultyStatement");
        request.getSession().removeAttribute("results");

        request.getRequestDispatcher("/app/admin/admin_home.jsp")
                .forward(request, response);
    }

}

