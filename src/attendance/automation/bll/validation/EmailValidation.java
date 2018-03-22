/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.validation;
import attendance.automation.dal.DALManager;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author B
 */
public class EmailValidation extends AbstractValidation{

    
    DALManager dal = new DALManager();
    
    @Override
    public boolean inputValidation(String mail) {
         
        if(mail.isEmpty())
        {
            super.validationMessage = "The textfield is empty. Please write your emailaddress";
            return false;
            
        }
        
        Pattern p = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                                    
        Matcher m = p.matcher(mail);
        if(!m.find())
        {
            super.validationMessage = "Emailaddress is not valid";
            return false;
        }
        
        
        return true;

    }



    
    
}
