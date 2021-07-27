package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.ConnectionUtils;
import models.Account;
import security.Utils;
import services.DbServices;
import services.EmailSend;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@SuppressWarnings("serial")
@WebServlet("/SendEmail")
public class SendEmailController extends HttpServlet {
    private String host;
    private String port;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        host = "smtp.gmail.com";
        port = "587";
    }
    
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/SendEmail.jsp");
        
        dispatcher.forward(request, response);
    }
    
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
    	request.setCharacterEncoding("UTF-8");
        String recipient = request.getParameter("recipient");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String security = request.getParameter("security");
        
        String resultMessage = "";

        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("user");
        String user = account.getEmail();
        String pass = account.getApplicationPassword();
        
 
        try {
        	if(security.equals("y")) {
        		// Lấy sk, pk
                Connection conn = ConnectionUtils.getConnection();
                byte[] skAliceBytes = account.getPrivateKey();
                byte[] pkBobBytes = null;
                pkBobBytes = DbServices.getPublicKey(conn, recipient);
                
                if(pkBobBytes != null) {
                	subject = Utils.makeSecurityMessage(subject, pkBobBytes, skAliceBytes);
                	content = Utils.makeSecurityMessage(content, pkBobBytes, skAliceBytes);
                }
        	}
        	
            EmailSend.sendEmail(host, port, user, pass, recipient, subject, content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            response.sendRedirect(request.getContextPath() + "/CheckEmail");
        }
    }
}
