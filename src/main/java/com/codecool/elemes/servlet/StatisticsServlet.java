package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.dao.impl.SolutionDao;
import com.codecool.elemes.dao.impl.UserDao;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import com.codecool.elemes.service.StatisticsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/statistics")
public class StatisticsServlet extends AbstractServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loggedin");

        try(Connection connection = getConnection(req.getServletContext())) {
            UserDataBase ud = new UserDao(connection);
            SolutionDatabase sd = new SolutionDao(connection);
            StatisticsService statisticsService = new StatisticsService(ud, sd);

        try {
            User user1 = statisticsService.getUser(req.getParameter("email"));
            user = user1;
        } catch (NoSuchUserException e) {
        }
        if (user.getRole().equals(Role.MENTOR)) {
            req.setAttribute("stats", statisticsService.getSummerizeStudentStatistics());
            req.getRequestDispatcher("statistics.jsp").forward(req,resp);
        }else {
            req.setAttribute("stats", statisticsService.getDetailedStudentStatistics(user));
            Map<User,Double> stat = statisticsService.getSummerizeStudentStatistics();
            req.setAttribute("summary",stat.get(user));
            req.getRequestDispatcher("studentstatistics.jsp").forward(req,resp);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
