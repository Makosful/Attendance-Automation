/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll;

/**
 *
 * @author B
 */
public class RandomPassword {
    private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    public static String generateRandomPassword(){
        String password = "";
        for(int i = 0; i < 14; i++)
        {
            if(i % 3 == 0)
            {
                password += (int) Math.floor(Math.random() * 100);
                
            }
            else
            {
               password += alphabet[(int)Math.floor(Math.random() * 26)]; 
            }
            
        }
        return password;
    }
}
