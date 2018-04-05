package com.codecool.elemes.service;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;

import java.util.ArrayList;
import java.util.List;

public class ListSolutionService {
    private Database database = Database.getInstance();

    public List<Solution> getAllSolutions() {
        return database.getAllSolutions();
    }

    public List<Solution> getGradedSolutions() {
        List<Solution> solutions = new ArrayList<>();
        for (Solution solution: getAllSolutions()) {
            if (solution.getAssignment().getIsCorrected()) {
                solutions.add(solution);
            }
        }
        return solutions;
    }

    public List<Solution> getSolutionsToGrade() {
        List<Solution> solutions = new ArrayList<>();
        for (Solution solution: getAllSolutions()) {
            if (! solution.getAssignment().getIsCorrected()) {
                solutions.add(solution);
            }
        }
        return solutions;
    }
}
