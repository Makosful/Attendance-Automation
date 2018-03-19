/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.Encryption;

import attendance.automation.dal.DataBaseConnector;
import be.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Hussain
 */
public class LogInEncryption 
{
    private DataBaseConnector dbConnection = new DataBaseConnector();
    
    public User userLogIn(String username, String password) throws SQLException
    {
        try(Connection con = dbConnection.getConnection())
        {
            String sql = "SELECT * FROM Users where username = ? and password = ?";
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            User user = new User();
            
            user.setId(rs.getInt("ID"));
            user.setFirstName(rs.getString("Username"));
            user.setLastName(rs.getString("LastName"));
            user.setUserType(rs.getString("UserType"));
            user.setEmail(rs.getString("Email"));
            return user;
        } 
        catch (SQLServerException ex)
        {
            System.out.println("Username or Password is wrong");
        }
        return null;
    }   

}
