package com.codecool.elemes.model;

public class Text {
    private String text;
    private boolean isPublished;

    public Text(String text, boolean isPublished) {
        this.text = text;
        this.isPublished = isPublished;
    }

    public void publish() {
        isPublished = true;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public String getText() {
        return text;
    }
}
