/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author B
 */
public class StoreLocalLogin {
    
    
    /**
     * Writes the login credentials to a txt file locally,
     * to be used next time the user wants to login
     * @param userName
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException 
     */
    public static void setLoginInfo(String userName, String password) throws IOException, NoSuchAlgorithmException
    {
        password = hashPass(password);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("loginInfo.txt")))
        {
            writer.write(userName);
            writer.newLine();
            writer.write(password);
        }
    }

    
    
    /**
     * Get the login credentials for the user stored on this local machine
     * @return String array of login info - username and password
     * @throws FileNotFoundException 
     */
    public static String[] getLoginInfo() throws FileNotFoundException
    {
        File f = new File("loginInfo.txt");
        Scanner sc = new Scanner(new BufferedReader(new FileReader(f)));
        
        String userName = null;
        String password = null;
        
        if(sc.hasNext())
        {
            userName = sc.nextLine();
        }
        if(sc.hasNext())
        {
            password = sc.nextLine();
        }
        
        String[] loginInfo = new String[2];
        loginInfo[0] = userName;
        loginInfo[1] = password;
        return loginInfo;
        
    }
    
   
    
    /**
     * One way function/hashing of password
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    public static String hashPass(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException 
    {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hash = digest.digest(password.getBytes("UTF-8"));

        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++)
        {

            String hex = Integer.toHexString(0xff & hash[i]);

            if(hex.length() == 1) hexString.append('0');

            hexString.append(hex);

        }

        return hexString.toString();
        
    }
}
