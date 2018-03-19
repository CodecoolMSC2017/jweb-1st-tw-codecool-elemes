package com.codecool.web.model;

import com.codecool.web.exceptions.NoSuchUserException;

public interface UserDataBase {

    void add(User user);

    List<User> getAllUser();

    void deleteUser(String email) throws NoSuchUserException;

    User getUser(String email) throws NoSuchUserException;
}
