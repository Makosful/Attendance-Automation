/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.validation;
    

public class PassValidation extends AbstractValidation{

    @Override
    public boolean inputValidation(String pass) {
        if (pass.length() < 8)
        {
            super.validationMessage = "Password must be at least 8 characters long";
            return false;
        }
        else if (!haveUpperCase(pass))
        {
            super.validationMessage = "Password must have at least 1 upper case";
            return false;
        }
        else if (!haveLowerCase(pass))
        {
            super.validationMessage = "Password must have at least 1 lower case";
            return false;
        }
        else if (!haveNumber(pass))
        {
            super.validationMessage = "Password must have at least 1 number";
            return false;
        }
        
        return true;
    }

    /**
     * Checks if the given oassword contains any uppercase letters
     *
     * @param password
     *
     * @return
     */
    private boolean haveUpperCase(String password)
    {
        char[] pass = password.toCharArray();
        for (char p : pass)
        {
            if (Character.isUpperCase(p))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks of the given password contains any lowercase letters
     *
     * @param password
     *
     * @return
     */
    private boolean haveLowerCase(String password)
    {
        char[] pass = password.toCharArray();
        for (char p : pass)
        {
            if (Character.isLowerCase(p))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * checks is a password contains any numbers
     *
     * @param password
     *
     * @return
     */
    private boolean haveNumber(String password)
    {
        char[] pass = password.toCharArray();
        for (char p : pass)
        {
            if (Character.isDigit(p))
            {
                return true;
            }
        }
        return false;
    }
    
}
