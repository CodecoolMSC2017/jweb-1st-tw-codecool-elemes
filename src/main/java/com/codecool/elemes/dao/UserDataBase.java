package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.util.List;

public interface UserDataBase {

    void add(User user);

    List<User> getAllUser();

    User getUser(String email) throws NoSuchUserException;

    List<User> getOnlyStudents(List<User> users);
}
