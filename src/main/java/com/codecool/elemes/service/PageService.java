package com.codecool.elemes.service;

import com.codecool.elemes.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public final class PageService {

    TextDatabase database = Database.getInstance();

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

    public String getPage(User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            return "app/pages.jsp";
        }
        return "app/mentorPages.jsp";
    }

    public void handlePublish(HttpServletRequest req) {
        String condition;
       for (Text text :database.getTexts()) {
           if (req.getParameter(text.getTitle())!= null) {
                condition = req.getParameter(text.getTitle());
                if (condition.equals("true")) {
                    text.publish();
                }
                else {
                    text.unPublish();
                }
           }
       }
    }
}
