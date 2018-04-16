package com.codecool.elemes.service;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import com.codecool.elemes.dao.UserDataBase;

public final class LoginService {

    private UserDataBase userDataBase;

    public LoginService(UserDataBase userDataBase) {
        this.userDataBase = userDataBase;
    }

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
        userDataBase.add(user);
        return user;
    }
}
