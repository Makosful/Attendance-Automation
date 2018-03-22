/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.validation;

/**
 *
 * @author B
 */
public class ValidationFactory {
    
    public enum validationType
    {
        email
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
     
            default: 
                throw new Exception("Unknown validation type submitted to ValidationFactory");
        }
       
    }
    
    
}
