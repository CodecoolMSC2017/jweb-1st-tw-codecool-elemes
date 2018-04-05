package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.AttendanceAlreadyUpdated;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.AttendanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet {

    AttendanceService attendanceService = new AttendanceService();
    Database database = Database.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", database.getOnlyStudents(database.getAllUser()));
        req.getAttribute("users");

        req.setAttribute("AllOverAttendance",database.getAttendanceMap());
        for(Map.Entry<Date,List<User>> entry : database.getAttendanceMap().entrySet()){
            System.out.println(entry.getKey()+"  "+entry.getValue().size());
        }
        req.getRequestDispatcher("attendance.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            attendanceService.handleAttendance(req);
        } catch (AttendanceAlreadyUpdated attendanceAlreadyUpdated) {
            attendanceAlreadyUpdated.getMessage();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("attendance");

    }

}
