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
import businessLogic.javaClass.Room;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet(name = "ManagerServlet", urlPatterns = "/manage")
public class ManagerServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      // TODO test session for manager login, invalidate and return to staff.jsp if none
      // TODO get (and set in session) the hotel name/ID from the logged-in manager
      request.getSession().setAttribute("manager_hotel", "Hilbert's");

      // TODO if (valid manager login or session)
      {

         ArrayList<Booking> bookings = new ArrayList<Booking>();
         ArrayList<Room> rooms = new ArrayList<Room>();

         // TODO get from the database, and set, a list of currently occupied rooms for this hotel
         // test bookings for page
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
         request.setAttribute("manager_bookings", bookings);

         // TODO get from the database a list of rooms and bookings, and set them properly
         Room testRoom = new Room();
         testRoom.setRoomNo("1234");
         testRoom.setRoomType("TARDIS");
         rooms.add(testRoom);

         request.setAttribute("manager_occupancy", rooms);

         RequestDispatcher rd = request.getRequestDispatcher("/manager.jsp");
         rd.forward(request, response);
      }
      // TODO else {
      // TODO login error
      // TODO response.sendRedirect("/staff");
      //}
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // post back to manage is a "remove room"
      // (could also be logout or something? TODO)

      // TODO if (valid session)
      {
         // TODO: get room IDs from checkboxes
         // also their room numbers, maybe?
         // TODO: set the actual room to available in the database!
         
         request.setAttribute("manager_message", "Room(s) cleared!");
         
      }
      // TODO else {
      // TODO login error
      // TODO response.sendRedirect("/staff");
      //}
   }

}
