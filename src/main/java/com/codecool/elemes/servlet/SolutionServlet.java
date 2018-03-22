package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solution")
public class SolutionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Assignment assignment= null;
        try {
            assignment= Database.getInstance().getAssignment(id);
        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        }
        req.setAttribute("question",assignment.getQuestion() );
        req.getRequestDispatcher("solution.jsp").forward(req,resp);
    }
}
