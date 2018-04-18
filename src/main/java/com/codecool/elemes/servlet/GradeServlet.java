package com.codecool.elemes.servlet;

import com.codecool.elemes.dao.SolutionDao;
import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.exceptions.NoSuchSolutionException;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.service.ListSolutionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/grade")
public class GradeServlet extends AbstractServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try (Connection connection = getConnection(req.getServletContext())){
            SolutionDatabase solutionDatabase = new SolutionDao(connection);
            ListSolutionService listSolutionService = new ListSolutionService(solutionDatabase);
            Solution solution = listSolutionService.getSolution(id);
            req.setAttribute("solution", solution);
        } catch (NoSuchSolutionException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("grading.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String id = req.getParameter("id");
            Solution solution = null;
        try (Connection connection = getConnection(req.getServletContext())){
            SolutionDatabase solutionDatabase = new SolutionDao(connection);
            ListSolutionService listSolutionService = new ListSolutionService(solutionDatabase);

            solution = listSolutionService.getSolution(id);
            int grade = Integer.parseInt(req.getParameter("grade"));
            solution.getAssignment().grade(grade);
            if (solution.getAssignment().getMaxScore() < grade) {
                throw  new NumberFormatException();
            }
            req.setAttribute("solution", solution);
            req.setAttribute("message", "Grade saved");
        } catch (NoSuchSolutionException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            req.setAttribute("message", "Invalid input");
            req.setAttribute("solution", solution);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("grading.jsp").forward(req, resp);
    }
}
