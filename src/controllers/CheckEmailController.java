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
import javax.servlet.http.HttpSession;

import models.Account;
import models.Mail;
import services.ReceiveMailImap;

@WebServlet("/CheckEmail")
public class CheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckEmailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Account user = (Account) session.getAttribute("user");
		String username = user.getEmail();
		String password = user.getApplicationPassword();
		
		System.out.println(username);
		System.out.println(password);

        List<Mail> mails = null;
		try {
			mails = ReceiveMailImap.check(username, password);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("mails", mails);

        request.setAttribute("mails", mails);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/CheckMail.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
