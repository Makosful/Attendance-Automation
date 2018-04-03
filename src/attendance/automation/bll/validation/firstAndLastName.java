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
public class firstAndLastName extends AbstractValidation {


    @Override
    public boolean inputValidation(String input) {
               
        Pattern p = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
                                    
        Matcher m = p.matcher(input);
        if(!m.find())
        {
            super.validationMessage = "Name is not valid";
            return false;
        }
        
        
        return true;
    }
    
}
