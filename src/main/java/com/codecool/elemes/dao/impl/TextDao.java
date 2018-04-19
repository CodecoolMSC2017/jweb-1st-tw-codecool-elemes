package com.codecool.elemes.dao.impl;

import com.codecool.elemes.dao.TextDatabase;
import com.codecool.elemes.exceptions.NoSuchTextException;
import com.codecool.elemes.exceptions.TextNotFoundException;
import com.codecool.elemes.model.Text;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TextDao extends AbstractDao implements TextDatabase {

    public TextDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Text> getTexts() throws SQLException {
        List<Text> texts = new ArrayList<>();
        String sql = "SELECT * FROM texts";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            int id;
            boolean isPublished;
            String title;
            String content;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                isPublished = resultSet.getBoolean("is_published");
                title = resultSet.getString("title");
                content = resultSet.getString("content");
                texts.add(new Text(title, isPublished, id, content));
            }

        }
        return texts;
    }

    @Override
    public void addText(Text text) throws SQLException {
        String sql = "INSERT INTO texts (title, content, is_published) VALUES (?,?,?)";
        boolean isPublished = text.getisPublished();
        String title = text.getTitle();
        String content = text.getContent();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, content);
            preparedStatement.setBoolean(3, isPublished);
            preparedStatement.executeQuery();
        }

    }

    @Override
    public void deleteText(Text text) throws NoSuchTextException {

    }

    @Override
    public Text getText(int id) throws TextNotFoundException, SQLException {
        String sql = "SELECT * FROM texts WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    boolean isPublished = resultSet.getBoolean("is_published");
                    String title = resultSet.getString("title");
                    String content = resultSet.getString("content");
                    return new Text(title, isPublished, id, content);
                } else throw new TextNotFoundException();
            }
        }
    }

    @Override
    public void update(Text text) throws SQLException {
        String sql = "UPDATE texts SET title = ?, content = ?, is_published = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,text.getTitle());
            statement.setString(2,text.getContent());
            statement.setBoolean(3,text.getisPublished());
            statement.setInt(4,text.getId());
            statement.executeUpdate();

        }
    }
}
