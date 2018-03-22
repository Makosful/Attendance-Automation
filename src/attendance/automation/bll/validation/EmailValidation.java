/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.validation;
import attendance.automation.dal.DALManager;

/**
 *
 * @author B
 */
public class EmailValidation extends AbstractValidation{

    
    DALManager dal = new DALManager();
    
    @Override
    public boolean inputValidation(String input) {
         
        if(input.isEmpty())
        {
            super.validationMessage = "The textfield is empty. Please write your emailaddress";
            return false;
            
        }

        return true;

    }



    
    
}
