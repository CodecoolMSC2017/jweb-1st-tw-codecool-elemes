package com.codecool.elemes.servlet;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.Text;
import com.codecool.elemes.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pages")
public class PagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User("a","a", Role.MENTOR);
        Database database = Database.getInstance();
        List<Text> texts = new ArrayList<>();
        if (user.getRole().equals(Role.STUDENT)) {
            for (Text text : database.getTexts()) {
                if (text.isPublished()) {
                    texts.add(text);
                }
            }

        }
        else{
            texts = database.getTexts();
        }
        req.setAttribute("texts", texts);
        req.getRequestDispatcher("pages.jsp").forward(req, resp);

    }
}
