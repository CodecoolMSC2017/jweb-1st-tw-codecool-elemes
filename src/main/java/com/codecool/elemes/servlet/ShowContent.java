package com.codecool.elemes.servlet;

import com.codecool.elemes.service.ContentShowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/content")
public class ShowContent extends HttpServlet {
    private ContentShowService contentShowService = new ContentShowService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String content = contentShowService.getContent(id);
        String[] asd;
        asd = content.split("\n");
        req.setAttribute("content",asd);
        req.getRequestDispatcher("content.jsp").include(req, resp);
        req.getRequestDispatcher("userpage.jsp").include(req, resp);
    }
}
