package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import models.Mail;

public class EmailCheck {

   public static List<Mail> check(String host, String storeType, String user,
      String password) 
   {
	  List<Mail> mails = new ArrayList<Mail>();
      try {


      Properties properties = new Properties();

      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", "995");
      properties.put("mail.pop3.starttls.enable", "true");
      Session emailSession = Session.getDefaultInstance(properties);
  

      Store store = emailSession.getStore("pop3s");

      store.connect(host, user, password);


      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);


      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);
      

      for (int i = 0, n = messages.length; i < n; i++) {
         Message message = messages[i];
         String subject = message.getSubject();
         Address from = message.getFrom()[0];
//         String content = message.getContent().toString();
         String content = getTextFromMessage(message);
         System.out.println("---------------------------------");
         System.out.println("Email Number " + (i + 1));
         System.out.println("Subject: " + subject);
         System.out.println("From: " + from);
         System.out.println("To: " + message.getReplyTo());
         System.out.println("Text: " + content);
         
         mails.add(new Mail(subject, from, content));

      }


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
   
   private static String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
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