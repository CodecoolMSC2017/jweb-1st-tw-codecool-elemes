package com.codecool.elemes.service;

import com.codecool.elemes.dao.AssigmentDatabase;
import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.exceptions.SubmissionAlreadyAddedException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SolutionSubmissionService {

    SolutionDatabase solutionDatabase;
    AssigmentDatabase assigmentDatabase;

    public SolutionSubmissionService(SolutionDatabase solutionDatabase, AssigmentDatabase assigmentDatabase) {
        this.solutionDatabase = solutionDatabase;
        this.assigmentDatabase = assigmentDatabase;
    }


    public void handleSubmission(String question, String answer, User user) throws SubmissionAlreadyAddedException, SQLException {
        for (Solution solution: solutionDatabase.getAllSolutions()) {
            if (solution.getUser().getName().equals(user.getName()) && solution.getAssignment().getQuestion().equals(question)) {
                solution.getAssignment().setAnswear(answer);
                assigmentDatabase.update(solution.getAssignment());
                return;
            }
        }
    }

    public Solution getUsersSolution(User user, String assignmentId) throws NoSuchAssignmentException, NoSuchSolutionException, SQLException {
        Assignment assignment = assigmentDatabase.getAssignment(Integer.parseInt(assignmentId));
        for(Solution solution:solutionDatabase.getAllSolutions()) {
            String userName = solution.getUser().getName();
            Assignment solutionAssignment = solution.getAssignment();
            if (userName.equals(user.getName()) && assignment.getQuestion().equals(solutionAssignment.getQuestion())) {
                return solution;
            }
        }
        Assignment assignment1 = assigmentDatabase.getAssignment(Integer.parseInt(assignmentId));
        createUserSolution(user, assignment1);
        return getUsersSolution(user, assignmentId);
    }

    public void createUserSolution(User user, Assignment assignment) throws SQLException {
        Solution solution;
        solution = new Solution(assignment, user);
        assigmentDatabase.addAssignment(solution.getAssignment());
        solutionDatabase.addSolution(solution);
    }

    public Assignment getAssignmnet(String id) throws SQLException {
        try {
            return assigmentDatabase.getAssignment(Integer.parseInt(id));
        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        }
        return null;
    }

}
