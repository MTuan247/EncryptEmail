package services;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import models.Mail;

public class ReceiveMailImap {

  public ReceiveMailImap() {}

  //
  // inspired by :
  // http://www.mikedesjardins.net/content/2008/03/using-javamail-to-read-and-extract/
  //

  public static List<Mail> check( String user, String password) throws MessagingException, IOException {
	  
    Folder folder = null;
    Store store = null;
    List<Mail> mails = new ArrayList<Mail>();
    
    try {
      Properties props = System.getProperties();
      props.setProperty("mail.store.protocol", "imaps");

      Session session = Session.getDefaultInstance(props, null);
      // session.setDebug(true);
      store = session.getStore("imaps");
      store.connect("imap.gmail.com", user, password);
      folder = store.getFolder("Inbox");
      /* Others GMail folders :
       * [Gmail]/All Mail   This folder contains all of your Gmail messages.
       * [Gmail]/Drafts     Your drafts.
       * [Gmail]/Sent Mail  Messages you sent to other people.
       * [Gmail]/Spam       Messages marked as spam.
       * [Gmail]/Starred    Starred messages.
       * [Gmail]/Trash      Messages deleted from Gmail.
       */
      folder.open(Folder.READ_WRITE);
      Message messages[] = folder.getMessages();
      System.out.println("No of Messages : " + folder.getMessageCount());
      System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
      
      for (int i=0; i < messages.length; ++i) {
        System.out.println("MESSAGE #" + (i + 1) + ":");
        Message msg = messages[i];
        /*
          if we don''t want to fetch messages already processed
          if (!msg.isSet(Flags.Flag.SEEN)) {
             String from = "unknown";
             ...
          }
        */
        Address from = msg.getFrom()[0];
        if (msg.getReplyTo().length >= 1) {
          from = msg.getReplyTo()[0];
        }
        else if (msg.getFrom().length >= 1) {
          from = msg.getFrom()[0];
        }
        String subject = msg.getSubject();
        
        String content = getTextFromMessage(msg);
        System.out.print(content);
        msg.setFlag(Flags.Flag.SEEN,true);
        
        mails.add(new Mail(subject, from, content));
      }
    }
    finally {
      if (folder != null) { folder.close(true); }
      if (store != null) { store.close(); }
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

//  public static void main(String args[]) throws Exception {
//    ReceiveMailImap.check();
//  }
}
