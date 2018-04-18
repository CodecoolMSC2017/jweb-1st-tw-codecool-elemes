package com.codecool.elemes.dao;

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
        Boolean isComplete;
        Boolean isCorrected;
        Boolean isPublished;
        String question;
        String answear;
        Integer grade;
        int id;
        int maxScore;

        while(resultSet.next()) {
            id = resultSet.getInt("id");
            isPublished = resultSet.getBoolean("is_published");
            isComplete = resultSet.getBoolean("is_complete");
            isCorrected = resultSet.getBoolean("is_corrected");
            question = resultSet.getString("question");
            answear = resultSet.getString("answer");
            grade = resultSet.getInt("grade");
            maxScore = resultSet.getInt("max_score");
            assignment = new Assignment(isComplete, isCorrected, isPublished,
                                    question, answear, grade, id, maxScore);
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            Role role = Role.valueOf(resultSet.getString("role"));
            user = new User(name, email, role);
            solutions.add(new Solution(assignment, user));
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
        Boolean isComplete;
        Boolean isCorrected;
        Boolean isPublished;
        String question;
        String answear;
        Integer grade;
        int maxScore;
        if (resultSet.next()) {
            isPublished = resultSet.getBoolean("is_published");
            isComplete = resultSet.getBoolean("is_complete");
            isCorrected = resultSet.getBoolean("is_corrected");
            question = resultSet.getString("question");
            answear = resultSet.getString("answer");
            grade = resultSet.getInt("grade");
            maxScore = resultSet.getInt("max_score");
            assignment = new Assignment(isComplete, isCorrected, isPublished,
                    question, answear, grade, id, maxScore);
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            Role role = Role.valueOf(resultSet.getString("role"));
            user = new User(name, email, role);
            return new Solution(assignment, user);
        }
        throw new SQLException();
    }

    @Override
    public void addSolution(Solution solution) throws SQLException {
            String sql = "insert into solutions (user_email, assignment_id, result) values (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, solution.getUser().geteMail());
            preparedStatement.setInt(2, solution.getAssignment().getId());
            preparedStatement.setInt(3, solution.getResult());
            preparedStatement.executeUpdate();
    }

    @Override
    public List<Solution> getGradedSolutions(int assignmentId) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql ="select * from solutions\n" +
                "join users on solutions.user_email = users.email\n" +
                "join assignments on solutions.assignment_id = assignments.id\n" +
                "where result <> null and assignment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, assignmentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Assignment assignment;
        User user;
        Boolean isComplete;
        Boolean isCorrected;
        Boolean isPublished;
        String question;
        String answear;
        Integer grade;
        int id;
        int maxScore;

        while(resultSet.next()) {
            id = resultSet.getInt("id");
            isPublished = resultSet.getBoolean("is_published");
            isComplete = resultSet.getBoolean("is_complete");
            isCorrected = resultSet.getBoolean("is_corrected");
            question = resultSet.getString("question");
            answear = resultSet.getString("answer");
            grade = resultSet.getInt("grade");
            maxScore = resultSet.getInt("max_score");
            assignment = new Assignment(isComplete, isCorrected, isPublished,
                    question, answear, grade, id, maxScore);
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            Role role = Role.valueOf(resultSet.getString("role"));
            user = new User(name, email, role);
            solutions.add(new Solution(assignment, user));
        }
        return solutions;
    }

    @Override
    public List<Solution> getSolutionsToGrade(int assignmentId) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        String sql ="select * from solutions\n" +
                "join users on solutions.user_email = users.email\n" +
                "join assignments on solutions.assignment_id = assignments.id\n" +
                "where result is null and assignment_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, assignmentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Assignment assignment;
        User user;
        Boolean isComplete;
        Boolean isCorrected;
        Boolean isPublished;
        String question;
        String answear;
        Integer grade;
        int id;
        int maxScore;

        while(resultSet.next()) {
            id = resultSet.getInt("id");
            isPublished = resultSet.getBoolean("is_published");
            isComplete = resultSet.getBoolean("is_complete");
            isCorrected = resultSet.getBoolean("is_corrected");
            question = resultSet.getString("question");
            answear = resultSet.getString("answer");
            grade = resultSet.getInt("grade");
            maxScore = resultSet.getInt("max_score");
            assignment = new Assignment(isComplete, isCorrected, isPublished,
                    question, answear, grade, id, maxScore);
            String email = resultSet.getString("email");
            String name = resultSet.getString("name");
            Role role = Role.valueOf(resultSet.getString("role"));
            user = new User(name, email, role);
            solutions.add(new Solution(assignment, user));
        }
        return solutions;
    }


}
