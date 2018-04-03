package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.SubmissionAlreadyAddedException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SolutionSubmissionService {

    public void handleSubmission(HttpServletRequest req, HttpServletResponse resp) throws SubmissionAlreadyAddedException {
        String answer = req.getParameter("answer");
        String question = req.getParameter("question");
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
        for (Solution solution1: database.getAllSolutions()) {
            if (solution.equals(solution1)) {
                throw new SubmissionAlreadyAddedException();
            }
        }
        database.addSolution(solution);
    }

}
