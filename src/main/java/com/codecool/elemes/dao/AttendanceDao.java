package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class AttendanceDao implements AttendanceDatabase {
    @Override
    public Map<Date, List<User>> getAttendanceMap() {
        return null;
    }

    @Override
    public List<Map<Date, List<User>>> missingStudents() throws Exception {
        return null;
    }

    @Override
    public List<Date> getMissedDays(String eMail) throws NoSuchUserException {
        return null;
    }
}
