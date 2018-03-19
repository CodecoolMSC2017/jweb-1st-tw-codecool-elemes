package com.codecool.web.model;

import com.codecool.web.exceptions.NoSuchUserException;

import java.util.ArrayList;
import java.util.List;

public class DataBase implements UserDataBase {

    private static DataBase instance = DataBase.createInstance();

    private List<User> users = new ArrayList<User>();

    private DataBase(){};

    public static DataBase createInstance() {
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
