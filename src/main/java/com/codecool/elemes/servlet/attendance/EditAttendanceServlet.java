package com.codecool.elemes.servlet.attendance;

import com.codecool.elemes.dao.impl.AttendanceDao;
import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.impl.UserDao;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
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
import java.text.ParseException;
import java.util.Map;

@WebServlet("/editAttendance")
public class EditAttendanceServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            UserDataBase userDataBase = new UserDao(connection);

            AttendanceService attendanceService = new AttendanceService(attendanceDatabase, userDataBase);
            Map<User,Boolean> editableMap = (Map<User, Boolean>) req.getAttribute("editAttendanceMap");
            String date = (String) req.getSession().getAttribute("defaultDate");
            attendanceService.rewriteAttendance(date,editableMap,req);
            req.getRequestDispatcher("attendance.jsp").forward(req, resp);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }


    }
}
