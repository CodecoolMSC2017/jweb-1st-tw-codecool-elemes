package com.codecool.elemes.service;

import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListSolutionService {

    private SolutionDatabase database;

    public ListSolutionService(SolutionDatabase database) {
        this.database = database;
    }


    public List<Solution> getAllSolutions() throws SQLException {
        return database.getAllSolutions();
    }

    public List<Solution> getGradedSolutions(String assignmentId) {
       return database.getGradedSolutions(String assignmentId);
    }

    public List<Solution> getSolutionsToGrade(String assignmentId) {
        return database.getGradedSolutions(String assignmentId);
    }


}
