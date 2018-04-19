package com.codecool.elemes.dao.impl;

import com.codecool.elemes.dao.AssigmentDatabase;
import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.model.Assignment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDao extends AbstractDao implements AssigmentDatabase {

    public AssignmentDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Assignment> getAllAssignments() throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM assignments";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            Boolean isComplete;
            Boolean isCorrected;
            Boolean isPublished;
            String question;
            String answear;
            Integer grade;
            int id;
            int maxScore;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                isPublished = resultSet.getBoolean("is_published");
                question = resultSet.getString("question");
                maxScore = resultSet.getInt("max_score");

                assignments.add(new Assignment(isPublished,
                        question, id, maxScore));
            }

        }
        return assignments;
    }

    @Override
    public void addAssignment(Assignment assignment) throws SQLException {
        String sql = "INSERT INTO assignments (is_published, question, max_score) VALUES (?,?,?)";
        Boolean isPublished = assignment.getisPublished();
        String question = assignment.getQuestion();
        int maxScore = assignment.getMaxScore();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, isPublished);
            preparedStatement.setString(2, question);
            preparedStatement.setInt(3, maxScore);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Assignment getAssignment(int id) throws NoSuchAssignmentException, SQLException {
        String sql = "SELECT * FROM assignments WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Boolean isComplete;
                Boolean isCorrected;
                Boolean isPublished;
                String question;
                String answear;
                Integer grade;
                int maxScore;
                if (resultSet.next()) {
                    isPublished = resultSet.getBoolean("is_published");
                    question = resultSet.getString("question");
                    maxScore = resultSet.getInt("max_score");
                    return new Assignment(isPublished, question, id, maxScore);
                }

            }
            throw new NoSuchAssignmentException();
        }
    }

    @Override
    public void update(Assignment assignment) throws SQLException {
        String sql = "UPDATE assignments SET is_published = ?,question = ?,max_score = ? WHERE id = ?";

        Boolean isPublished = assignment.getisPublished();
        String question = assignment.getQuestion();


        int maxScore = assignment.getMaxScore();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, isPublished);

            preparedStatement.setString(2, question);

            preparedStatement.setInt(3, maxScore);
            preparedStatement.setInt(4, assignment.getId());
            preparedStatement.executeUpdate();

        }
    }
}
