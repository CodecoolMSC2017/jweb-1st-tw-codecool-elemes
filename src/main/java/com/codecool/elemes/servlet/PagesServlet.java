package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.TextDao;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/pages")
public class PagesServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        try (Connection connection = getConnection(req.getServletContext())) {
            TextDao textDao = new TextDao(connection);
            PageService pageService = new PageService(textDao);

            req.setAttribute("texts", pageService.getTexts(user));
            req.getRequestDispatcher(pageService.getPage(user)).forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loggedin");
        try (Connection connection = getConnection(req.getServletContext())) {
            TextDao textDao = new TextDao(connection);
            PageService pageService = new PageService(textDao);

            pageService.handlePublish(req);

            req.setAttribute("texts", pageService.getTexts(user));
            req.getRequestDispatcher(pageService.getPage(user)).forward(req, resp);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
