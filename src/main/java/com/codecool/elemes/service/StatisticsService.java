package com.codecool.elemes.service;

import com.codecool.elemes.dao.SolutionDatabase;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.exceptions.NotGradedYetException;
import com.codecool.elemes.model.*;

import javax.swing.text.Document;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsService {
    private UserDataBase ud;
    private SolutionDatabase sd;

    public StatisticsService(UserDataBase ud, SolutionDatabase sd) {
        this.ud = ud;
        this.sd = sd;
    }

    public Map<User, Double> getSummerizeStudentStatistics() throws SQLException {
        List<User> users = ud.getAllUser();
        List<Solution> solutions = sd.getAllSolutions();

        Map<User, Double> result = new HashMap<>();

        for (User user : users) {
            if (user.getRole().equals(Role.STUDENT)) {
                Double performance = 0.0;
                Double percentage = 0.0;
                int count = 0;
                for (Solution solution : solutions) {
                    if (solution.getUser().geteMail().equals(user.geteMail())) {
                        int grade = 0;
                        grade = solution.getResult();
                        count++;
                        percentage += grade * 1.0 / solution.getAssignment().getMaxScore();

                    }
                }
                performance = percentage / count * 100;
                performance = Math.floor(performance * 100) / 100;
                if (percentage == 0.0) {
                    performance = null;
                }
                result.put(user, performance);
            }
        }
        return result;
    }

    public Map<String, Double> getDetailedStudentStatistics(User user) throws SQLException {
        Map<String, Double> result = new HashMap<>();
        int grade;
        for (Solution solution : sd.getAllSolutions()) {
            Double percentage = 0.0;
            if (solution.getUser().geteMail().equals(user.geteMail())) {
                grade = solution.getResult();
                percentage = grade * 100.0 / solution.getAssignment().getMaxScore();
                percentage = Math.floor(percentage * 100) / 100;
                result.put(solution.getAssignment().getQuestion(), percentage);
            }
        }
        return result;
    }

    public User getUser(String email) throws NoSuchUserException, SQLException {
        return ud.getUser(email);
    }
    
}
