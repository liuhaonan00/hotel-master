package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.javaClass.*;
import businessLogic.jdbc.*;
import businessLogic.library.*;

public class ShoppingCartServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public ShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String[] bookroom = request.getParameterValues("bookroom");
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ShoppingCart> thisCart = new ArrayList<ShoppingCart>();
		int user_id = (int)request.getSession().getAttribute("current_user_id");
		if(request.getSession().getAttribute("ShoppingCart") != null){
			thisCart = (ArrayList)request.getSession().getAttribute("ShoppingCart");
		}
		ShoppingCartDAO shoppingcart = new ShoppingCartDAO();
		try {
			shoppingcart.insert(thisCart,user_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int bookingid = shoppingcart.findLastBookingID();
		String pin = shoppingcart.findpin(bookingid);
		UserDAO userDAO = new UserDAO();
		String username = (String)request.getSession().getAttribute("current_user");
		String email = userDAO.getEmail(user_id);
		EmailApi emailapi = new EmailApi();
		//EmailApi.sendBookingConfirmation(bookingid,username,email,pin);

		request.getSession().setAttribute("ShoppingCart", null);
		request.getRequestDispatcher("thankyou.jsp").forward(request,response);	
		
	}
	
	
}
