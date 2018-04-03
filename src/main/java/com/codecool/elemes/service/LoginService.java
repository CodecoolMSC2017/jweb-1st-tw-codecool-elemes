package com.codecool.elemes.service;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;

import javax.servlet.http.HttpServletRequest;

public final class LoginService {

    public boolean isRegistered(String email) {
        for(User user : Database.getInstance().getAllUser()) {
            if (user.geteMail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User createUser(String name, String email, Role role) {
        User user = new User(name, email, role);
        Database.getInstance().add(user);
        return user;
    }
}
