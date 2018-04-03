package com.codecool.elemes.exceptions;

public class AttendanceAlreadyUpdated extends Exception {


    public AttendanceAlreadyUpdated() {
        super("Attendance is already updated on this date!");
    }
}
