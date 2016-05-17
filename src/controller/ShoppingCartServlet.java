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
		int uesr_id = (int)request.getSession().getAttribute("current_user_id");
		if(request.getSession().getAttribute("ShoppingCart") != null){
			thisCart = (ArrayList)request.getSession().getAttribute("ShoppingCart");
		}
		
		for (int i =0;i<thisCart.size();i++){
			ShoppingCartDAO shoppingcart = new ShoppingCartDAO();
			//shoppingcart.insert(thisCart.get(i),uesr_id);
		}
		
		
	}
	
	
}
