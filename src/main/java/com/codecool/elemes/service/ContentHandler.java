package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.InvalidInputException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Text;
import com.codecool.elemes.model.TextDatabase;

public class ContentHandler {
    private Database database = Database.getInstance();

    public void addNewTextContent(String title, String content) throws InvalidInputException {
        if (title == "" || content == "") {
            throw new InvalidInputException();
        }
        Text text = new Text(title, false);
        text.setContent(content);
        database.addText(text);
    }
}
