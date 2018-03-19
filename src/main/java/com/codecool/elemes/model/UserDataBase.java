package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NoSuchUserException;

import java.util.List;

public interface UserDataBase {

    void add(User user);

    List<User> getAllUser();

    void deleteUser(String email) throws NoSuchUserException;

    User getUser(String email) throws NoSuchUserException;
}
