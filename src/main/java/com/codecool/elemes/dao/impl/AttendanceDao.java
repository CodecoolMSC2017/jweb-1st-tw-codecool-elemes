package com.codecool.elemes.dao.impl;

import com.codecool.elemes.dao.AttendanceDatabase;
import com.codecool.elemes.dao.UserDataBase;
import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class AttendanceDao extends AbstractDao implements AttendanceDatabase {
    public AttendanceDao(Connection connection) {
        super(connection);
    }
    UserDataBase userDataBase = new UserDao(connection);

    @Override
    public Map<String, List<User>> getAttendanceMap() throws SQLException, NoSuchUserException {
        Map<String,List<User>> attendanceMap = new HashMap<>();
        List<User> tempList = new ArrayList<>();
        String sql = "SELECT date,user_email FROM attendance";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                String today = resultSet.getString("date");
                User user = new UserDao(connection).getUser(resultSet.getString("user_email"));
                if(attendanceMap.containsKey(today)){
                    tempList.clear();
                    tempList.addAll(attendanceMap.get(today));
                    tempList.add(user);
                    attendanceMap.put(today,tempList);
                }
                else if (!attendanceMap.containsKey(today) && user == null) {
                    attendanceMap.put(today,new ArrayList<User>());
                }
                else if (attendanceMap.containsKey(today) && user == null) {
                    attendanceMap.put(today, new ArrayList<User>());
                }
                else{
                    attendanceMap.put(today,Arrays.asList(user));
                }
            }
        }
        return attendanceMap;

    }

    @Override
    public List<Map<String, List<User>>> missingStudents() throws SQLException, NoSuchUserException {
        List<Map<String,List<User>>> daysWhereSomeoneIsMissing  = new ArrayList<>();
        for(Map.Entry<String,List<User>>entry : getAttendanceMap().entrySet()){
            if(entry.getValue().size() != userDataBase.getOnlyStudents().size()){
                daysWhereSomeoneIsMissing.add((Map<String, List<User>>) entry);
            }
        }
        return daysWhereSomeoneIsMissing;
}

    @Override
    public List<String> getMissedDays(String eMail) throws NoSuchUserException, SQLException {
        List<String> missedDates = new ArrayList<>();
        String sql = "SELECT date FROM attendance WHERE user_email = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,eMail);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    missedDates.add(resultSet.getString("date"));
                }
            }
        }
        return missedDates;
    }
    @Override
    public void writeAttendance(String date,List<User> users) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO attendance(date,user_email) VALUES(?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            for(User user : users) {
                statement.setString(1, (date));
                statement.setString(2,user.geteMail());
                statement.executeUpdate();
            }
            connection.commit();
        }catch (SQLException ex){
            connection.rollback();
            throw ex;
        }finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    @Override
    public void deleteAttendance(String date)throws SQLException{
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "DELETE FROM attendance WHERE date = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, (date));
                statement.executeUpdate();
                connection.commit();
        }catch (SQLException ex){
            connection.rollback();
            throw ex;
        }finally {
            connection.setAutoCommit(autoCommit);
        }
    }
}
