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
        database.add(new User("Robi", "robi@live.it", Role.STUDENT));
        database.add(new User("Wilson", "wilson@live.it", Role.STUDENT));
        database.add(new User("Anna", "anna@live.it", Role.STUDENT));
        database.add(new User("Jenny", "jenny@live.it", Role.STUDENT));
        database.addText(new Text("Hello World is Python", true));
        database.addText(new Text("Conditional Statements or Selection Statements", false));
        database.addText(new Text("Functions or Methods", false));
        Assignment a = new Assignment("How to create loops?", 10);
        Assignment b = new Assignment("Are you OK?", 12);
        Assignment c = new Assignment("Do you like Pandas?", 20);
        database.addAssignment(a);
        database.addAssignment(b);
        database.addAssignment(c);
        a.publish();
        b.publish();
        c.publish();
        Solution s1 = new Solution(new Assignment("How to create loops?", 10), new User("Lili", "lili@live.it", Role.STUDENT));
        Solution s2 = new Solution(new Assignment("How to create loops?", 10), new User("Robi", "robi@live.it", Role.STUDENT));
        Solution s3 = new Solution(new Assignment("How to create loops?", 10), new User("Jenny", "jenny@live.it", Role.STUDENT));

        Solution s4 = new Solution(new Assignment("Are you OK?", 12), new User("Lili", "lili@live.it", Role.STUDENT));
        Solution s5 = new Solution(new Assignment("Are you OK?", 12), new User("Robi", "robi@live.it", Role.STUDENT));
        Solution s6 = new Solution(new Assignment("Do you like Pandas?", 20), new User("Jenny", "jenny@live.it", Role.STUDENT));
        s4.getAssignment().setAnswear("Actually bla bla bla...");
        s5.getAssignment().setAnswear("Maybe tomorrow?");
        s6.getAssignment().setAnswear("I really don't have a clue..");


        s4.getAssignment().grade(10);
        s5.getAssignment().grade(8);
        s6.getAssignment().grade(7);

        //database.addSolution(s1);
        //database.addSolution(s2);
        //database.addSolution(s3);
        database.addSolution(s4);
        database.addSolution(s5);
        database.addSolution(s6);

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
