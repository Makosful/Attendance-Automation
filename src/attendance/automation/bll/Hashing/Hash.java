/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll.Hashing;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hussain
 */
public class Hash 
{
    private static MessageDigest md;
    
    public static String passwordEncryption(String password)
    {
        try 
        {
             md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) 
            {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) 
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex)
        {
           ex.getMessage();
        } catch (UnsupportedEncodingException ex) 
        {
            ex.getMessage();
        }
        return null;
    }
}
