package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.model.Solution;

import java.sql.SQLException;
import java.util.List;

public interface SolutionDatabase {

    List<Solution> getAllSolutions() throws SQLException;

    Solution getSolution(int id) throws NoSuchSolutionException, SQLException;

    void addSolution(Solution solution) throws SQLException;

    List<Solution> getGradedSolutions(int assignmentId) throws SQLException;

    List<Solution> getSolutionsToGrade(int assignmentId) throws SQLException;

    Solution getUserSolutionsAtAssignmentId(String userEmail, int assignmentId) throws SQLException, NoSuchSolutionException;

    void update(Solution solution) throws SQLException;
}
