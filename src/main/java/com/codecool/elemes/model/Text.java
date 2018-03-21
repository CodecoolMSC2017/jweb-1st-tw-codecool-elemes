package com.codecool.elemes.model;

import java.util.Random;

public class Text {
    private String text;
    private boolean isPublished;
    private String content;
    private int id;

    public Text(String text, boolean isPublished) {
        this.text = text;
        this.isPublished = isPublished;
        Random random = new Random();
        id = random.nextInt(1000000);
        content = "default content";
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

    public boolean getisPublished() {
        return isPublished;
    }

    public void unPublish() {
        isPublished = false;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }
}
