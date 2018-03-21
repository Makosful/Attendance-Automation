/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.be;

/**
 *
 * @author Hussain
 */
public class UserFactory 
{
    public static User makeUser(boolean userType, String firstName, String lastName, String userName, String email, String passWord)
    {
        if(userType)
        {
            return new Teacher(userType, firstName, lastName, userName, email, passWord);
        }
        else
        {
            return new Student(userType, firstName, lastName, userName, email, passWord);
        }
    }
}
