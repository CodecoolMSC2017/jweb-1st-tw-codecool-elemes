package com.codecool.elemes.servlet;

import com.codecool.elemes.model.User;
import com.codecool.elemes.service.AssignmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addassignment")
public class AddAssignmentServlet extends HttpServlet {

    AssignmentService assignmentService = new AssignmentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedin");

        assignmentService.addAssignment(req);

        req.setAttribute("assignments",assignmentService.getAssigments(user));
        req.getRequestDispatcher(assignmentService.getPage(user)).forward(req, resp);
    }
}

