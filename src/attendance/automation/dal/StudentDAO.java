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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author B
 */
public class StudentDAO {

    private DataBaseConnector db;

    public StudentDAO() {
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
    public void registerAttendance(int userID, boolean attendance) throws SQLServerException, SQLException {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO StudentAttendance VALUES(?, ?, getDate())";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.setBoolean(2, attendance);

            pstmt.executeUpdate();
        }
    }

    public ArrayList<Student> getAllStudents() throws SQLException {
        try (Connection con = db.getConnection()) {
            ArrayList<Student> students = new ArrayList();

            String sql = "SELECT * FROM Users WHERE UserType = 1";
            ResultSet rs = con.createStatement().executeQuery(sql);

            while (rs.next()) {
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

    public void sendAttendanceChange(int studentID, int classID, String message, Date date) throws SQLServerException, SQLException {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO AttendanceChangeRequest VALUES(?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studentID);
            pstmt.setInt(2, classID);
            pstmt.setString(3, message);
            pstmt.setDate(4, new java.sql.Date(date.getTime()));
            pstmt.executeUpdate();
        }
    }

    public void changeStudentAttendance() {

    }

    public ArrayList<Boolean> registerAverageAttendance(int userID) throws SQLServerException, SQLException {
        ArrayList<Boolean> avgList = new ArrayList<>();

        try (Connection con = db.getConnection()) {

            String sql = "SELECT * FROM StudentAttendance WHERE UserID = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userID);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                avgList.add(rs.getBoolean("Attended"));
            }
        } catch (Exception e) {
        }
        return avgList;
    }

    public ArrayList<Integer> getStudentAttendance(User user) throws SQLException {
        ArrayList<Integer> attendance = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            int i = 1;
            String sql = "SELECT * FROM StudentAttendance WHERE UserID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(i++, user.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int att = rs.getInt("Attended");
                attendance.add(att);
            }
        }
        return attendance;
    }

    public ArrayList<Boolean> attendanceTimeFrame(LocalDate from, LocalDate to, User user)
            throws SQLException {
        ArrayList<Boolean> att = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            int i = 1;
            String sql = "SELECT * FROM StudentAttendance WHERE UserID = ? AND Date BETWEEN ? and ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(i++, user.getId());
            stmt.setDate(i++, java.sql.Date.valueOf(from));
            stmt.setDate(i++, java.sql.Date.valueOf(to));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                boolean bool = rs.getBoolean("Attended");
                att.add(bool);
            }
        }
        return att;
    }
}
