/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.NotificationMessage;
import attendance.automation.be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author B
 */
public class TeacherDAO 
{
    private final DataBaseConnector dbConnector; 
    private List<Integer> classIds;
    public TeacherDAO()
    {
        dbConnector = new DataBaseConnector();
    }
    
    
    public List<NotificationMessage> allNotifications() throws SQLException 
    {
        List<NotificationMessage> allNotifications;

        try (Connection con = dbConnector.getConnection()) 
        {
            allNotifications = new ArrayList();
            String sql = "SELECT * FROM AttendanceChangeRequest";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) 
            {
                int messageClassID = rs.getInt("ClassID");
                for (int i = 0; i < classIds.size(); i++)
                {
                    if (messageClassID == classIds.get(i)) 
                    {
                        int studentId = rs.getInt("StudentID");
                        String message = rs.getString("Message");
                        String studentName = getStudentName(studentId);
                        String className = getStudentClassName(messageClassID);
                        Date date = rs.getDate("Date");
                        NotificationMessage notificationMessage = new NotificationMessage(studentId, className, studentName,  message, date);
                        allNotifications.add(notificationMessage);
                    }
                }
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
            throw new SQLException();
        }
        return allNotifications;
    }
    /**
     * Gets the current user, and returns a list with his classIDs
     * @param user
     * @throws SQLException 
     */
    public void getUser(User user) throws SQLException
    {
        classIds = new ArrayList();
        try(Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT ClassID From UserClass where UserID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                classIds.add(rs.getInt("ClassID"));
                System.out.println(rs.getInt("ClassID"));
            }
        } 
        catch (SQLServerException ex) 
        {
            System.out.println(ex.getMessage());
            throw new SQLException();
        }
    }
    /**
     * Returns teacher list with classes.
     * @return 
     */
    public List<Integer> getClassIdList()
    {
        return classIds;
    }
    
    public String getStudentName(int studentId) throws SQLException
    {
        try(Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT DISTINCT FirstName, LastName from Users where UserID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return  rs.getString("FirstName") + " " + rs.getString("LastName");    
        } 
        catch (SQLServerException ex) 
        {
            System.out.println(ex.getMessage());
            throw new SQLException();
        }
    }
    
    public String getStudentClassName(int studentId) throws SQLException
    {
         try(Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT ClassName from Classes where ClassID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getString("ClassName");
        } catch (SQLException ex) 
        {
            throw new SQLException(ex.getMessage(), ex);
        }
    }
    
    public void changeStudentAttendance(Date date, int classID, int userID) throws SQLException
    {
        try(Connection  con = dbConnector.getConnection())
        {
            String sql = "Update StudentAttendance set Attended = true where Date = ?, and ClassID = ?, and UserID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDate(1, date);
            pstmt.setInt(2, classID);
            pstmt.setInt(3, userID);
            pstmt.executeUpdate();
        }
        catch (SQLException ex) 
        {
            throw new SQLException(ex.getMessage(), ex);
        }
    }
}
