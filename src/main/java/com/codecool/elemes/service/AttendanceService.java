package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.AttendanceAlreadyUpdated;
import com.codecool.elemes.model.AttendanceDatabase;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.User;
import com.codecool.elemes.model.UserDataBase;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class AttendanceService {

    private UserDataBase database = Database.getInstance();
    private AttendanceDatabase attendanceDatabase = Database.getInstance();
    private Map<User, Boolean> todaysAttendance = new HashMap<>();
    private Map<Date, List<User>> rollCallAttendance = attendanceDatabase.getAttendanceMap();

    private void checkAttendance(Map<User, Boolean> today,Date date) throws AttendanceAlreadyUpdated {
        List<User> hereToday = new ArrayList<>();
        if (!rollCallAttendance.containsKey(date)) {
            today.forEach((key, value) -> {
                if (value) {
                    hereToday.add(key);
                }
            });
        } else {
            throw new AttendanceAlreadyUpdated();
        }
        rollCallAttendance.put(date, hereToday);
    }
    public void handleAttendance(HttpServletRequest req) throws AttendanceAlreadyUpdated, ParseException {
        List<Boolean> isHere = new ArrayList<>();
        List<User> usersHere = new ArrayList<>();
        String booleanString;
        String date = req.getParameter("attendanceDate");
        Date formattedDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        for(User user: database.getOnlyStudents(database.getAllUser())){
            usersHere.add(user);
            booleanString = req.getParameter(user.geteMail());
            if(booleanString != null){
                isHere.add(true);

            }else{
                isHere.add(false);
            }
        }
        organizeAttendance(usersHere,isHere);
        checkAttendance(todaysAttendance, formattedDate);
    }

    private void organizeAttendance(List<User> users ,List<Boolean> bools){
        for (int i = 0; i < bools.size(); i++) {
            todaysAttendance.put(users.get(i),bools.get(i));
        }
    }

    public Map<User,Boolean> editAtt(String stringDate) throws Exception {
        List<User> users = new ArrayList<>();
        List<Boolean> booleans = new ArrayList<>();
        Map<User,Boolean> attendanceMap = new HashMap<>();
        Date date = new SimpleDateFormat("MM/dd/yyyy").parse(stringDate);
        if(!rollCallAttendance.containsKey(date)){
            throw new Exception();
        }
        List<User> editableUsers = rollCallAttendance.get(date);
        for(User user : database.getOnlyStudents(database.getAllUser())){
           if(editableUsers.contains(user)){
               users.add(user);
               booleans.add(true);
           }else{
               users.add(user);
               booleans.add(false);
           }
        }
        for (int i = 0; i <booleans.size() ; i++) {
            attendanceMap.put(users.get(i),booleans.get(i));
        }
        return attendanceMap;
    }
    public void rewriteAttendance(HttpServletRequest req) throws ParseException {
        List<User> usersHere = new ArrayList<>();
        List<Boolean> isHere = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Date formattedDate = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("editableDate"));
        String booleanString;
        Map<User,Boolean> map = (Map<User, Boolean>) req.getAttribute("editAttendanceMap");
        for(Map.Entry<User,Boolean> entry: map.entrySet()){
            usersHere.add(entry.getKey());
            booleanString = req.getParameter(entry.getKey().geteMail());
            if(booleanString != null){
                isHere.add(true);

            }else{
                isHere.add(false);
            }
        }
        for (int i = 0; i <isHere.size() ; i++) {
            if(isHere.get(i)){
                users.add(usersHere.get(i));
            }
        }
        attendanceDatabase.getAttendanceMap().put(formattedDate,users);

    }

}

