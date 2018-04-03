/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.NotificationMessage;
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
 * @author Hussain
 */
public class NotificationDAO 
{
    DataBaseConnector dbConnector;
    
    public NotificationDAO()
    {
        dbConnector = new DataBaseConnector();
    }
    public List<NotificationMessage> allNotifications(int... classID) throws SQLServerException, SQLException
    {
        List<NotificationMessage> allNotifications;
        
        try(Connection con = dbConnector.getConnection())
        {
            allNotifications = new ArrayList();
            String sql = "SELECT * FROM AttendanceChangeRequest";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                int messageClassID = rs.getInt("ClassID");
                for(int i = 0;i<classID.length;i++)
                {
                    if(messageClassID == classID[i])
                    {
                        int StudentID = rs.getInt("StudentID");
                        String message = rs.getString("Message");
                        NotificationMessage notificationMessage = new NotificationMessage(StudentID, messageClassID, message);
                        allNotifications.add(notificationMessage);
                    }
                }
            }
        }
        return allNotifications;
    }
}
