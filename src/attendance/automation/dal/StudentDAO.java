/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author B
 */
public class StudentDAO {
    private DataBaseConnector db;
    
    public StudentDAO()
    {
        db = new DataBaseConnector();
    }
    /**
     * Registers whether the student is in school or not.
     * @param userID
     * @param attendance
     * @throws SQLServerException
     * @throws SQLException 
     */
    public void registerAttendance(int userID, boolean attendance) throws SQLServerException, SQLException
    {
       try(Connection con = db.getConnection())
       {
           String sql = "INSERT INTO StudentAttendance VALUES(?, ?, getDate())";         
           PreparedStatement pstmt = con.prepareStatement(sql);           
           pstmt.setInt(1, userID);
           pstmt.setBoolean(2, attendance);
           
           pstmt.executeUpdate();
       }
    }

    
}
