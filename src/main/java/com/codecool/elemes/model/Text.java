package com.codecool.elemes.model;

import java.util.Random;

public class Text {
    private String title;
    private boolean isPublished;
    private String content;
    private int id;


    public Text(String title, boolean isPublished, String content) {
        this.title = title;
        this.isPublished = isPublished;
        this.content = content;
    }

    public void publish() {
        isPublished = true;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public String getTitle() {
        return title;
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
