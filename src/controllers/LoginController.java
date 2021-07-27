package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.ConnectionUtils;
import models.Account;
import security.BCryptHash;
import services.DbServices;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account user = (Account)request.getSession().getAttribute("user");
		if(user == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/CheckEmail"); 
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("userName");
		String password = request.getParameter("password");
		
		Account user = null;
		
		boolean hasError = false;
		String errorString = null;
		
		if (email == null || password == null || email.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Email and Password are required!";
		} else {
			Connection conn = ConnectionUtils.getConnection();
			
			user = DbServices.findUser(conn, email);
			
			if (user == null || BCryptHash.checkPass(password, user.getPassword()) == false) {
				hasError = true;
				errorString = "Email or Password is wrong!";
			}
			
			
		}
		
		if (hasError) {

			request.setAttribute("errorString", errorString);
			
			System.out.print(errorString);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");

			dispatcher.forward(request, response);
		} else {
			HttpSession session = request.getSession();
			
			session.setAttribute("user", user);

			response.sendRedirect(request.getContextPath() + "/CheckEmail");

		}
		
	}

}
