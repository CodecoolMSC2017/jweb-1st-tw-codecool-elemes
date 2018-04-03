package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.SubmissionAlreadyAddedException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.SolutionSubmissionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/solution")
public class SolutionServlet extends HttpServlet {
    private SolutionSubmissionService solutionSubmissionService = new SolutionSubmissionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Assignment assignment= null;
        try {
            assignment= Database.getInstance().getAssignment(id);
        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        }
        req.setAttribute("question",assignment.getQuestion());
        req.getRequestDispatcher("solution.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String backMessage;
        try {
            solutionSubmissionService.handleSubmission(req, resp);
            backMessage = "Answer recorded.";

        } catch (SubmissionAlreadyAddedException e) {
            backMessage = "Subbmisson can not be edited.";
        }

        req.setAttribute("backmessage", backMessage);
        req.getRequestDispatcher("solution.jsp").forward(req, resp);
    }
}
