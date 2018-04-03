package com.codecool.elemes.service;

import com.codecool.elemes.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AssignmentService {
    private AssigmentDatabase database = Database.getInstance();

    public String getPage(User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            return "assignmentStudent.jsp";
        }
        return "assignmentMentor.jsp";
    }

    public List<Assignment> getAssigments(User user) {
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

    public void handlePublish(HttpServletRequest req) {
        String condition;
        for (Assignment assignment :database.getAllAssignments()) {
            if (req.getParameter(assignment.getQuestion())!= null) {
                condition = req.getParameter(assignment.getQuestion());
                if (condition.equals("true")) {
                    assignment.publish();
                }
                else {
                    assignment.unPublish();
                }
            }
        }
    }

    public Assignment createAssignment(String question, int maxScore) {
        return new Assignment(question, maxScore);

    }
    public void addAssignment(Assignment assignment) {
        database.addAssignment(assignment);
    }
}
