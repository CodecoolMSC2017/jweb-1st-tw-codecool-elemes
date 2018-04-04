package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.exceptions.NotGradedYetException;
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
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        Solution solution = null;
        try {
            solution = solutionSubmissionService.getUsersSolution(user, req.getParameter("id"));
        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        } catch (NoSuchSolutionException e) {
            Assignment assignment = solutionSubmissionService.getAssignmnet(req.getParameter("id"));
            solutionSubmissionService.createUserSolution(user, assignment);
            req.setAttribute("question", assignment.getQuestion());
            req.getRequestDispatcher("solution.jsp").forward(req,resp);
        }
        req.setAttribute("quest", solution.getAssignment().getQuestion());
        req.setAttribute("answer", solution.getAssignment().getAnswear());
        try {
            req.setAttribute("grade", solution.getAssignment().getGrade());
        } catch (NotGradedYetException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("solution.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String backMessage;
        String answer = req.getParameter("answer");
        String question = req.getParameter("question");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        try {
            solutionSubmissionService.handleSubmission(question, answer, user);
            backMessage = "Answer recorded.";

        } catch (SubmissionAlreadyAddedException e) {
            backMessage = "Subbmisson can not be edited.";
        }

        req.setAttribute("backmessage", backMessage);
        req.getRequestDispatcher("solution.jsp").forward(req, resp);
    }

}
