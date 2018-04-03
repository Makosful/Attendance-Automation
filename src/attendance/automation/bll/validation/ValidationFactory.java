/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.validation;

import static attendance.automation.bll.validation.ValidationFactory.validationType.firstAndLastName;

/**
 *
 * @author B
 */
public class ValidationFactory {
    
    public enum validationType
    {
        email, password, username, firstAndLastName
    }
    
    /**
     * Creates new validation objects
     * @param validationType
     * @return
     * @throws Exception 
     */
    public static IValidation createValidation(validationType validationType) throws Exception
    {
        switch(validationType)
        {
            case email:
                 return new EmailValidation();
            case password:
                return new PassValidation();
            case username:
                return new UsernameValidation();
            case firstAndLastName:
                return new firstAndLastName();
                
            default: 
                throw new Exception("Unknown validation type submitted to ValidationFactory");
        }
       
    }
    
    
}
