package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.LoginService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (loginService.isRegistered(email)) {
            try {
                User user = Database.getInstance().getUser(email);
                HttpSession session = req.getSession();
                session.setAttribute("loggedin", user );
                resp.sendRedirect("userpage");
            } catch (NoSuchUserException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("message", "Invalid login data, please check.");
            req.getRequestDispatcher("/index.jsp").include(req, resp);
        }
    }
}

