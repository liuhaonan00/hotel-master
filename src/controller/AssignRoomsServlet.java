package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.javaClass.Booking;
import businessLogic.javaClass.Room;
import businessLogic.javaClass.TypeRequest;
import businessLogic.javaClass.User;
import businessLogic.jdbc.BookingDAO;
import businessLogic.jdbc.RoomDAO;
import businessLogic.jdbc.UserDAO;

/**
 * Servlet implementation class AssignRoomsServlet
 */
@WebServlet(name = "AssignRoomsServlet", urlPatterns = "/assignrooms")
public class AssignRoomsServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.sendRedirect("/manage");
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // post to assignrooms is either an initial showing of the booking & available rooms
      // or a request to assign particular rooms

      if (request.getSession().getAttribute("logged_in_manager") == null) {
         response.sendRedirect(request.getContextPath() + "/staff");
      } else {
         if (request.getParameter("assign_setting_rooms") == null) {
            // manager has just chosen this booking...
            String bookingID = request.getParameter("id_to_book");

            // retrieve the actual booking by ID
            Booking booking = new Booking();
            BookingDAO bookingDao = new BookingDAO();
            try {
               booking = bookingDao.findBookingById(Integer.parseInt(bookingID));
            } catch (NumberFormatException e) {
               e.printStackTrace();
            } catch (SQLException e) {
               e.printStackTrace();
            }
            
            // retrieve user(name) from booking
            String userName = "";
            UserDAO userDao = new UserDAO();
            try {
               userName = userDao.findExistingUserById(booking.getUserID()).getUsername();
            } catch (SQLException e) {
               e.printStackTrace();
            }
            
            // pass on the booking's user
            request.setAttribute("assign_user", userName);

            // TODO: get requested room types for the booking
            // TODO: find available rooms of required types (or all available rooms?)

            // TODO(restriction once the base is working) if all requested types are fewer than available types
            {
               // TODO for relevant types:
               // TODO: put in type name, requested number, available rooms of that type
               // TODO (restriction once the base is working) ignore types with 0 requests, *unless* we need to override booking due to limited availability
               Room dummyRoom = new Room();
               dummyRoom.setRoomNo("87678");
               dummyRoom.setRoomId(86);
               dummyRoom.setRoomDescription("a dummy room");
               List<Room> fakeRooms = new ArrayList<Room>();
               fakeRooms.add(dummyRoom);

               TypeRequest dummyType = new TypeRequest();
               dummyType.setNumberRequested(2);
               dummyType.setTypeName("dummy room type");
               dummyType.setAvailableRooms(fakeRooms);

               List<TypeRequest> requestedTypes = new ArrayList<TypeRequest>();
               requestedTypes.add(dummyType);

               request.setAttribute("assign_requested_types", requestedTypes);
            }
            // TODO else
            {
               // TODO error message, too few rooms available
               // TODO include types with 0 requests
               // TODO let them choose some set of all the available rooms?
            }

            RequestDispatcher rd = request.getRequestDispatcher("/assignrooms.jsp");
            rd.forward(request, response);

         } else {
            // manager is selecting rooms

            String[] roomIDs = request.getParameterValues("assign_rooms");
            List<Room> assignedRooms = new LinkedList<Room>();

            if (roomIDs != null) {
               RoomDAO roomDao = new RoomDAO();
               for (String roomID : roomIDs) {
                  // obtain all the selected rooms submitted
                  try {
                     assignedRooms.add(roomDao.findRoomById(Integer.parseInt(roomID)));
                  } catch (NumberFormatException e) {
                     e.printStackTrace();
                  } catch (SQLException e) {
                     e.printStackTrace();
                  }
               }
            }

            // TODO (restriction once the base is working) check if the correct number of rooms for requests is given
            {
               // TODO mark the rooms as occupied
               // TODO mark the booking as filled
               request.setAttribute("assign_message", "Room(s) assigned successfully!");
               request.setAttribute("assign_succeeded", true);

            }
            // TODO else
            {
               // TODO error message, assigned does not match required, some are missing or something?
            }
            RequestDispatcher rd = request.getRequestDispatcher("/assignrooms.jsp");
            rd.forward(request, response);
         }
      }
   }
}
