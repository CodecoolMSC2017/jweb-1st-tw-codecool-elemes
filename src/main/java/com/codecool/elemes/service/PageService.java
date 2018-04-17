package com.codecool.elemes.service;

import com.codecool.elemes.dao.TextDatabase;
import com.codecool.elemes.model.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class PageService {

    TextDatabase database;

    public PageService(TextDatabase database) {
        this.database = database;
    }

    public List<Text> getTexts(User user) throws SQLException {

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

    public String getPage(User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            return "pages.jsp";
        }
        return "mentorPages.jsp";
    }

    public void handlePublish(HttpServletRequest req) throws SQLException {
        String condition;
       for (Text text :database.getTexts()) {
           if (req.getParameter(text.getTitle())!= null) {
                condition = req.getParameter(text.getTitle());
                if (condition.equals("true")) {
                    text.publish();
                    database.update(text);
                }
                else {
                    text.unPublish();
                    database.update(text);
                }
           }
       }
    }
}
