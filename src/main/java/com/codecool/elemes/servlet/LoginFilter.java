package com.codecool.elemes.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig arg) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/login";
        String uri = request.getRequestURI();

        if ("/codecool-lms/login".equals(uri) || "/codecool-lms/register".equals(uri) || ("/codecool-lms/style.css".equals(uri))) {
            chain.doFilter(request, response);
        } else {
            boolean loggedIn = session != null && session.getAttribute("loggedin") != null;
            boolean loginRequest = request.getRequestURI().equals(loginURI);

            if (loggedIn || loginRequest) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(loginURI);
            }
        }

    }

    @Override
    public void destroy() {
    }
}
