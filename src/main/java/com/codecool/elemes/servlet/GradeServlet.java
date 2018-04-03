package com.codecool.elemes.servlet;

import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/grade")
public class GradeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Database database = Database.getInstance();
        try {
            Solution solution = database.getSolution(Integer.parseInt(id));
            req.setAttribute("solution", solution);
        } catch (NoSuchSolutionException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("grading.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Database database = Database.getInstance();
        try {
            Solution solution = database.getSolution(Integer.parseInt(id));
            int grade = Integer.parseInt(req.getParameter("grade"));
            solution.getAssignment().grade(grade);
            req.setAttribute("solution", solution);
            req.setAttribute("message", "Grade saved");
        } catch (NoSuchSolutionException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("grading.jsp").forward(req, resp);
    }
}

