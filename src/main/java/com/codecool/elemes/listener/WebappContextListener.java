package com.codecool.elemes.listener;

import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.FileHandle;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database database = Database.getInstance();
        ServletContext ctx = sce.getServletContext();
        sce.getServletContext().addFilter("SetCharacterEncodingFilter", "org.apache.catalina.filters.SetCharacterEncodingFilter");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Server shutting down...");

    }
}
