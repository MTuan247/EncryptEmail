package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Mail;
import services.EmailCheck;

@WebServlet("/CheckEmail")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckEmail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String host = "pop.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "mtuan2472000@gmail.com";// change accordingly
        String password = "cyyqarmkbkimgzat";// change accordingly
        List<Mail> mails = EmailCheck.check(host, mailStoreType, username, password);
        for (Mail mail : mails) {
        	response.getWriter().append("---------------------");
        	response.getWriter().append("Subject: " + mail.getSubject());
        	response.getWriter().append("From: " + mail.getFrom());
        	response.getWriter().append("Content: " + mail.getContent());
        }
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
