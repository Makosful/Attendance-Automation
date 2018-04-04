/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.UserLogIn;

import attendance.automation.dal.DataBaseConnector;
import attendance.automation.be.User;
import attendance.automation.dal.UserFactory.UserFactory;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hussain
 */
public class UserLogIn 
{
    private final DataBaseConnector dbConnection = new DataBaseConnector();
    
    public User userLogIn(String username, String password) throws SQLException 
    {
        try(Connection con = dbConnection.getConnection())
        {
            String sql = "SELECT * FROM Users where LOWER(UserName) = ? AND Password = ?";
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username.toLowerCase());
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            User user = null;
            user = UserFactory.makeUser(rs.getBoolean("UserType"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("UserName"),
                    rs.getString("Email"),
                    rs.getString("Password"));
            user.setId(rs.getInt("UserID"));  
            addClasses(user);
            return user;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
    }
    /**
     * Gets all the class names for the specific student.
     * @throws SQLException 
     */
    public void addClasses(User user) throws SQLException
    {
        try(Connection con = dbConnection.getConnection())
        {
            String sql = "SELECT ClassName FROM Classes"
                    + " JOIN UserClass ON UserClass.ClassID = Classes.ClassID"
                    + " JOIN Users ON users.UserID = UserClass.UserID \n" 
                    +"WHERE Users.UserID = ?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                user.setClass(rs.getString("ClassName"));
            }
        } 
        catch (SQLServerException ex) 
        {
            Logger.getLogger(UserLogIn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
