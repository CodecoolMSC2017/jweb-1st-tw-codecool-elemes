package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.exceptions.SubmissionAlreadyAddedException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SolutionSubmissionService {

    Database database = Database.getInstance();

    public void handleSubmission(String question, String answer, User user) throws SubmissionAlreadyAddedException {
        for (Solution solution: database.getAllSolutions()) {
            if (solution.getUser().getName().equals(user.getName()) && solution.getAssignment().getQuestion().equals(question)) {
                solution.getAssignment().setAnswear(answer);
                return;
            }
        }
    }

    public Solution getUsersSolution(User user, String assignmentId) throws NoSuchAssignmentException, NoSuchSolutionException {
        Assignment assignment = database.getAssignment(Integer.parseInt(assignmentId));
        for(Solution solution:database.getAllSolutions()) {
            String userName = solution.getUser().getName();
            Assignment solutionAssignment = solution.getAssignment();
            if (userName.equals(user.getName()) && assignment.getQuestion().equals(solutionAssignment.getQuestion())) {
                return solution;
            }
        }
        throw new NoSuchSolutionException();
    }

    public void createUserSolution(User user, Assignment assignment) {
        Solution solution;
        solution = new Solution(new Assignment(assignment.getQuestion(), assignment.getMaxScore()), user);
        database.addSolution(solution);
    }

    public Assignment getAssignmnet(String id) {
        try {
            return database.getAssignment(Integer.parseInt(id));
        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
