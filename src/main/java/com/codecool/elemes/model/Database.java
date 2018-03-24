package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance = new Database();

    private AssigmentDatabase assigmentDatabase = Singleton.getInstance();
    private SolutionDatabase solutionDatabase = Singleton.getInstance();
    private TextDatabase textDatabase = Singleton.getInstance();
    private UserDataBase userDataBase = Singleton.getInstance();

    private Database(){}

    public void setAssigmentDatabase(AssigmentDatabase assigmentDatabase) {
        this.assigmentDatabase = assigmentDatabase;
    }

    public void setSolutionDatabase(SolutionDatabase solutionDatabase) {
        this.solutionDatabase = solutionDatabase;
    }

    public void setTextDatabase(TextDatabase textDatabase) {
        this.textDatabase = textDatabase;
    }

    public void setUserDataBase(UserDataBase userDataBase) {
        this.userDataBase = userDataBase;
    }

    public static Database getInstance() {
        return instance;
    }

    public void add(User user) {
        userDataBase.add(user);
    }


    public List<User> getAllUser() {
        return userDataBase.getAllUser();
    }


    public void deleteUser(String email) throws NoSuchUserException {
        userDataBase.deleteUser(email);
    }

    public User getUser(String email) throws NoSuchUserException {
        return userDataBase.getUser(email);
    }

    public List<Text> getTexts() {
        return textDatabase.getTexts();
    }

    public void addText(Text text) {
        textDatabase.addText(text);
    }

    public void deleteText(Text title) throws NoSuchTextException {
        textDatabase.deleteText(title);
    }

    public Text getText(int id) throws TextNotFoundException {
       return textDatabase.getText(id);
    }


    public List<Assignment> getAllAssignments() {
        return assigmentDatabase.getAllAssignments();
    }

    public void addAssignment(Assignment assignment) {
        assigmentDatabase.addAssignment(assignment);
    }

    public void deleteAssignment(Assignment assignment) throws NoSuchAssignmentException {
        assigmentDatabase.deleteAssignment(assignment);
    }

    public List<Solution> getAllSolutions() {
        return solutionDatabase.getAllSolutions();
    }

    public Solution getSolution(int id) throws NoSuchSolutionException {
        return solutionDatabase.getSolution(id);
    }

    public void addSolution(Solution solution) {
        solutionDatabase.addSolution(solution);
    }

    public Assignment getAssignment(int id) throws NoSuchAssignmentException {
       return assigmentDatabase.getAssignment(id);
    }
}
