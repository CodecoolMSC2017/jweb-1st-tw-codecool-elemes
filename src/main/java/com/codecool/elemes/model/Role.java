package com.codecool.elemes.model;

public enum Role {
    MENTOR ("mentor"),
    STUDENT ("student");

    public final String role;

    Role(String role) {

        this.role = role;
    }

    public String toString(){
        return this.role;
    }
}
