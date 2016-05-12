package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import businessLogic.jdbc.*;

@WebServlet(name = "loginServlet", urlPatterns = "/signup")

public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public signupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("new_username");
		username.toLowerCase();
		String password = request.getParameter("new_password");
		String email = request.getParameter("new_email");
		if (username == null || password == null || email == null) {
			request.setAttribute("error", "Sign up incompleted");
			RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
			rd.forward(request, response);
		} else {
			
			userDAO userDAO = new userDAO();
			int pass = userDAO.checkDuplicate(username, email);
			if (pass<1){
				request.setAttribute("error", "Sign up incompleted");
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response);
			}
			else
			{
				userDAO.addUser(username, password,email);
				//Todo:send email
				//MyAuthenticator sender = new MyAuthenticator();
				//sender.ConfirmEmail(username, email, "user");
				//System.out.println("Confirmation email sent!");
				RequestDispatcher rd = request
						.getRequestDispatcher("/sign_up_complete.jsp");
				rd.forward(request, response);
			}
		}
	}
}
