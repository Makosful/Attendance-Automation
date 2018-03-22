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
public abstract class AbstractValidation implements IValidation{
    
    protected String validationMessage; 

    @Override
    public String getValidationMessage() 
    {
        return validationMessage;
    }
    
}
