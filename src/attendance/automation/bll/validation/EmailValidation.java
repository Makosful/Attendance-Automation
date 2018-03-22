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
            super.validationMessage = "Feltet er tomt. Skriv venligt din email adresse ovenover";
            return false;
            
        }
        
        boolean emailInDB = dal.validEmail(input);
        if(emailInDB)
        {
            validationMessage = "The email does not exist for any users";
            return false;
        }

        return true;

    }



    
    
}
