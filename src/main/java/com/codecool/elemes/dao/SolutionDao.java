package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao extends AbstractDao implements SolutionDatabase {

    SolutionDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Solution> getAllSolutions() throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql = "select * from solutions" +
                "join users on solutions .user_email = users.email" +
                "join assignments on solutions.assignment_id = assignments.id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            Boolean isComplete;
            Boolean isCorrected;
            Boolean isPublished;
            String question;
            String answer;
            Integer grade;
            Integer id;
            Integer maxScore;
            String name;
            String email;
            Role role;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                isPublished = resultSet.getBoolean("is_published");
                isComplete = resultSet.getBoolean("is_complete");
                isCorrected = resultSet.getBoolean("is_corrected");
                question = resultSet.getString("question");
                answer = resultSet.getString("answer");
                grade = resultSet.getInt("grade");
                maxScore = resultSet.getInt("max_score");
                name = resultSet.getString("name");
                email = resultSet.getString("user_email");
                role = Role.valueOf(resultSet.getString("role"));

                solutions.add(new Solution(new Assignment(isComplete, isCorrected, isPublished, question, answer, grade, id, maxScore), new User(name, email, role)));
            }

        }
        return solutions;
    }

    @Override
    public Solution getSolution(int id) throws NoSuchSolutionException, SQLException {
        String sql = "select * from solutions" +
                "join users on solutions .user_email = users.email" +
                "join assignments on solutions.assignment_id = assignments.id" +
                "where solutions.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Boolean isComplete;
                Boolean isCorrected;
                Boolean isPublished;
                String question;
                String answer;
                Integer grade;
                Integer maxScore;
                String name;
                String email;
                Role role;
                if (resultSet.next()) {
                    isPublished = resultSet.getBoolean("is_published");
                    isComplete = resultSet.getBoolean("is_complete");
                    isCorrected = resultSet.getBoolean("is_corrected");
                    question = resultSet.getString("question");
                    answer = resultSet.getString("answer");
                    grade = resultSet.getInt("grade");
                    maxScore = resultSet.getInt("max_score");
                    name = resultSet.getString("name");
                    email = resultSet.getString("user_email");
                    role = Role.valueOf(resultSet.getString("role"));
                    return new Solution(new Assignment(isComplete, isCorrected, isPublished, question, answer, grade, id, maxScore), new User(name, email, role));
                }

            }
        }
        return null;

    }

    @Override
    public void addSolution(Solution solution) {

    }

    @Override
    public void update(Solution solution) {

    }
}
