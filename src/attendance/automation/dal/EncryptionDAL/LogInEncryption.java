/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.EncryptionDAL;

import attendance.automation.dal.DataBaseConnector;
import be.User;
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
            String sql = "SELECT * FROM Users where LOWER(UserName) = ? AND Password = ?";
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username.toLowerCase());
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            User user = new User();
            
            user.setId(rs.getInt("UserID"));
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            user.setUserType(rs.getBoolean("UserType"));
            user.setEmail(rs.getString("Email"));
            user.setUsername(rs.getString("UserName"));   
            return user;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
//        return null;
    }   

}
