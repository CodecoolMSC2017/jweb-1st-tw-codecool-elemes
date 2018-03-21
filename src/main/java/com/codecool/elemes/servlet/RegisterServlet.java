package com.codecool.elemes.servlet;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.LoginService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!loginService.isRegistered(req)) {
            User user = loginService.createUser(req);
            Database.getInstance().add(user);
            HttpSession session = req.getSession();
            session.setAttribute("loggedin", user );
            req.setAttribute("user", user);
            resp.sendRedirect("userpage");
        } else {
            req.setAttribute("error", "The email address you have entered is already registered");
            req.getRequestDispatcher("register.jsp").include(req, resp);
        }
    }
}
