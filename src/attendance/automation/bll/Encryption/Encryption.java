/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.Encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hussain
 */
public class Encryption 
{
    private static MessageDigest md;
    
    public static String passwordEncryption(String password)
    {
        try 
        {
            md = MessageDigest.getInstance("MD5");
            byte[] passBytes = password.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            
            for (int i = 0; i < digested.length; i++) 
            {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } 
        catch (NoSuchAlgorithmException ex) 
        {

            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
