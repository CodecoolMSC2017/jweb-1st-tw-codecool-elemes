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
    private String pattern = "MM/dd/yyyy";
    private SimpleDateFormat smpl = new SimpleDateFormat(pattern);



    private void checkAttendance(Map<User, Boolean> today,Date date) throws AttendanceAlreadyUpdated {
        Date todayDate = date;
        List<User> hereToday = new ArrayList<>();
        if (!rollCallAttendance.containsKey(todayDate)) {
            today.forEach((key, value) -> {
                if (value) {
                    hereToday.add(key);
                }
            });
        } else {
            throw new AttendanceAlreadyUpdated();
        }
        rollCallAttendance.put(todayDate, hereToday);
        System.out.println(attendanceDatabase.getAttendanceMap().size());
    }
    public void handleAttendance(HttpServletRequest req) throws AttendanceAlreadyUpdated, ParseException {
        List<Boolean> isHere = new ArrayList<>();
        List<User> usersHere = new ArrayList<>();
        String booleanString;
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
        checkAttendance(todaysAttendance,smpl.parse(req.getParameter("attendanceDate")));
    }

    private void organizeAttendance(List<User> users ,List<Boolean> bools){
        for (int i = 0; i < bools.size(); i++) {
            todaysAttendance.put(users.get(i),bools.get(i));
        }
    }

}

