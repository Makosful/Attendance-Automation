/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.Encryption;

import attendance.automation.bll.Encryption.Encryption;
import attendance.automation.dal.DataBaseConnector;
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
public class LogInEncryption 
{
    private DataBaseConnector dbConnection = new DataBaseConnector();
    
    public void userLogIn(String username, String password) throws SQLException
    {
        try(Connection con = dbConnection.getConnection())
        {
            String encryptedPassword = Encryption.passwordEncryption(password);
            String sql = "ToBeMade, ?";
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next())
            {
                if(rs.getString("Password").equals(encryptedPassword))
                {
                    System.out.println("SUCESS LOGIN");
                }

            }
            
            
        } 
        catch (SQLServerException ex)
        {
            Logger.getLogger(LogInEncryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

}
