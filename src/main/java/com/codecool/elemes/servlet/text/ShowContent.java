package com.codecool.elemes.servlet.text;

import com.codecool.elemes.dao.impl.TextDao;
import com.codecool.elemes.service.ContentShowService;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/content")
public class ShowContent extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try (Connection connection = getConnection(req.getServletContext())){
            TextDao textDao = new TextDao(connection);
            ContentShowService contentShowService = new ContentShowService(textDao);
            String content = contentShowService.getContent(id);

            String[] asd;
            asd = content.split("\n");
            req.setAttribute("content",asd);
            req.getRequestDispatcher("content.jsp").forward(req, resp);
        } catch (SQLException e) {
            req.setAttribute("content", e.getMessage());
        }
        req.getRequestDispatcher("content.jsp").forward(req, resp);

    }
}
