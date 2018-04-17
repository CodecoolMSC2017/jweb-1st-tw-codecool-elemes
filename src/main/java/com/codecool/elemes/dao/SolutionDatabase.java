package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.model.Solution;

import java.sql.SQLException;
import java.util.List;

public interface SolutionDatabase {

    List<Solution> getAllSolutions() throws SQLException;

    Solution getSolution(int id) throws NoSuchSolutionException, SQLException;

    void addSolution(Solution solution);

    void update(Solution solution);
}
