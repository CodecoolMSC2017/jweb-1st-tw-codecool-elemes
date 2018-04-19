package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AttendanceDatabase {

    Map<String,List<User>> getAttendanceMap() throws SQLException, NoSuchUserException;

    List<Map<String,List<User>>> missingStudents() throws SQLException, NoSuchUserException;

    List<String> getMissedDays(String eMail) throws NoSuchUserException, SQLException;

    void writeAttendance(String date,List<User> users) throws SQLException;

    void deleteAttendance(String date) throws SQLException;
}
