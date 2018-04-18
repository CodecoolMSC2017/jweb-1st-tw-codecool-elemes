package com.codecool.elemes.servlet.solution;

import com.codecool.elemes.dao.impl.SolutionDao;
import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.service.ListSolutionService;
import com.codecool.elemes.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/listsolutions")
public class ListSolutionsServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        try(Connection connection = getConnection(req.getServletContext())) {
            SolutionDatabase solutionDatabase = new SolutionDao(connection);
            ListSolutionService listSolutionService = new ListSolutionService(solutionDatabase);
            req.setAttribute("gradedSolutions", listSolutionService.getGradedSolutions(id));
            req.setAttribute("solutionsToGrade", listSolutionService.getSolutionsToGrade(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("listSolutions.jsp").forward(req,resp);
    }
}
