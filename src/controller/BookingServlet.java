package controller;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import businessLogic.jdbc.*;
import businessLogic.javaClass.*;
@WebServlet(name = "BookingServlet", urlPatterns = "/booking")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	String[] bookrooms = request.getParameterValues("bookRoom");
    	String check_in = (String)request.getSession().getAttribute("check_in");
    	String check_out = (String)request.getSession().getAttribute("check_out");
    	String city = (String)request.getSession().getAttribute("city");
    	
    	System.out.println(bookrooms[0]);
		RoomDAO roomDAO = new RoomDAO();
		ShoppingCartDAO ShoppingCartDAO = new ShoppingCartDAO();
		ArrayList<ShoppingCart> thisCart = new ArrayList<ShoppingCart>();
		if(request.getSession().getAttribute("ShoppingCart") != null){
			thisCart = (ArrayList)request.getSession().getAttribute("ShoppingCart");
		}
		
		for(int i=0;i<bookrooms.length;i++){
			try {
				
				Room this_room = roomDAO.findRoomById(Integer.parseInt(bookrooms[i]));
				ShoppingCart shoppingCart = ShoppingCartDAO.addToCart(check_in,check_out,this_room);
				thisCart.add(shoppingCart);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("ShoppingCart", thisCart);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request,response);
		
    }
    
}
