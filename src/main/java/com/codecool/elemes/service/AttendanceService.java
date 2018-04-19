package com.codecool.elemes.service;


import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.AttendanceAlreadyUpdated;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AttendanceService {

    private AttendanceDatabase attendanceDatabase;
    private UserDataBase userDataBase;
    private Map<User, Boolean> todaysAttendance = new HashMap<>();

    public AttendanceService(AttendanceDatabase attendanceDatabase, UserDataBase userDataBase) {
        this.attendanceDatabase = attendanceDatabase;
        this.userDataBase = userDataBase;
    }

    private void prepare(Map<User, Boolean> today, String date) throws AttendanceAlreadyUpdated, SQLException, NoSuchUserException {
        List<User> hereToday = new ArrayList<>();
        if(attendanceDatabase.getAttendanceMap().containsKey(date)){
            List<User> users = attendanceDatabase.getAttendanceMap().get(date);
            for(Map.Entry<User,Boolean> entry:today.entrySet())
                if(entry.getValue() && !users.contains(entry.getKey())){
                    hereToday.add(entry.getKey());
                }
        }else{
            for(Map.Entry<User,Boolean> entry:today.entrySet()){
                if(entry.getValue()){
                    hereToday.add(entry.getKey());
                }
            }
        }
        attendanceDatabase.writeAttendance(date, hereToday);
    }

    public void handleAttendance(String date, HttpServletRequest req) throws AttendanceAlreadyUpdated, ParseException, SQLException, NoSuchUserException {
        List<Boolean> isHere = new ArrayList<>();
        List<User> usersHere = new ArrayList<>();
        String booleanString;
        for (User user : userDataBase.getOnlyStudents()) {
            usersHere.add(user);
            booleanString = req.getParameter(user.geteMail());
            if (booleanString != null) {
                isHere.add(true);

            } else {
                isHere.add(false);
            }
        }
        organizeAttendance(usersHere, isHere);
        prepare(todaysAttendance, date);
    }

    private void organizeAttendance(List<User> users, List<Boolean> bools) {
        for (int i = 0; i < bools.size(); i++) {
            todaysAttendance.put(users.get(i), bools.get(i));
        }
    }

    public Map<User, Boolean> editAttendance(String date) throws SQLException, NoSuchUserException {
        List<User> users = new ArrayList<>();
        List<Boolean> booleans = new ArrayList<>();
        Map<User, Boolean> attendanceMap = new HashMap<>();
        if (!attendanceDatabase.getAttendanceMap().containsKey(date)) {
            for (User user : userDataBase.getOnlyStudents()) {
                users.add(user);
                booleans.add(false);
            }
            for (int i = 0; i < booleans.size(); i++) {
                attendanceMap.put(users.get(i), booleans.get(i));
            }
        } else {
            List<User> editableUsers = attendanceDatabase.getAttendanceMap().get(date);
            for (User user : userDataBase.getOnlyStudents()) {
                if (editableUsers.contains(user)) {
                    users.add(user);
                    booleans.add(true);
                } else {
                    users.add(user);
                    booleans.add(false);
                }
            }
            for (int i = 0; i < booleans.size(); i++) {
                attendanceMap.put(users.get(i), booleans.get(i));
            }
        }
        return attendanceMap;
    }

    public void rewriteAttendance(String date, Map<User, Boolean> edit, HttpServletRequest req) throws SQLException {
        if (edit == null) {
            return;
        }
        List<User> usersHere = new ArrayList<>();
        List<Boolean> isHere = new ArrayList<>();
        List<User> users = new ArrayList<>();
        String booleanString;
        for (Map.Entry<User, Boolean> entry : edit.entrySet()) {
            usersHere.add(entry.getKey());
            booleanString = req.getParameter(entry.getKey().geteMail());
            if (booleanString != null) {
                isHere.add(true);

            } else {
                isHere.add(false);
            }
        }
        for (int i = 0; i < isHere.size(); i++) {
            if (isHere.get(i)) {
                users.add(usersHere.get(i));
            }
        }
        attendanceDatabase.deleteAttendance(date);
        attendanceDatabase.writeAttendance(date, users);
    }

    public String getPage(User user){
        if(user.getRole() == Role.MENTOR){
            return "attendance";
        }
        return "studentAttendance";
    }
}

