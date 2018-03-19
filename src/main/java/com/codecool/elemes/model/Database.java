package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NoSuchUserException;

import java.util.ArrayList;
import java.util.List;

public class Database implements UserDataBase {

    private static Database instance = Database.createInstance();

    private List<User> users = new ArrayList<User>();

    private Database(){}

    public static Database createInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUser() {
        return users;
    }

    @Override
    public void deleteUser(String email) throws NoSuchUserException {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.geteMail().equals(email)) {
                users.remove(i);
                return;
            }
        }
        throw new NoSuchUserException();
    }

    @Override
    public User getUser(String email) throws NoSuchUserException {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.geteMail().equals(email)) {
                return user;
            }
        }
        throw new NoSuchUserException();
    }
}
