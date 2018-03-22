package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String answer = req.getParameter("answer");
        System.out.println(answer);
        String question = req.getParameter("question");
        System.out.println(question);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        Database database = Database.getInstance();
        Assignment ass = null;
        for (Assignment assignment: database.getAllAssignments()) {
            if(assignment.getQuestion().equals(question)) {
                ass = assignment;
            }
        }
        ass.setAnswear(answer);
        Solution solution = new Solution(ass, user);
        database.addSolution(solution);

        req.setAttribute("backmessage", "Answer recorded, thanks bazdmeg!");
        req.getRequestDispatcher("solution.jsp").forward(req, resp);

    }
}
