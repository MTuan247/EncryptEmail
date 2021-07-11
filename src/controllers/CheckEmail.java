package controllers;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Mail;
import services.EmailCheck;
import services.ReceiveMailImap;

@WebServlet("/CheckEmail")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckEmail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "dangerinmyheart@gmail.com";// change accordingly
        String password = "ouebqttihqpiwxlm";// change accordingly
//        List<Mail> mails = EmailCheck.check(host, mailStoreType, username, password);
        List<Mail> mails = null;
		try {
			mails = ReceiveMailImap.check(host, mailStoreType, username, password);
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        for (Mail mail : mails) {
//        	response.getWriter().append("\n---------------------");
//        	response.getWriter().append("\nSubject: " + mail.getSubject());
//        	response.getWriter().append("\nFrom: " + mail.getFrom());
//        	response.getWriter().append("\nContent: " + mail.getContent());
//        }
//		response.getWriter().append("Served at: ").append(request.getContextPath());
        request.setAttribute("mails", mails);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Result.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
