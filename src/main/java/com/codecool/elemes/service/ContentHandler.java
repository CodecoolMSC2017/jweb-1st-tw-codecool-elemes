package com.codecool.elemes.service;

import com.codecool.elemes.dao.TextDao;
import com.codecool.elemes.exceptions.InvalidInputException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Text;
import com.codecool.elemes.dao.TextDatabase;

import java.sql.SQLException;

public class ContentHandler {
    private TextDatabase database ;

    public ContentHandler(TextDatabase database) {
        this.database = database;
    }

    public void addNewTextContent(String title, String content) throws InvalidInputException, SQLException {
        if (title.equals("") || content.equals("")) {
            throw new InvalidInputException();
        }
        Text text = new Text(title, false,content);
        database.addText(text);
    }
}
