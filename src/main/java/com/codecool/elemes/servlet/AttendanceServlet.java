package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.AttendanceDao;
import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.UserDao;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.AttendanceAlreadyUpdated;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.service.AttendanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/attendance")
public class AttendanceServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            UserDataBase userDataBase = new UserDao(connection);
            req.setAttribute("students", userDataBase.getOnlyStudents());
            req.getAttribute("users");
            req.setAttribute("AllOverAttendance", attendanceDatabase.getAttendanceMap());
            req.getRequestDispatcher("attendance.jsp").forward(req, resp);
        } catch (SQLException | IOException | NoSuchUserException | ServletException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            UserDataBase userDataBase = new UserDao(connection);
            AttendanceService attendanceService = new AttendanceService(attendanceDatabase, userDataBase);
            attendanceService.handleAttendance(req);
            resp.sendRedirect("attendance");
        } catch (SQLException | NoSuchUserException | AttendanceAlreadyUpdated | IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
