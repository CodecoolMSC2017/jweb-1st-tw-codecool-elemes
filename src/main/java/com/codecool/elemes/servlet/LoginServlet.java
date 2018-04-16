package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.UserDao;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(getServletContext())) {
            try {
                UserDataBase userDataBase = new UserDao(connection);
                LoginService loginService = new LoginService(userDataBase);
                String email = req.getParameter("email");
                if (loginService.isRegistered(email)) {
                    User user = userDataBase.getUser(email);
                    HttpSession session = req.getSession();
                    session.setAttribute("loggedin", user);
                    resp.sendRedirect("userpage");
                } else {
                    req.setAttribute("message", "Invalid login data, please check.");
                    req.getRequestDispatcher("/index.jsp").include(req, resp);
                }
            } catch (NoSuchUserException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}


