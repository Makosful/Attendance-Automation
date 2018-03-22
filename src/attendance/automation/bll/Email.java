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
   
   
   /**
    * Set the email information, which is used in the sendMail method in this class
    * @param recipientAddress
    * @param titleText
    * @param messageText 
    */
   public Email(String recipientAddress, String titleText, String messageText)
   {
        Email.recipientAddress = recipientAddress;
        Email.titleText = titleText;
        Email.messageText = messageText;
   } 
   
   
   /**
    * Sends an email based on the instance variables recipient, title and message
    */
   public static void sendMail() throws AddressException, MessagingException {    

        // Our applications email info - TODO - create new email account at gmail
        String fromUser = "schoolattessndance1@gmail.com";
        String fromPass = "davidmsf2312";


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
 

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromUser));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientAddress));
        message.setSubject(titleText);
        message.setContent(messageText, "text/html");

        Transport.send(message);
       
   }
   
   
   
}