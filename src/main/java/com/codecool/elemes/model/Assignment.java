package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NotGradedYetException;

public class Assignment {
    private Boolean isComplete;
    private Boolean isCorrected;
    private Boolean isPublished;
    private String question;
    private String answear;
    private Integer grade;

    public Assignment(String question) {
        this.question = question;
        isComplete = false;
        isCorrected = false;
        isPublished = false;
        grade = null;
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
}
