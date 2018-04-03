package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.*;

import java.util.*;

public class Database implements UserDataBase, TextDatabase, AssigmentDatabase, SolutionDatabase, AttendanceDatabase {

    private static Database instance = new Database();

    private List<User> users = new ArrayList<>();

    private List<Text> texts = new ArrayList<>();

    private List<Assignment> assignments = new ArrayList<>();

    private List<Solution> solutions = new ArrayList<>();

    private Map<Date,List<User>> rollCallAttendance = new HashMap<>();

    public Database(){}

    public static Database getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUser() {
        return users;
    }

    @Override
    public void deleteUser(String email) throws NoSuchUserException {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.geteMail().equals(email)) {
                users.remove(i);
                return;
            }
        }
        throw new NoSuchUserException();
    }

    @Override
    public User getUser(String email) throws NoSuchUserException {
        for (User user : users) {
            if (user.geteMail().equals(email)) {
                return user;
            }
        }
        throw new NoSuchUserException();
    }

    @Override
    public List<User> getOnlyStudents(List<User> users) {
        List<User> students = new ArrayList<>();
        for (User user:users) {
            if((user.getRole() == Role.STUDENT)){
                students.add(user);
            }
        }
        return students;
    }

    @Override
    public User getUserByEmail(String eMail) throws NoSuchUserException {
        for(User user: getAllUser()){
            if(user.geteMail().equals(eMail)){
                return user;
            }
        }
        throw new NoSuchUserException();
    }


    @Override
    public List<Text> getTexts() {
        return texts;
    }

    @Override
    public void addText(Text text) {
        texts.add(text);
    }

    @Override
    public void deleteText(Text title) throws NoSuchTextException {
        for (Text textInDatabase: texts) {
            if (title.getTitle().equals(textInDatabase.getTitle())) {
                texts.remove(textInDatabase);
                return;
            }
        }
        throw new NoSuchTextException();
    }

    @Override
    public Text getText(int id) throws TextNotFoundException {
        for (Text text: texts) {
            if(text.getId() == id) {
                return text;
            }
        }
        throw new TextNotFoundException();
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignments;
    }

    @Override
    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    @Override
    public void deleteAssignment(Assignment assignment) throws NoSuchAssignmentException {
        for (Assignment assignmentInDatabase: assignments) {
            if (assignment.getQuestion().equals(assignmentInDatabase.getQuestion())) {
                assignments.remove(assignmentInDatabase);
                return;
            }
        }
        throw new NoSuchAssignmentException();
    }

    @Override
    public List<Solution> getAllSolutions() {
        return solutions;
    }

    @Override
    public Solution getSolution(int id) throws NoSuchSolutionException {
        for(Solution solution: solutions) {
            if (solution.getId() == id) {
                return solution;
            }
        }
        throw new NoSuchSolutionException();
    }

    @Override
    public void addSolution(Solution solution) {
        solutions.add(solution);
    }

    @Override
    public Assignment getAssignment(int id) throws NoSuchAssignmentException {
        for (Assignment assignment: assignments) {
            if(assignment.getId()== id) {
                return assignment;
            }
        }
        throw new NoSuchAssignmentException();
    }

    @Override
    public Map<Date, List<User>> getAttendanceMap() {
        return rollCallAttendance;
    }

    @Override
    public List<Date> getMissedDays(String eMail) throws NoSuchUserException {
        List<Date> missedDates = new ArrayList<>();
        for(User user:getOnlyStudents(getAllUser())){
            if(user.geteMail().equals(eMail)){
                for(Map.Entry<Date,List<User>> entry : rollCallAttendance.entrySet()){
                    for(User loggedInUser:entry.getValue()){
                        if(loggedInUser.geteMail().equals(eMail)){
                            missedDates.add(entry.getKey());
                        }
                    }
                }
            }
        }
        return missedDates;
    }

}
