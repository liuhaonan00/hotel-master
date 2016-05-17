package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.javaClass.Room;
import businessLogic.javaClass.TypeRequest;

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

      // TODO if (valid session)
      {
         if (request.getParameter("assign_setting_rooms") == null) {
            // manager has just chosen this booking...
            String bookingID = request.getParameter("id_to_book");

            // TODO: retrieve the actual booking by ID

            // TODO retrieve the actual user from the booking
            String userName = "fake user";
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

            // TODO: obtain all the selected rooms submitted
            String[] roomIDs = request.getParameterValues("assign_rooms");
            List<Room> assignedRooms = new LinkedList<Room>(); // possibly just to test?

            if (roomIDs != null) {
               for (String roomID : roomIDs) {
                  // TODO: set the actual room to available in the database!
                  Room room = new Room();
                  room.setRoomId(Integer.parseInt(roomID));
                  assignedRooms.add(room);
               }
            }

            // TODO (restriction once the base is working) check if the correct number of rooms for requests is given
            {
               // TODO assign them to the booking
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
      // TODO else {
      // TODO redirect to staff login servlet
      //}
   }
}
