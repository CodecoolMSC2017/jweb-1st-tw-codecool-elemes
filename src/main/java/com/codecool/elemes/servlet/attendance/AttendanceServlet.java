package com.codecool.elemes.servlet.attendance;

import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.dao.impl.AttendanceDao;
import com.codecool.elemes.dao.impl.UserDao;
import com.codecool.elemes.exceptions.AttendanceAlreadyUpdated;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.service.AttendanceService;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/attendance")
public class AttendanceServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            UserDataBase userDataBase = new UserDao(connection);
            HttpSession session = req.getSession();
            AttendanceService attendanceService = new AttendanceService(attendanceDatabase, userDataBase);
            req.setAttribute("students", userDataBase.getOnlyStudents());
            if (session.getAttribute("defaultDate") == null) {
                req.setAttribute("defaultDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
            req.setAttribute("editAttendanceMap", attendanceService.editAttendance((String)req.getSession().getAttribute("defaultDate")));

            req.setAttribute("saved","SAVED!");
        } catch (SQLException e) {
            req.setAttribute("error", "error");
        } catch (NoSuchUserException e) {
            req.setAttribute("error", "nincs ilyen nap!");
        } finally {
            try {
                req.getRequestDispatcher("attendance.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (Connection connection = getConnection(req.getServletContext())) {
            AttendanceDatabase attendanceDatabase = new AttendanceDao(connection);
            UserDataBase userDataBase = new UserDao(connection);
            HttpSession session = req.getSession();
            AttendanceService attendanceService = new AttendanceService(attendanceDatabase, userDataBase);
            String date = req.getParameter("attendanceDate");
            attendanceService.handleAttendance(date, req);
            session.setAttribute("defaultDate", req.getParameter("attendanceDate"));
            resp.sendRedirect("attendance");
        } catch (SQLException | NoSuchUserException | AttendanceAlreadyUpdated | IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
