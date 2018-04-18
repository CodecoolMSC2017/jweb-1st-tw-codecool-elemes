package com.codecool.elemes.service;

import com.codecool.elemes.dao.AssigmentDatabase;
import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.exceptions.SubmissionAlreadyAddedException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;

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
                solution.setAnswer(answer);
                solutionDatabase.update(solution);
                return;
            }
        }
    }

    public Solution getUsersSolution(User user, String assignmentId) throws SQLException, NoSuchAssignmentException {
        try {
            return solutionDatabase.getUserSolutionsAtAssignmentId(user.geteMail(), Integer.parseInt(assignmentId));
        }
        catch (NoSuchSolutionException e) {
            createUserSolution(user, assigmentDatabase.getAssignment(Integer.parseInt(assignmentId)));
        }
        return getUsersSolution(user, assignmentId);
    }

    public void createUserSolution(User user, Assignment assignment) throws SQLException {
        Solution solution;
        solution = new Solution(assignment, user);
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
