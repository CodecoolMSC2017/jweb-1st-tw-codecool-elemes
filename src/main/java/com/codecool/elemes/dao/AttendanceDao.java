package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.User;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class AttendanceDao extends AbstractDao implements AttendanceDatabase {
    public AttendanceDao(Connection connection) {
        super(connection);
    }
    UserDataBase userDataBase = new UserDao(connection);

    @Override
    public Map<Date, List<User>> getAttendanceMap() throws SQLException, NoSuchUserException {
        Map<Date,List<User>> attendanceMap = new HashMap<>();
        List<User> tempList = new ArrayList<>();
        String sql = "SELECT date,user_email FROM attendance";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Date sqlDate = resultSet.getDate("date");
                Date date = new Date(sqlDate.getTime());
                User user = new UserDao(connection).getUser(resultSet.getString("user_email"));
                if(attendanceMap.containsKey(date)){
                    tempList.clear();
                    tempList.addAll(attendanceMap.get(date));
                    tempList.add(user);
                    attendanceMap.put(date,tempList);
                }
                else{
                    attendanceMap.put(date,Arrays.asList(user));
                }
            }
        }
        return attendanceMap;

    }

    @Override
    public List<Map<Date, List<User>>> missingStudents() throws SQLException, NoSuchUserException {
        List<Map<Date,List<User>>> daysWhereSomeoneIsMissing  = new ArrayList<>();
        for(Map.Entry<Date,List<User>>entry : getAttendanceMap().entrySet()){
            if(entry.getValue().size() != userDataBase.getOnlyStudents().size()){
                daysWhereSomeoneIsMissing.add((Map<Date, List<User>>) entry);
            }
        }
        return daysWhereSomeoneIsMissing;


}

    @Override
    public List<Date> getMissedDays(String eMail) throws NoSuchUserException, SQLException {
        List<Date> missedDates = new ArrayList<>();
        for(User user:userDataBase.getOnlyStudents()){
            if(user.geteMail().equals(eMail)){
                for(Map.Entry<Date,List<User>> entry : getAttendanceMap().entrySet()){
                    for(User loggedInUser:entry.getValue()){
                        if(loggedInUser.geteMail().equals(eMail)){
                            missedDates.add(entry.getKey());
                        }
                    }
                }
            }
        }
        return missedDates;
    }
    @Override
    public void writeAttendance(Date date,List<User> users) throws SQLException {
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "INSERT INTO attendance(date,user_email) VALUES(?,?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            for(User user : users) {
                statement.setDate(1, (sqlDate));
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
    public void deleteAttendance(Date date)throws SQLException{
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "DELETE FROM attendance WHERE date = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setDate(1, (sqlDate));
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
