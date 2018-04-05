/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Student;
import attendance.automation.be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author B
 */
public class StudentDAO
{

    private DataBaseConnector db;

    public StudentDAO()
    {
        db = new DataBaseConnector();
    }

    /**
     * Registers whether the student is in school or not.
     *
     * @param userID
     * @param attendance
     *
     * @throws SQLServerException
     * @throws SQLException
     */
    public void registerAttendance(int userID, boolean attendance) throws SQLServerException, SQLException
    {
        try (Connection con = db.getConnection())   
        {
            String sql = "INSERT INTO StudentAttendance VALUES(?, ?, getDate())";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.setBoolean(2, attendance);

            pstmt.executeUpdate();
        }
    }

    public ArrayList<Student> getAllStudents() throws SQLException
    {
        try (Connection con = db.getConnection())
        {
            ArrayList<Student> students = new ArrayList();

            String sql = "SELECT * FROM Users WHERE UserType = 1";
            ResultSet rs = con.createStatement().executeQuery(sql);

            while (rs.next())
            {
                Student s = new Student(
                        rs.getBoolean("UserType"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("UserName"),
                        rs.getString("Email"),
                        rs.getString("Password"));
                s.setId(rs.getInt("UserId"));
                students.add(s);
            }
            return students;
        }
    }

    
    /**
     * Store the request for changing the attendance in the db
     * @param studentID
     * @param classID
     * @param message
     * @throws SQLException 
     */
    public void sendAttendanceChange(int studentID, int classID, String message, LocalDate date) throws SQLException 
    {
        try (Connection con = db.getConnection())
        {

            String sql = "INSERT INTO AttendanceChangeRequest VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentID);
            pstmt.setInt(2, classID);
            pstmt.setDate(3, java.sql.Date.valueOf(date));
            pstmt.setString(4, message);
            pstmt.execute();
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        } 
       
    }

    public void changeStudentAttendance()
    {

    }

    public ArrayList<Boolean> registerAverageAttendance(int userID) throws SQLServerException, SQLException
    {
        ArrayList<Boolean> avgList = new ArrayList<>();

        try (Connection con = db.getConnection())
        {

            String sql = "SELECT * FROM StudentAttendance WHERE UserID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                avgList.add(rs.getBoolean("Attended"));
            }
        }
        catch (Exception e)
        {
        }
        return avgList;
    }

    public ArrayList<Integer> getStudentAttendance(User user) throws SQLException
    {
        ArrayList<Integer> attendance = new ArrayList<>();
        try (Connection con = db.getConnection())
        {
            int i = 1;
            String sql = "SELECT * FROM StudentAttendance WHERE UserID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(i++, user.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                int att = rs.getInt("Attended");
                attendance.add(att);
            }
        }
        return attendance;
    }

    public ArrayList<Boolean> attendanceTimeFrame(LocalDate from, LocalDate to, int id)
            throws SQLException
    {
        ArrayList<Boolean> att = new ArrayList<>();
        try (Connection con = db.getConnection())
        {
            int i = 1;
            String sql = "SELECT * FROM StudentAttendance WHERE UserID = ? AND Date BETWEEN ? and ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(i++, id);
            stmt.setDate(i++, java.sql.Date.valueOf(from));
            stmt.setDate(i++, java.sql.Date.valueOf(to));
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                boolean bool = rs.getBoolean("Attended");
                att.add(bool);
            }
        }
        return att;
    }



    public ArrayList<Integer> lookUpClassIdsFromClassNames(List<String> chosenCalsses, String classes) throws SQLServerException, SQLException {
        ArrayList<Integer> ids = new ArrayList();
        try (Connection con = db.getConnection())
        {
            int i = 1;
            String sql = "SELECT ClassID FROM Classes WHERE "+classes;
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            for(String subject : chosenCalsses){
                stmt.setString(i++, subject);
                
            }
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                ids.add(rs.getInt("ClassID"));
            }
        }
        return ids;
    }
 
    public HashMap<String, Integer> attendanceClassStatistics(int StudentId, List<String> chosenCalsses, String sqlChosenClasses) throws SQLException
    {
        
        HashMap<String, Integer> classes = new HashMap();
        try (Connection con = db.getConnection())
        {
       
            String sql = "SELECT Classes.ClassName, 100*P.Present/T.Total AS Procent " 
                       + "FROM " 
                       + "(SELECT ClassID, COUNT(attended) AS Total " 
                       + "FROM StudentAttendance " 
                       + "WHERE UserID = ? " 
                       + "GROUP BY ClassID) T " 
                       + "LEFT JOIN " 
                       + "(SELECT ClassID, COUNT(attended) AS Present " 
                       + "FROM StudentAttendance " 
                       + "WHERE Attended = 'true' AND  UserID = ? " 
                       + "GROUP BY ClassID) P " 
                       + "ON T.ClassID = P.ClassID "
                       + "INNER JOIN Classes ON T.ClassID = Classes.ClassID";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            int i = 1;
            stmt.setInt(i++, StudentId);  
            stmt.setInt(i++, StudentId); 
            
            for(String subject : chosenCalsses){
                stmt.setString(i++, subject);    
            }
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                classes.put(rs.getString("ClassName"), rs.getInt("Procent"));
                classes.put(rs.getString("ClassName"), rs.getInt("Procent"));
            }
        } catch (SQLServerException ex) {
            throw new SQLException(ex.getMessage());
        }
        return classes;
        
    }
    
}
