package com.codecool.elemes.service;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;

import javax.servlet.http.HttpServletRequest;

public final class LoginService {

    public boolean isRegistered(HttpServletRequest req) {
        String email = req.getParameter("email");
        for(User user : Database.getInstance().getAllUser()) {
            if (user.geteMail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
