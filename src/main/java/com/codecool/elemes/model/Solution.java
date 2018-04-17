package com.codecool.elemes.model;

import java.util.Random;

public class Solution {
    private Assignment assignment;
    private User user;
    private int result;
    private int id;

    public Solution(Assignment assignment, User user) {
        this.assignment = assignment;
        this.user = user;

    }

    public Solution(Assignment assignment, User user, int id) {
        this.assignment = assignment;
        this.user = user;
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public int getResult() {
        return result;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setResult(int result) {
        this.result = result;
    }


    public boolean equals(Solution solution) {
        if (solution.getAssignment().getQuestion().equals(this.getAssignment().getQuestion()) && solution.getUser().getName().equals(this.getUser().getName())) {
            return true;
        }
        return false;
    }
}
