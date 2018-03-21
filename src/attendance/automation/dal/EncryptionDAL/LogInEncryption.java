/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.EncryptionDAL;

import attendance.automation.be.Student;
import attendance.automation.be.Teacher;
import attendance.automation.dal.DataBaseConnector;
import attendance.automation.be.User;
import attendance.automation.be.UserFactory;
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
            UserFactory userFactory = new UserFactory();
            User user = null;
            user = userFactory.makeUser(rs.getBoolean("UserType"),
                                        rs.getString("FirstName"), 
                                        rs.getString("LastName"), 
                                        rs.getString("UserName"),
                                        rs.getString("Email"),
                                        rs.getString("Password"));
            user.setId(rs.getInt("UserID"));      
            return user;
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
            throw new SQLException(ex);
        }
    }   

}
