package com.codecool.elemes.servlet;

import com.codecool.elemes.service.ContentShowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/content")
public class ShowContent extends HttpServlet {
    private ContentShowService contentShowService = new ContentShowService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String content = contentShowService.getContent(id);
        System.out.println(content);
        req.setAttribute("content",content);
        req.getRequestDispatcher("app/content.jsp").forward(req, resp);
    }
}
