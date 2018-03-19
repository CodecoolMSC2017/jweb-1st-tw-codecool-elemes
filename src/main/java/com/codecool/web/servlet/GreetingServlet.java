package com.codecool.web.servlet;

import com.codecool.web.model.Greeting;
import com.codecool.web.service.GreetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/greeting")
public class GreetingServlet extends HttpServlet {

    private final GreetingService service = new GreetingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Greeting greeting = service.getGreeting();

        req.setAttribute("greeting", greeting);

        req.getRequestDispatcher("greeting.jsp").forward(req, resp);
    }
}
