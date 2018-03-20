package com.codecool.elemes.servlet;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = Database.getInstance();
        req.setAttribute("users",database.getAllUser());
        req.getRequestDispatcher("userlist.jsp").forward(req, resp);


    }
}
