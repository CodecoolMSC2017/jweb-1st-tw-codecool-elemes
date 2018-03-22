package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NoSuchSolutionException;

import java.util.List;

public interface SolutionDatabase {

    List<Solution> getAllSolutions();

    Solution getSolution(int id) throws NoSuchSolutionException;

    void addSolution(Solution solution);


}
