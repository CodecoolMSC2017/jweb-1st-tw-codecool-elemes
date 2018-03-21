package com.codecool.elemes.model;

public class Text {
    private String text;
    private boolean isPublished;
    private String content;

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
}
