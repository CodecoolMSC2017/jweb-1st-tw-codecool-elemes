package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AttendanceDatabase {

    Map<Date,List<User>> getAttendanceMap() throws SQLException, NoSuchUserException;

    List<Map<Date,List<User>>> missingStudents() throws SQLException, NoSuchUserException;

    List<Date> getMissedDays(String eMail) throws NoSuchUserException, SQLException;

    void writeAttendance(Date date,List<User> users) throws SQLException;

    void deleteAttendance(Date date) throws SQLException;

}
