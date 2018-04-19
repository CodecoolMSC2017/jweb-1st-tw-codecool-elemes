package com.codecool.elemes.servlet.user;

import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.dao.impl.AttendanceDao;
import com.codecool.elemes.dao.impl.UserDao;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.AttendanceService;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/userpage")
public class UserPageServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            UserDataBase userDataBase = new UserDao(connection);
            AttendanceService attendanceService = new AttendanceService(attendanceDatabase, userDataBase);
            User user = (User) req.getSession().getAttribute("loggedin");
            req.getSession().setAttribute("redirect", attendanceService.getPage(user));
           req.getRequestDispatcher("userpage.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

