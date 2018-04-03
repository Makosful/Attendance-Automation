/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author B
 */
public class UsernameValidation extends AbstractValidation{


    @Override
    public boolean inputValidation(String username) {
        
        Pattern p = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
                                    
        Matcher m = p.matcher(username);
        if(!m.find())
        {
            super.validationMessage = "Username is not valid";
            return false;
        }
        
        
        return true;
    }


    
}
