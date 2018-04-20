package com.codecool.elemes.service;

import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import com.codecool.elemes.dao.UserDataBase;

import java.sql.SQLException;

public final class LoginService {

    private UserDataBase userDataBase;

    public LoginService(UserDataBase userDataBase) {
        this.userDataBase = userDataBase;
    }

    public boolean isRegistered(String email) throws SQLException {
        for(User user : userDataBase.getAllUser()) {
            if (user.geteMail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public User createUser(String name, String email, Role role) throws SQLException {
        User user = new User(name, email, role);
        userDataBase.add(user);
        return user;
    }
}
