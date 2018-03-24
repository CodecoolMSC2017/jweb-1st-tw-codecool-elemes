package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.TextNotFoundException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Text;
import com.codecool.elemes.model.TextDatabase;

public final class ContentShowService {

    Database database = Database.getInstance();

    public String getContent(int textId) {
        Text text;
        try {
            text = database.getText(textId);
            return text.getContent();
        } catch (TextNotFoundException e) {
            return "Content not found";
        }
    }
}
