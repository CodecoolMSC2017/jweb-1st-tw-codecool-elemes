package com.codecool.elemes.servlet;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Solution;
import com.codecool.elemes.service.ListSolutionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listsolutions")
public class ListSolutionsServlet extends HttpServlet {
    ListSolutionService listSolutionService = new ListSolutionService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setAttribute("gradedSolutions", listSolutionService.getGradedSolutions());
        req.setAttribute("solutionsToGrade", listSolutionService.getSolutionsToGrade());
        req.getRequestDispatcher("listSolutions.jsp").forward(req,resp);
    }
}
