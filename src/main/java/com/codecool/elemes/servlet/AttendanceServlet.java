package com.codecool.elemes.servlet;

import com.codecool.elemes.model.Database;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/attendance")
public class AttendanceServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = Database.getInstance();
        req.setAttribute("users",database.getAllUser());
        req.getRequestDispatcher("attendance.jsp").include(req, resp);
        req.getRequestDispatcher("userpage.jsp").include(req, resp);
    }
}
