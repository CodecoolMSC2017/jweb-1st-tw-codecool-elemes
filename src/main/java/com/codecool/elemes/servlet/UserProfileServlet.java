package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.UserDao;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/userprofile")
public class UserProfileServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedin");
        req.setAttribute("user",user);
        req.getRequestDispatcher("userprofile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try(Connection connection = getConnection(req.getServletContext())) {
            UserDataBase userDataBase = new UserDao(connection);
        String name = req.getParameter("name");
        String role = req.getParameter("role");
        HttpSession session = req.getSession();
        User user =(User) session.getAttribute("loggedin");

        if (name.equals("")) {
            name = user.getName();
        }
            userDataBase.editUsername(user.geteMail(),name);
            userDataBase.editRole(user.geteMail(),role);
        }catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("userprofile");
    }
}