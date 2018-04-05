package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.model.*;

import javax.swing.text.Document;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {
    private Database database = Database.getInstance();

    public Map<User, Double> getSummerizeStudentStatistics() {
        List<User> users = database.getAllUser();
        List<Solution> solutions = database.getAllSolutions();

        Map<User, Double> result = new HashMap<>();

        for (User user:users) {
            if (user.getRole().equals(Role.STUDENT)) {
                Double performance = 0.0;
                Double percentage = 0.0;
                int count = 0;
                for (Solution solution : solutions) {
                    if (solution.getUser().geteMail().equals(user.geteMail())) {
                        try {
                            int grade = 0;
                            grade = solution.getAssignment().getGrade();
                            count++;
                            percentage += grade * 1.0 / solution.getAssignment().getMaxScore();
                        } catch (NotGradedYetException e) {
                        }
                    }
                }
                performance = percentage / count * 100;
                performance = Math.floor(performance * 100) / 100;
                result.put(user, performance);
            }
        }
        return result;
    }

    public Map<String, Double>  getDetailedStudentStatistics(User user) {
        Map<String, Double> result = new HashMap<>();
        int grade;
        for(Solution solution : database.getAllSolutions()) {
            Double percentage = 0.0;
            if (solution.getUser().geteMail().equals(user.geteMail())) {
                try {
                    grade = solution.getAssignment().getGrade();
                    percentage = grade * 100.0 / solution.getAssignment().getMaxScore();
                    percentage = Math.floor(percentage * 100) / 100;
                    result.put(solution.getAssignment().getQuestion(),percentage);
                } catch (NotGradedYetException e) { }
            }
        }
        return result;
    }

    public User getUser(String email) throws NoSuchUserException {
        return database.getUser(email);
    }

    public static void main(String[] args) throws NotGradedYetException, NoSuchUserException {

        StatisticsService s = new StatisticsService();
        Assignment ass = new Assignment("asd", 10);
        Assignment as = new Assignment("asdasd", 12);
        ass.grade(10);
        as.grade(11);
        s.database.add(new User("Lili", "lili@live.it", Role.STUDENT));
        s.database.add(new User("Lili", "lili@live.i", Role.STUDENT));
        Solution solution = new Solution(ass, s.database.getUser("lili@live.it"));
        Solution solution2 = new Solution(as, s.database.getUser("lili@live.i"));
        s.database.addSolution(solution);
        s.database.addSolution(solution2);
        System.out.println(s.getDetailedStudentStatistics(new User("Lili", "lili@live.it", Role.STUDENT)));
        System.out.println(s.getSummerizeStudentStatistics());
    }
}
