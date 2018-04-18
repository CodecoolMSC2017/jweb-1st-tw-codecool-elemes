package com.codecool.elemes.servlet.solution;

import com.codecool.elemes.dao.AssigmentDatabase;
import com.codecool.elemes.dao.impl.AssignmentDao;
import com.codecool.elemes.dao.impl.SolutionDao;
import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.exceptions.SubmissionAlreadyAddedException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.SolutionSubmissionService;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/solution")
public class SolutionServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        Solution solution = null;

        try (Connection connection = getConnection(req.getServletContext())) {
            SolutionDatabase solutionDatabase = new SolutionDao(connection);
            AssigmentDatabase assigmentDatabase = new AssignmentDao(connection);
            SolutionSubmissionService solutionSubmissionService = new SolutionSubmissionService(solutionDatabase, assigmentDatabase);
            solution = solutionSubmissionService.getUsersSolution(user, req.getParameter("id"));
            if (solution.getAnswer() == null) {
                req.setAttribute("question", solution.getAssignment().getQuestion());
                req.getRequestDispatcher("solution.jsp").forward(req, resp);
            }

        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        req.setAttribute("quest", solution.getAssignment().getQuestion());
        req.setAttribute("answer", solution.getAnswer());
        req.setAttribute("grade", solution.getResult());
        req.getRequestDispatcher("solution.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String backMessage = null;
        String answer = req.getParameter("answer");
        String question = req.getParameter("question");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        try (Connection connection = getConnection(req.getServletContext())) {
            SolutionDatabase solutionDatabase = new SolutionDao(connection);
            AssigmentDatabase assigmentDatabase = new AssignmentDao(connection);
            SolutionSubmissionService solutionSubmissionService = new SolutionSubmissionService(solutionDatabase, assigmentDatabase);
            solutionSubmissionService.handleSubmission(question, answer, user);
            backMessage = "Answer recorded.";

        } catch (SubmissionAlreadyAddedException e) {
            backMessage = "Subbmisson can not be edited.";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("backmessage", backMessage);
        req.getRequestDispatcher("solution.jsp").forward(req, resp);
    }

}
