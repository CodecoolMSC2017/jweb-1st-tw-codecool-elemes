package com.codecool.elemes.model;

import java.util.Random;

public class Solution {
    private Assignment assignment;
    private User user;
    private int result;
    private int id;
    private String answer;

    public Solution(Assignment assignment, User user, String answer, int id) {
        this.assignment = assignment;
        this.user = user;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    public Solution(Assignment assignment, User user) {
        this.assignment = assignment;
        this.user = user;
    }

    public void setAnswer(String answer) {
        this.answer = answer;

    }

    public boolean equals(Solution solution) {
        if (solution.getAssignment().getQuestion().equals(this.getAssignment().getQuestion()) && solution.getUser().getName().equals(this.getUser().getName())) {
            return true;
        }
        return false;
    }
}
