package com.codecool.elemes.dao.impl;

import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao implements UserDataBase {

    public UserDao(Connection connection) {
        super(connection);
    }

    @Override
    public void add(User user) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(true);
        String sql = "INSERT INTO users (email,name,role)VALUES(?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.geteMail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getRole().toString());
            executeInsert(statement);
        } finally {
            connection.setAutoCommit(autoCommit);
        }


    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String sql = "SELECT email, name, role FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
            return users;
        }

    }

    @Override
    public User getUser(String email) throws NoSuchUserException, SQLException {
        if (email == null) {
            return null;
        }
        String sql = "SELECT email,name,role FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next())
                    return fetchUser(resultSet);
            }
        }
        throw new NoSuchUserException();
    }

    @Override
    public List<User> getOnlyStudents() throws SQLException {
        List<User> onlyStudents = new ArrayList<>();
        String sql = "SELECT email, name, role FROM users WHERE role = 'STUDENT'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                onlyStudents.add(fetchUser(resultSet));
            }
            return onlyStudents;
        }


    }

    @Override
    public void editUsername(String email, String username) throws SQLException {
        String sql = "UPDATE users SET name = ? WHERE email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void editRole(String email, String role) throws SQLException {
        String sql = "UPDATE users SET role = ? WHERE email = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,role.toUpperCase());
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
        }
    }

    User fetchUser(ResultSet resultSet) throws SQLException {
        String email = resultSet.getString("email");
        String name = resultSet.getString("name");
        Role role = Role.valueOf(resultSet.getString("role"));
        return new User(name, email, role);
    }

}
