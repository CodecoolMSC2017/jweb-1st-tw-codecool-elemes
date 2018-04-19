package com.codecool.elemes.service;

import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.model.Solution;

import java.sql.SQLException;
import java.util.List;

public class ListSolutionService {

    private SolutionDatabase database;

    public ListSolutionService(SolutionDatabase database) {
        this.database = database;
    }


    public List<Solution> getAllSolutions() throws SQLException {
        return database.getAllSolutions();
    }

    public List<Solution> getGradedSolutions(String assignmentId) throws SQLException {
       return database.getGradedSolutions(Integer.parseInt(assignmentId));
    }

    public List<Solution> getSolutionsToGrade(String assignmentId) throws SQLException {
        return database.getSolutionsToGrade(Integer.parseInt(assignmentId));
    }

    public Solution getSolution(String id) throws SQLException, NoSuchSolutionException {
        return database.getSolution(Integer.parseInt(id));
    }

    public void updtadeSolution(Solution solution) throws SQLException {
        database.update(solution);
    }

    public void handleGrading(String solutionId, String grade) throws SQLException, NoSuchSolutionException {
        Solution s = getSolution(solutionId);
        s.setResult(Integer.parseInt(grade));
        database.update(s);
    }


}
