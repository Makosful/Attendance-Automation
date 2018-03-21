/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.dal.ValidationDatabase;

/**
 *
 * @author Hussain
 */
public interface IValidationDatabase 
{
    /**
     * Checking if the username is valid in db.
     * @param username
     * @return 
     */
    public boolean validUsername(String username);
    /**
     * Checking if the email is valid in db.
     * @param email
     * @return 
     */
    public boolean validEmail(String email);
    
}
