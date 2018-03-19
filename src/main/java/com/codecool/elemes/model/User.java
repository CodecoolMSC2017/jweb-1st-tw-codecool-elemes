package com.codecool.elemes.model;

public class User {

    private final String eMail;
    private  Role role;
    private  String name;

    public User(String name, String eMail, Role role) {
        this.eMail = eMail;
        this.role = role;
        this.name = name;

    }

    public String geteMail() {
        return eMail;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }
}
