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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author B
 */
public class TeacherDAO 
{
    private DataBaseConnector dbConnector; 
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
                        int studentID = rs.getInt("StudentID");
                        String message = rs.getString("Message");
                        String studentName = getStudent(studentID);
                        NotificationMessage notificationMessage = new NotificationMessage(studentID, messageClassID, studentName,  message);
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
    
    public String getStudent(int studentId) throws SQLException
    {
        try(Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT DISTINCT FirstName, LastName FROM Users JOIN AttendanceChangeRequest ON AttendanceChangeRequest.StudentID = users.UserID where StudentID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("test");
            rs.next();
            String name = rs.getString("FirstName") + " " + rs.getString("LastName"); 
            return name;
            
        } 
        catch (SQLServerException ex) 
        {
            System.out.println(ex.getMessage());
            throw new SQLException();
        }
    }
}
