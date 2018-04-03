package com.codecool.elemes.listener;

import com.codecool.elemes.model.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database database = Database.getInstance();
        database.add(new User("Laci", "laci@gmail.ch", Role.MENTOR));
        database.add(new User("Imi", "imi@gmail.ch", Role.MENTOR));
        database.add(new User("Peter", "peter@gmail.ch", Role.STUDENT));
        database.add(new User("Lili", "lili@live.it", Role.STUDENT));
        database.addText(new Text("Hello World is Python", true));
        database.addText(new Text("Conditional Statements or Selection Statements", false));
        database.addText(new Text("Functions or Methods", false));
        database.addAssignment(new Assignment("How many times a cow poo in a day?", 20));
        database.addAssignment(new Assignment("Do you like programming?", 20));
        ServletContext ctx = sce.getServletContext();
        sce.getServletContext().addFilter("SetCharacterEncodingFilter", "org.apache.catalina.filters.SetCharacterEncodingFilter");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Server shutting down...");

    }
}
