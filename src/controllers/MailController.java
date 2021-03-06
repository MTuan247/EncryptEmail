package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.mail.Address;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.ConnectionUtils;
import models.Account;
import models.Mail;
import security.Utils;
import services.DbServices;

@WebServlet("/Mail")
public class MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String index = request.getParameter("mail");
		
		int i = Integer.parseInt(index);
		
		List<Mail> mails = (List<Mail>) session.getAttribute("mails");
		Mail mail = mails.get(i);
        
        
		request.setAttribute("mail", mail);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Email.jsp");

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
