package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.awt.geom.QuadCurve2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserDataBase {

    void add(User user) throws SQLException;

    List<User> getAllUser() throws SQLException;

    User getUser(String email) throws NoSuchUserException, SQLException;

    List<User> getOnlyStudents() throws SQLException;

    void editUsername(String email,String username)throws NoSuchUserException,SQLException;

    void editRole(String email,String role)throws NoSuchUserException,SQLException;
}
