package com.codecool.elemes.servlet.text;

import com.codecool.elemes.dao.impl.TextDao;
import com.codecool.elemes.exceptions.InvalidInputException;
import com.codecool.elemes.service.ContentHandler;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/addcontent")
public class AddContent extends AbstractServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        try (Connection connection = getConnection(req.getServletContext())){
            TextDao textDao = new TextDao(connection);
            ContentHandler contentHandler = new ContentHandler(textDao);
            contentHandler.addNewTextContent(title, content);
        } catch (InvalidInputException e) {
            req.setAttribute("message", "Invalid input");
            req.getRequestDispatcher("pages").include(req, resp);
        } catch (SQLException e) {
            req.setAttribute("message", "Invalid input");
            req.getRequestDispatcher("pages").include(req, resp);
        }
        req.setAttribute("message", "Text added");
        req.getRequestDispatcher("pages").include(req, resp);
    }
}
