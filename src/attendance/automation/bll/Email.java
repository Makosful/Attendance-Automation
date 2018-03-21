/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance.automation.bll;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class Email {

   private static String titleText;
   private static String messageText;
   private static String recipientAddress;
   
   
   
   public Email(String recipientAddress, String titleText, String messageText)
   {
        Email.recipientAddress = recipientAddress;
        Email.titleText = titleText;
        Email.messageText = messageText;
   } 
   
   
   
   public static void sendMail() {    

        // Our applications email info - TODO - create new email at gmail
        String fromUser = "OUR-NEW-EMAIL-FOR-ATTENDANCE-AUTOMATION@gmail.com";
        String fromPass = "OUR-NEW-EMAIL-PASSWORD-FOR-ATTENDANCE-AUTOMATION";


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.connectiontimeout", "5000");
        props.put("mail.smtp.timeout", "5000");
        

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromUser, fromPass);
            }
        });
 
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromUser));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
            message.setSubject(titleText);
            message.setContent("<h1>"+messageText+"</h1>", "text/html");

            Transport.send(message);
            System.out.println("message sent");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   }
   
   
   
}