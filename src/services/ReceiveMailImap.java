package services;

import java.io.*;
import java.sql.Connection;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import db.ConnectionUtils;
import models.Mail;
import security.Utils;

public class ReceiveMailImap {

  public ReceiveMailImap() {}

  public static List<Mail> check( String user, String password) throws Exception {
	  
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
      
      // Test nhận thư mã hóa
      Connection conn = ConnectionUtils.getConnection();
      byte[] skBobBytes = DbServices.getPrivateKey(conn, user);
      byte[] pkAliceBytes = null;
      
      for (int i=messages.length - 1; i >= 0; --i) {
        Message msg = messages[i];

        Address from = msg.getFrom()[0];
        if (msg.getReplyTo().length >= 1) {
          from = msg.getReplyTo()[0];
        }
        else if (msg.getFrom().length >= 1) {
          from = msg.getFrom()[0];
        }
        
        
        String subject = msg.getSubject();
        String content = getTextFromMessage(msg);
        String noticeSecurity;
        
        pkAliceBytes = DbServices.getPublicKey(conn, from.toString());
    	subject = Utils.readSecurityMessage(subject, pkAliceBytes, skBobBytes).get(0);
    	
		List<String> list =  Utils.readSecurityMessage(content, pkAliceBytes, skBobBytes);
		content = list.get(0);
		noticeSecurity = list.get(1);
		
        msg.setFlag(Flags.Flag.SEEN,true);
       
        mails.add(new Mail(subject, from, content, noticeSecurity));
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
	    if (message.isMimeType("text/plain")||message.isMimeType("text/html")) {
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
//	            result = result + "\n" + bodyPart.getContent();
	            result = result + bodyPart.getContent();
	            break;
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
//	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	            result = result + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}

}
