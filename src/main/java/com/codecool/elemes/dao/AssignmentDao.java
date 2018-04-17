package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchAssignmentException;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.exceptions.TextNotFoundException;
import com.codecool.elemes.model.Assignment;
import com.codecool.elemes.model.Text;

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
                isComplete = resultSet.getBoolean("is_complete");
                isCorrected = resultSet.getBoolean("is_corrected");
                question = resultSet.getString("question");
                answear = resultSet.getString("answer");
                grade = resultSet.getInt("grade");
                maxScore = resultSet.getInt("max_score");

                assignments.add(new Assignment(isComplete, isCorrected, isPublished, question, answear, grade, id, maxScore));
            }

        }
        return assignments;
    }

    @Override
    public void addAssignment(Assignment assignment) throws SQLException, NotGradedYetException {
        String sql = "INSERT INTO assignments (is_published, is_corrected, is_complete, answer, question, grade, max_score) VALUES (?,?,?,?,?,?,?)";
        Boolean isComplete = assignment.getIsComplete();
        Boolean isCorrected = assignment.getIsCorrected();
        Boolean isPublished = assignment.getisPublished();
        String question = assignment.getQuestion();
        String answear = assignment.getAnswear();
        Integer grade = assignment.getGrade();
        int maxScore = assignment.getMaxScore();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, isPublished);
            preparedStatement.setBoolean(2, isCorrected);
            preparedStatement.setBoolean(3, isComplete);
            preparedStatement.setString(4, answear);
            preparedStatement.setString(5, question);
            preparedStatement.setInt(6, grade);
            preparedStatement.setInt(7, maxScore);
            preparedStatement.executeQuery();
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
                    isComplete = resultSet.getBoolean("is_complete");
                    isCorrected = resultSet.getBoolean("is_corrected");
                    question = resultSet.getString("question");
                    answear = resultSet.getString("answer");
                    grade = resultSet.getInt("grade");
                    maxScore = resultSet.getInt("max_score");
                    return new Assignment(isComplete, isCorrected, isPublished, question, answear, grade, id, maxScore);
                } else throw new NoSuchAssignmentException();
            }
        }
    }

    @Override
    public void update(Assignment assignment) throws SQLException {
        String sql = "UPDATE assignments SET is_published = ?, is_corrected = ?, is_complete = ?, answer = ?, question = ?, grade = ?, max_score = ? WHERE id = ?";
        Boolean isComplete = assignment.getIsComplete();
        Boolean isCorrected = assignment.getIsCorrected();
        Boolean isPublished = assignment.getisPublished();
        String question = assignment.getQuestion();
        String answear = assignment.getAnswear();
        Integer grade;
        try {
            grade = assignment.getGrade();
        } catch (NotGradedYetException e) {
            grade = 0;
        }

        int maxScore = assignment.getMaxScore();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setBoolean(1, isPublished);
            preparedStatement.setBoolean(2, isCorrected);
            preparedStatement.setBoolean(3, isComplete);
            preparedStatement.setString(4, answear);
            preparedStatement.setString(5, question);
            preparedStatement.setInt(6, grade);
            preparedStatement.setInt(7, maxScore);
            preparedStatement.setInt(8, assignment.getId());
            preparedStatement.executeUpdate();

        }
    }
}
