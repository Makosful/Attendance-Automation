/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal;

import attendance.automation.be.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author B
 */
public class UserDAO {
    private DataBaseConnector db;
    
    public UserDAO()
    {
        db = new DataBaseConnector();
    }
    
    public void addNewUser(User user)
    {

        try (Connection con = db.getConnection())
        {
            String sql = "INSERT INTO Users (FirstName, LastName, UserType, UserName, Password, Email) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int i = 1;
            preparedStatement.setString(i++, user.getFirstName());
            preparedStatement.setString(i++, user.getLastName());
            preparedStatement.setBoolean(i++, user.getUserType());
            preparedStatement.setString(i++, user.getUserName());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setString(i++, user.getEmail());
            preparedStatement.executeUpdate();
            
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
