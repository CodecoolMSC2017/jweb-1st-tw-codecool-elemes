package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NotGradedYetException;

import java.util.Random;

public class Assignment {
    private Boolean isComplete;
    private Boolean isCorrected;
    private Boolean isPublished;
    private String question;
    private String answear;
    private Integer grade;
    private int id;
    private int maxScore;

    public Assignment(String question, int maxScore) {
        this.question = question;
        isComplete = false;
        isCorrected = false;
        isPublished = false;
        grade = null;
        Random random = new Random();
        id = random.nextInt(1000000);
        this.maxScore = maxScore;
    }

    public String getAnswear() {
        return answear;
    }

    public void setAnswear(String answear) {
        this.answear = answear;
        isComplete = true;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean isCorrected() {
        return isCorrected;
    }

    public void grade(int grade) {
        isCorrected = true;
        this.grade = grade;
    }

    public int getGrade() throws NotGradedYetException {
        if (isCorrected) {
            return grade;
        } else {
            throw new NotGradedYetException();
        }
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
}
