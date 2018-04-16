package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AttendanceDatabase {

    Map<Date,List<User>> getAttendanceMap();

    List<Map<Date,List<User>>> missingStudents() throws Exception;

    List<Date> getMissedDays(String eMail) throws NoSuchUserException;

}
