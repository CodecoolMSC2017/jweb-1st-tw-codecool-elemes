package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.TextNotFoundException;
import com.codecool.elemes.model.Text;
import com.codecool.elemes.dao.TextDatabase;

import java.sql.SQLException;

public final class ContentShowService {

    TextDatabase database ;

    public ContentShowService(TextDatabase database) {
        this.database = database;
    }

    public String getContent(int textId) throws SQLException {
        Text text;
        try {
            text = database.getText(textId);
            return text.getContent();
        } catch (TextNotFoundException e) {
            return "Content not found";
        }
    }
}
