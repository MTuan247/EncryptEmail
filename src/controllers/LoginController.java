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
import services.DbServices;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		Account user = null;
		
		boolean hasError = false;
		String errorString = null;
		
		if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
			hasError = true;
			errorString = "Yêu cầu nhập tên tài khoản và mật khẩu!";
		} else {
			Connection conn = ConnectionUtils.getConnection();
			
			user = DbServices.findUser(conn, userName, password);
			
			if (user == null) {
				hasError = true;
				errorString = "Tên đăng nhập hoặc mật khẩu không đúng";
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
