/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.ValidationDatabase;

import attendance.automation.dal.DataBaseConnector;
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
public class ValidationDataBase implements IValidationDatabase
{
    DataBaseConnector dbConnector = new DataBaseConnector();
    
    @Override
    public boolean validUsername(String username) 
    {
        try(Connection con = dbConnector.getConnection())
        {
            String sql = "SELECT * FROM Users where LOWER (username) = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username.toLowerCase());
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next())
            {
                return false;
            }
            else
            {
                return true;
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(ValidationDataBase.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return false;
    }

    @Override
    public boolean validEmail(String email) 
    {
        try (Connection con = dbConnector.getConnection()) 
        {
            String sql = "SELECT * FROM Users where email = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) 
            {
                return false;
            }
            else 
            {
                return true;
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(ValidationDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
