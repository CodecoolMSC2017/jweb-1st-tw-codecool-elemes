package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NoSuchUserException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AttendanceDatabase {

    Map<Date,List<User>> getAttendanceMap();


    List<Date> getMissedDays(String eMail) throws NoSuchUserException;

}
