package com.codecool.elemes.servlet.user;

import com.codecool.elemes.dao.impl.UserDao;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/userprofile")
public class UserProfileServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        try(Connection connection = getConnection(req.getServletContext())){
            UserDataBase userDataBase = new UserDao(connection);
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("loggedin");
            req.setAttribute("user", userDataBase.getUser(user.geteMail()));
            req.getRequestDispatcher("userprofile.jsp").forward(req, resp);
        } catch (SQLException | IOException | ServletException | NoSuchUserException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDataBase userDataBase = new UserDao(connection);
            String name = req.getParameter("name");
            String role = req.getParameter("role");
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("loggedin");

            if (name.equals("")) {
                name = user.getName();
            }
            userDataBase.editUsername(user.geteMail(), name);
            user.setName(name);
            userDataBase.editRole(user.geteMail(), role);
            user.setRole(Role.valueOf(role));
            resp.sendRedirect("userprofile");
        } catch (NoSuchUserException | SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}