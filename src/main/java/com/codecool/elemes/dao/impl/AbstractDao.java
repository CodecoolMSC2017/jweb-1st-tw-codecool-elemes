package com.codecool.elemes.dao.impl;

import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

abstract class AbstractDao {

    final Connection connection;

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    void executeInsert(PreparedStatement statement) throws SQLException {
        int insertCount = statement.executeUpdate();
        if (insertCount != 1) {
            connection.rollback();
            throw new SQLException("Expected 1 row to be inserted");
        }
    }

    Assignment fetchAssignment(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        boolean isPublished = resultSet.getBoolean("is_published");
        String question = resultSet.getString("question");
        int maxScore = resultSet.getInt("max_score");
        return new Assignment(isPublished,
                question, id, maxScore);
    }

    User fetchUser(ResultSet resultSet) throws SQLException {
        String email = resultSet.getString("email");
        String name = resultSet.getString("name");
        Role role = Role.valueOf(resultSet.getString("role"));
        return new User(name, email, role);
    }
}
