package com.codecool.elemes.servlet;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.service.AttendanceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/editAttendance")
public class EditAttendance extends HttpServlet {
    Database database = Database.getInstance();
    AttendanceService attendanceService = new AttendanceService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("AllOverAttendance",database.getAttendanceMap());
        req.getRequestDispatcher("editAttendance.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            req.setAttribute("editAttendanceMap",attendanceService.editAtt(req.getParameter("editableDate")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("editAttendance.jsp").forward(req, resp);
    }
}
