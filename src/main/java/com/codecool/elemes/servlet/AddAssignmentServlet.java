package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.AssigmentDatabase;
import com.codecool.elemes.dao.AssignmentDao;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.AssignmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/addassignment")
public class AddAssignmentServlet extends AbstractServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        String question = req.getParameter("question");
        int maxScore = Integer.parseInt(req.getParameter("score"));

        try
                (Connection connection = getConnection(req.getServletContext())) {
            AssigmentDatabase database = new AssignmentDao(connection);
            AssignmentService assignmentService = new AssignmentService(database);

            Assignment assignment = assignmentService.createAssignment(question, maxScore);
            assignmentService.addAssignment(assignment);

            req.setAttribute("assignments", assignmentService.getAssigments(user));
            req.getRequestDispatcher(assignmentService.getPage(user)).forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

