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
    public boolean validUsername(String username);
    
    public boolean validEmail(String email);
    
}
