package com.codecool.elemes.service;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.Text;
import com.codecool.elemes.model.User;

import java.util.ArrayList;
import java.util.List;

public final class PageService {
    public List<Text> getTexts(User user) {
        Database database = Database.getInstance();
        List<Text> texts = new ArrayList<>();
        if (user.getRole().equals(Role.STUDENT)) {
            for (Text text : database.getTexts()) {
                if (text.isPublished()) {
                    texts.add(text);
                }
            }
        } else {
            texts = database.getTexts();
        }
        return texts;
    }
}
