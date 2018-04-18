package com.codecool.elemes.servlet.user;

import com.codecool.elemes.dao.impl.UserDao;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.LoginService;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDataBase userDataBase = new UserDao(connection);
            LoginService loginService = new LoginService(userDataBase);
            String email = req.getParameter("email");
            String name = req.getParameter("name");
            String role = req.getParameter("role");
            if (!loginService.isRegistered(email)) {
                User user = loginService.createUser(name, email, Role.valueOf(role));
                HttpSession session = req.getSession();
                session.setAttribute("loggedin", user);
                req.setAttribute("user", user);
                resp.sendRedirect("userpage");
            } else {
                req.setAttribute("error", "The email address you have entered is already registered");
                req.getRequestDispatcher("register.jsp").include(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
