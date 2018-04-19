package com.codecool.elemes.service;

import com.codecool.elemes.dao.AssigmentDatabase;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.model.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentService {
    private AssigmentDatabase database;

    public AssignmentService(AssigmentDatabase database) {
        this.database = database;
    }

    public String getPage(User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            return "assignmentStudent.jsp";
        }
        return "assignmentMentor.jsp";
    }

    public List<Assignment> getAssigments(User user) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        if (user.getRole().equals(Role.STUDENT)) {
            for (Assignment assignment : database.getAllAssignments()) {
                if (assignment.isPublished()) {
                    assignments.add(assignment);
                }
            }
        } else {
            assignments = database.getAllAssignments();
        }
        return assignments;
    }

    public void handlePublish(HttpServletRequest req) throws SQLException {
        String condition;
        for (Assignment assignment :database.getAllAssignments()) {
            if (req.getParameter(assignment.getQuestion())!= null) {
                condition = req.getParameter(assignment.getQuestion());
                if (condition.equals("true")) {
                    assignment.publish();
                    database.update(assignment);
                }
                else {
                    assignment.unPublish();
                    database.update(assignment);
                }
            }
        }
    }

    public Assignment createAssignment(String question, int maxScore) {
        return new Assignment(question, maxScore);

    }
    public void addAssignment(Assignment assignment) throws SQLException {
        database.addAssignment(assignment);
    }
}
