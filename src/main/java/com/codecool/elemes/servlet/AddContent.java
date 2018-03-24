package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.InvalidInputException;
import com.codecool.elemes.service.ContentHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addcontent")
public class AddContent extends HttpServlet {

    ContentHandler contentHandler = new ContentHandler();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        try {
            contentHandler.addNewTextContent(title, content);
        } catch (InvalidInputException e) {
            req.setAttribute("message", "Invalid input");
            req.getRequestDispatcher("pages").include(req, resp);
            req.getRequestDispatcher("userpage.jsp").include(req, resp);
        }
        req.setAttribute("message", "Text added");
        req.getRequestDispatcher("pages").include(req, resp);
        req.getRequestDispatcher("userpage.jsp").include(req, resp);
    }
}
