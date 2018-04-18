package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NotGradedYetException;

import java.util.Random;

public class Assignment {
    private Boolean isPublished;
    private String question;
    private int id;
    private int maxScore;

    public Assignment(Boolean isPublished, String question, int id, int maxScore) {

        this.isPublished = isPublished;
        this.question = question;
        this.id = id;
        this.maxScore = maxScore;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void publish() {
        isPublished = true;
    }

    public void unPublish() {
        isPublished = false;
    }

    public int getId() {
        return id;
    }

    public boolean getisPublished() {
        return isPublished;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public Assignment(String question, int maxScore) {
        this.question = question;
        this.maxScore = maxScore;
        this.isPublished = false;
    }
}
