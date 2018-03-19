/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author B
 */
public class StoreLocalLogin {
    
    public static void storeLoginInfo() throws IOException
    {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt")))
        {
            writer.write("UsernameAndPassword");
        }
    }
}
