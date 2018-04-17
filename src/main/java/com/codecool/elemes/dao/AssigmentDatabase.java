package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.model.Assignment;

import java.sql.SQLException;
import java.util.List;

public interface AssigmentDatabase {

    List<Assignment> getAllAssignments() throws SQLException;

    void addAssignment(Assignment assignment) throws SQLException, NotGradedYetException;

    Assignment getAssignment(int id) throws NoSuchAssignmentException, SQLException;

    void update(Assignment assignment) throws SQLException, NotGradedYetException;
}
