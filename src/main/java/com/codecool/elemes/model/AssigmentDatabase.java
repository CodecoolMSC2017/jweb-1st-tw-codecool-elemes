package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;

import java.util.List;

public interface AssigmentDatabase {

    List<Assignment> getAllAssignments();

    void addAssignment(Assignment assignment);

    Assignment getAssignment(int id) throws NoSuchAssignmentException;
}
