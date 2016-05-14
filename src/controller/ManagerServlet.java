package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.javaClass.Booking;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet(name = "ManagerServlet", urlPatterns = "/manage")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
	   // TODO test session for manager login, invalidate and return to staff.jsp if none
	   // TODO get from the database a list of rooms and bookings, and set them properly
	   // TODO what happens when we add a room to the booking?
	   // TODO check out time!
	   
	   ArrayList<Booking> bookings = new ArrayList<Booking>();
      
      Booking testBooking = new Booking();
      testBooking.setUser("userrrr");
      testBooking.setStartDate("start");
      testBooking.setEndDate("testEnd");
      testBooking.setRoomString("2 Twins, 1 Queen");
      
      bookings.add(testBooking);
      
      testBooking = new Booking();
      testBooking.setUser("userrrr2");
      testBooking.setStartDate("start2");
      testBooking.setEndDate("testEnd2");
      testBooking.setRoomString("2 Twins, 1 Queen, one TARDIS");
      
      bookings.add(testBooking);
      
	   request.getSession().setAttribute("manager_bookings", bookings);
      request.getSession().setAttribute("hotel_name", "Hilbert's");
	   RequestDispatcher rd = request.getRequestDispatcher("/manager.jsp");
      rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
