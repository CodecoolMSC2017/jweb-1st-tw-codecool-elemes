package com.codecool.elemes.servlet.attendance;

import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.impl.AttendanceDao;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/studentAttendance")
public class StudentAttendanceServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("loggedin");
        try(Connection connection = getConnection(getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            req.setAttribute("studentAttendance",attendanceDatabase.getMissedDays(user.geteMail()));
            req.getRequestDispatcher("studentAttendance").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
    }
}
