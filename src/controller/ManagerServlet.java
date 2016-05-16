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
import businessLogic.javaClass.Hotel;
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

      // TODO test session for manager login, return to staff.jsp if none

      int hotelID = 1; // TODO get the actual ID from the manager login

      // TODO get (and set in session) the hotel name/ID from the logged-in manager
      Hotel managedHotel = new Hotel();
      managedHotel.setHotelName("Hilbert's Grand Hotel");
      request.getSession().setAttribute("manager_hotel", managedHotel);

      // TODO if (valid manager login or session)
      {
         ArrayList<Booking> bookings = new ArrayList<Booking>();
         ArrayList<Room> rooms = new ArrayList<Room>();

         // TODO get from the database, and set, a list of currently occupied rooms for this hotel
         // test room for page
         Room testRoom = new Room();
         testRoom.setRoomId(1234);
         testRoom.setRoomNo("Not an actual number :p");
         testRoom.setRoomType("TARDIS");
         rooms.add(testRoom);
         
         request.setAttribute("manager_occupancy", rooms);
         
         // TODO get from the database a list of current bookings, and set them properly
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

      // TODO if (valid session)
      {
         // TODO: get room IDs from checkboxes
         String[] roomsToClear = request.getParameterValues("clear_rooms");
         String clearedRooms = ""; // possibly just to test?

         if (roomsToClear != null) {
            for (String roomID : roomsToClear) {
               // TODO: set the actual room to available in the database!
               clearedRooms = clearedRooms + ' ' + roomID;
            }
            request.setAttribute("manager_message", "Room(s) cleared: " + clearedRooms);
         }
         
         doGet(request, response);
      }
      // TODO else {
      // TODO login error
      // TODO response.sendRedirect("/staff");
      //}
   }
}
