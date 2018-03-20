package com.codecool.elemes.servlet;

import com.codecool.elemes.model.User;
import com.codecool.elemes.service.PageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pages")
public class PagesServlet extends HttpServlet {
    private PageService pageService = new PageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedin");

        req.setAttribute("texts",pageService.getTexts(user));
        req.getRequestDispatcher("pages.jsp").forward(req, resp);

    }
}
