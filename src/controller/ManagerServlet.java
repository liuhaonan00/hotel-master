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

import businessLogic.javaClass.Booking;
import businessLogic.javaClass.Hotel;
import businessLogic.javaClass.Room;
import businessLogic.jdbc.HotelDAO;
import businessLogic.jdbc.RoomDAO;

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

      // TODO log in the manager in the staff servlet, get hotel ID, put a hotel in the session
      int fakeHotelID = 1;
      // TODO do all this in login
      HotelDAO hotelDao = new HotelDAO();
      try {
         Hotel fakeHotel = hotelDao.findHotelById(fakeHotelID);
         request.getSession().setAttribute("manager_hotel", fakeHotel);
      } catch (SQLException e) {
         Hotel failHotel = new Hotel();
         failHotel.setHotelName("Error");
         request.getSession().setAttribute("manager_hotel", failHotel);
         e.printStackTrace();
      }

      // TODO if (valid manager session)
      {
         Hotel managedHotel = (Hotel) request.getSession().getAttribute("manager_hotel");
         ArrayList<Room> occupiedRooms = new ArrayList<Room>();
         ArrayList<Booking> emptyBookings = new ArrayList<Booking>();
         ArrayList<Booking> filledBookings = new ArrayList<Booking>();

         // get a list of currently occupied rooms for this hotel
         RoomDAO roomDao = new RoomDAO();
         try {
            occupiedRooms = roomDao.allOccRooms(managedHotel.getHotelId());
         } catch (SQLException e) {
            Room failRoom = new Room();
            failRoom.setRoomType("Error");
            e.printStackTrace();
         }

         // TODO get from the database a list of current bookings which are not yet assigned rooms
         // test bookings for page
         Booking testBooking = new Booking();
         testBooking.setBookingID(246);
         testBooking.setUser("userrrr");
         testBooking.setStartDate("start");
         testBooking.setEndDate("testEnd");
         testBooking.setRoomTypeString("2 Twins, 1 Queen");
         emptyBookings.add(testBooking);

         // TODO get from the database a list of current bookings which are already assigned rooms
         testBooking = new Booking();
         testBooking.setUser("userrrr2");
         testBooking.setStartDate("start2");
         testBooking.setEndDate("testEnd2");
         testBooking.setRoomTypeString("2 Twins, 1 Queen, one TARDIS");
         filledBookings.add(testBooking);

         request.setAttribute("manager_occupancy", occupiedRooms);
         request.setAttribute("manager_empty_bookings", emptyBookings);
         request.setAttribute("manager_filled_bookings", filledBookings);

         RequestDispatcher rd = request.getRequestDispatcher("/manager.jsp");
         rd.forward(request, response);
      }
      // TODO else {
      // TODO redirect to staff login servlet
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
      // TODO redirect to staff login servlet
      //}
   }
}
