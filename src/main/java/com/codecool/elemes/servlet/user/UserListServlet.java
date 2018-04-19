package com.codecool.elemes.servlet.user;

import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.dao.impl.UserDao;
import com.codecool.elemes.servlet.AbstractServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/userlist")
public class UserListServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDataBase userDataBase = new UserDao(connection);
            req.setAttribute("users", userDataBase.getAllUser());
            req.getRequestDispatcher("userlist.jsp").forward(req, resp);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }


    }
}
