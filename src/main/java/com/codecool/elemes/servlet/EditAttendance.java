package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.AttendanceDao;
import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.UserDao;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.AttendanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

@WebServlet("/editAttendance")
public class EditAttendance extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            req.setAttribute("AllOverAttendance", attendanceDatabase.getAttendanceMap());
            req.getRequestDispatcher("editAttendance.jsp").forward(req, resp);
        } catch (SQLException | ServletException | NoSuchUserException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            UserDataBase userDataBase = new UserDao(connection);

            AttendanceService attendanceService = new AttendanceService(attendanceDatabase, userDataBase);
            req.setAttribute("editAttendanceMap", attendanceService.editAttendance(req.getParameter("editableDate")));
            req.setAttribute("defaultDate", req.getParameter("editableDate"));
            String date = req.getParameter("editableDate");
            Map<User,Boolean> editableMap = (Map<User, Boolean>) req.getAttribute("editAttendanceMap");
            attendanceService.rewriteAttendance(date,editableMap,req);
            req.getRequestDispatcher("editAttendance.jsp").forward(req, resp);
        } catch (SQLException | IOException | ServletException | ParseException | NoSuchUserException e) {
            e.printStackTrace();
        }


    }
}
