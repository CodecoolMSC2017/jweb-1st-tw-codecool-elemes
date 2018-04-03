package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.AttendanceAlreadyUpdated;
import com.codecool.elemes.model.AttendanceDatabase;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.User;
import com.codecool.elemes.model.UserDataBase;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public final class AttendanceService {

    UserDataBase database = Database.getInstance();
    AttendanceDatabase attendanceDatabase = Database.getInstance();
    Map<User, Boolean> todaysAttendance = new HashMap<>();
    Map<Date, List<User>> rollCallAttendance = attendanceDatabase.getAttendanceMap();


    private void checkAttendance(Map<User, Boolean> today) throws AttendanceAlreadyUpdated {
        Date todayDate = new Date();
        List<User> hereToday = new ArrayList<>();
        if (!rollCallAttendance.containsKey(todayDate)) {
            for (Map.Entry<User, Boolean> entry : today.entrySet()) {
                if (entry.getValue() == true) {
                    hereToday.add(entry.getKey());
                }
            }
        } else {
            throw new AttendanceAlreadyUpdated();
        }
        rollCallAttendance.put(todayDate, hereToday);
    }
    public void handleAttendance(HttpServletRequest req) throws AttendanceAlreadyUpdated {
        List<Boolean> isHere = new ArrayList<>();
        List<User> usersHere = new ArrayList<>();
        for(User user: database.getOnlyStudents(database.getAllUser())){
            usersHere.add(user);
            if(req.getParameter(user.geteMail()) != null){
                isHere.add(true);

            }else{
                isHere.add(false);
            }
        }
        organizeAttendance(usersHere,isHere);
        checkAttendance(todaysAttendance);
    }

    private void organizeAttendance(List<User> users ,List<Boolean> bools){
        for (int i = 0; i < bools.size(); i++) {
            todaysAttendance.put(users.get(i),bools.get(i));
        }
    }
}

