package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;

import java.util.ArrayList;
import java.util.List;

public class ListSolutionService {
    private Database database = Database.getInstance();

    public List<Solution> getAllSolutions() {
        return database.getAllSolutions();
    }

    public List<Solution> getGradedSolutions(String assignmentId) {
        List<Solution> solutions = new ArrayList<>();
        String question = null;
        try {
            question = database.getAssignment(Integer.parseInt(assignmentId)).getQuestion();
        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        }
        for (Solution solution: getAllSolutions()) {
            if (question.equals(solution.getAssignment().getQuestion())) {
                if (solution.getAssignment().getIsCorrected()) {
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }

    public List<Solution> getSolutionsToGrade(String assignmentId) {
        List<Solution> solutions = new ArrayList<>();
        String question = null;
        try {
            question = database.getAssignment(Integer.parseInt(assignmentId)).getQuestion();
        } catch (NoSuchAssignmentException e) {
            e.printStackTrace();
        }
        for (Solution solution: getAllSolutions()) {
            if (question.equals(solution.getAssignment().getQuestion())) {
                if (!solution.getAssignment().getIsCorrected()) {
                    solutions.add(solution);
                }
            }
        }
        return solutions;
    }


}
