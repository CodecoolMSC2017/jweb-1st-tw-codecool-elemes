package com.codecool.elemes.servlet;

import com.codecool.elemes.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final LoginService loginService = new LoginService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (loginService.isRegistered(req)) {
            req.setAttribute("user", req.getParameter("user"));
            resp.sendRedirect("homepage.jsp");
        } else {
            req.setAttribute("message", "Invalid login data, please check.");
            req.getRequestDispatcher("index.jsp").include(req, resp);
        }
    }
}

