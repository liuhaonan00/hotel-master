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
    	String check_in = (String)request.getSession().getAttribute("check_in");
    	String check_out = (String)request.getSession().getAttribute("check_out");
    	String city = (String)request.getSession().getAttribute("city");
    	
    	String hotel_id = request.getParameter("hotelid");
    	String roomtype = request.getParameter("roomtype");
    	String no = request.getParameter("number_of_room");
    	String price = request.getParameter("price");
    	String extrabed = request.getParameter("extrabed");

		ArrayList<ShoppingCart> thisCart = new ArrayList<ShoppingCart>();
		if(request.getSession().getAttribute("ShoppingCart") != null){
			thisCart = (ArrayList)request.getSession().getAttribute("ShoppingCart");
		}
			ShoppingCart newItem = new ShoppingCart();
			newItem.setcheck_in(check_in);
			newItem.setcheck_out(check_out);
			newItem.sethotel_id(Integer.parseInt(hotel_id));
			newItem.setno(Integer.parseInt(no));
			Float total = Float.parseFloat(price)*Integer.parseInt(no);
			newItem.setprice(total);
			newItem.setroomType(roomtype);
			newItem.setextrabed(Integer.parseInt(extrabed));
			thisCart.add(newItem);
		
		
		request.getSession().setAttribute("ShoppingCart", thisCart);
		request.getRequestDispatcher("shoppingcart.jsp").forward(request,response);		
    }
    
}
