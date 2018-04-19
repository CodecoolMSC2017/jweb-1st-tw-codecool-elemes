package com.codecool.elemes.dao.impl;

import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao extends AbstractDao implements SolutionDatabase {

    public SolutionDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Solution> getAllSolutions() throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql = "select * from solutions\n" +
                "join users on solutions.user_email = users.email\n" +
                "join assignments on solutions.assignment_id = assignments.id";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        Assignment assignment;
        User user;
        int id;
        while (resultSet.next()) {
            id = resultSet.getInt("id");
            String answer = resultSet.getString("answer");
            int result = resultSet.getInt("result");
            assignment = fetchAssignment(resultSet);
            user = fetchUser(resultSet);
            solutions.add(new Solution(assignment, user, answer, id, result));
        }
        return solutions;
    }

    @Override
    public Solution getSolution(int id) throws NoSuchSolutionException, SQLException {
        String sql = "select * from solutions\n" +
                "join users on solutions.user_email = users.email\n" +
                "join assignments on solutions.assignment_id = assignments.id where solutions.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Assignment assignment;
        User user;
        Boolean isPublished;
        String question;
        String answear;
        int maxScore;
        if (resultSet.next()) {
            isPublished = resultSet.getBoolean("is_published");
            question = resultSet.getString("question");
            answear = resultSet.getString("answer");
            maxScore = resultSet.getInt("max_score");
            assignment = new Assignment(isPublished,
                    question, id, maxScore);
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            Role role = Role.valueOf(resultSet.getString("role"));
            user = new User(name, email, role);
            return new Solution(assignment, user, answear, id);
        }
        throw new SQLException();
    }

    @Override
    public void addSolution(Solution solution) throws SQLException {
        String sql = "insert into solutions (user_email, assignment_id, result) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, solution.getUser().geteMail());
        preparedStatement.setInt(2, solution.getAssignment().getId());
        if (solution.getResult() == null) {
            preparedStatement.setNull(3, java.sql.Types.INTEGER);
        } else {
            preparedStatement.setInt(3, solution.getResult());
        }
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Solution> getGradedSolutions(int assignmentId) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql = "select * from solutions \n" +
                "join users on solutions.user_email = users.email \n" +
                "join assignments on solutions.assignment_id = assignments.id \n" +
                "where result > 0 and assignment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, assignmentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Assignment assignment;
        User user;
        Boolean isPublished;
        String question;
        String answear;
        int id;
        int maxScore;

        while (resultSet.next()) {
            id = resultSet.getInt("id");
            isPublished = resultSet.getBoolean("is_published");
            question = resultSet.getString("question");
            answear = resultSet.getString("answer");
            maxScore = resultSet.getInt("max_score");
            assignment = new Assignment(isPublished,
                    question, id, maxScore);
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            Role role = Role.valueOf(resultSet.getString("role"));
            user = new User(name, email, role);
            solutions.add(new Solution(assignment, user, answear, id));
        }
        return solutions;
    }

    @Override
    public List<Solution> getSolutionsToGrade(int assignmentId) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql = "select * from solutions \n" +
                "join users on solutions.user_email = users.email \n" +
                "join assignments on solutions.assignment_id = assignments.id \n" +
                "where result = 0 and assignment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, assignmentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Assignment assignment;
        User user;
        Boolean isPublished;
        String question;
        String answear;
        int id;
        int maxScore;

        while (resultSet.next()) {
            id = resultSet.getInt("id");
            isPublished = resultSet.getBoolean("is_published");
            question = resultSet.getString("question");
            answear = resultSet.getString("answer");
            maxScore = resultSet.getInt("max_score");
            assignment = new Assignment(isPublished,
                    question, id, maxScore);
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            Role role = Role.valueOf(resultSet.getString("role"));
            user = new User(name, email, role);
            solutions.add(new Solution(assignment, user, answear, id));
        }
        return solutions;
    }

    @Override
    public Solution getUserSolutionsAtAssignmentId(String userEmail, int assignmentId) throws SQLException, NoSuchSolutionException {
        String sql = "select * from solutions \n" +
                "join users on solutions.user_email = users.email \n" +
                "join assignments on solutions.assignment_id = assignments.id " +
                "where users.email = ? and assignment_id =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userEmail);
            preparedStatement.setInt(2, assignmentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Assignment assignment;
                User user;
                Boolean isPublished;
                String question;
                String answear;
                int id;
                int maxScore;
                if (resultSet.next()) {
                    id = resultSet.getInt("assignment_id");
                    isPublished = resultSet.getBoolean("is_published");
                    question = resultSet.getString("question");
                    answear = resultSet.getString("answer");
                    Integer result = resultSet.getInt("result");
                    maxScore = resultSet.getInt("max_score");
                    assignment = new Assignment(isPublished,
                            question, id, maxScore);
                    String email = resultSet.getString("email");
                    String name = resultSet.getString("name");
                    Role role = Role.valueOf(resultSet.getString("role"));
                    user = new User(name, email, role);
                    return new Solution(assignment, user, answear, id, result);
                } else {
                    throw new NoSuchSolutionException();
                }
            }
        }
    }

    @Override
    public void update(Solution solution) throws SQLException {
        String sql = "update solutions \n" +
                "set result = ?, answer = ? \n" +
                "where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, solution.getResult());
            preparedStatement.setString(2, solution.getAnswer());
            preparedStatement.setInt(3, solution.getId());
            preparedStatement.executeUpdate();
        }
    }
}