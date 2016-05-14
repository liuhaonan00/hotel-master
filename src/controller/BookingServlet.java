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
    	ArrayList<Room> rooms = new ArrayList<Room>(); 
    	System.out.println(bookrooms[0]);
		RoomDAO roomDAO = new RoomDAO();
		
		for(int i=0;i<bookrooms.length;i++){
			try {
				Room this_room = roomDAO.findRoomById(Integer.parseInt(bookrooms[i]));
				rooms.add(this_room);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int booking_id=0;
		BookingDAO bookingDAO = new BookingDAO();
		
		
		int user_id = Integer.parseInt((String)request.getSession().getAttribute("user_id"));
		BookingDAO.insertBooking(user_id,rooms,check_in,check_out);
		

		try {
			booking_id = bookingDAO.findbookingID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BookingDAO.insertRoom_status(booking_id,rooms,check_in,check_out);
//		request.getSession().setAttribute("roomResult", rooms);
//		request.getSession().setAttribute("check_in", check_in);
//		request.getSession().setAttribute("check_out", check_out);
//		request.getSession().setAttribute("city", city);
		request.getRequestDispatcher("bookingComplete.jsp").forward(request,response);
		
    }
    
}
