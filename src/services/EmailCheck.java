package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import models.Mail;

public class EmailCheck {

   public static List<Mail> check(String host, String storeType, String user,
      String password) 
   {
	  List<Mail> mails = new ArrayList<Mail>();
      try {

      //create properties field
      Properties properties = new Properties();

      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", "995");
      properties.put("mail.pop3.starttls.enable", "true");
      Session emailSession = Session.getDefaultInstance(properties);
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");

      store.connect(host, user, password);

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);
      

      for (int i = 0, n = 10; i < n; i++) {
         Message message = messages[i];
         String subject = message.getSubject();
         Address from = message.getFrom()[0];
         String content = message.getContent().toString();
         System.out.println("---------------------------------");
         System.out.println("Email Number " + (i + 1));
         System.out.println("Subject: " + subject);
         System.out.println("From: " + from);
         System.out.println("To: " + message.getReplyTo());
         System.out.println("Text: " + content);
         
         mails.add(new Mail(subject, from, content));

      }

      //close the store and folder objects
      emailFolder.close(false);
      store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
      
      return mails;
   }

//   public static void main(String[] args) {
//
//      String host = "pop.gmail.com";// change accordingly
//      String mailStoreType = "pop3";
//      String username = "mtuan2472000@gmail.com";// change accordingly
//      String password = "cyyqarmkbkimgzat";// change accordingly
//
//      check(host, mailStoreType, username, password);
//
//   }

}