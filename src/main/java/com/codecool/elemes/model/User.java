package com.codecool.elemes.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(eMail, user.eMail) &&
                role == user.role &&
                Objects.equals(name, user.name);
    }
}
