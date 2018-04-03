/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.Student;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                students.add(new Student(
                        rs.getBoolean("UserType"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("UserName"),
                        rs.getString("Email"),
                        rs.getString("Password")
                ));
            }
            return students;
        }
    }
    
    public void sendAttendanceChange(int studentID, int classID, String message, Date date) throws SQLServerException, SQLException
    {
        try(Connection con = db.getConnection())
        {
            String sql = "INSERT INTO AttendanceChangeRequest VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentID);
            pstmt.setInt(2, classID);
            pstmt.setString(3, message);
            pstmt.setDate(4, new java.sql.Date(date.getTime()));
            pstmt.executeUpdate();
        }
    }
    
    public void changeStudentAttendance()
    {
        
    }

}
