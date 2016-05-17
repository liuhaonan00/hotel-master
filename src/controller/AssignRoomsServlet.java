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
         if (request.getParameter("setting_rooms") == null) {
            // manager has just chosen this booking...
            String bookingID = request.getParameter("id_to_book");

            // TODO: retrieve the actual booking by ID

            // TODO: get requested room types for the booking
            // TODO: find available rooms of required types
            
            // TODO(restriction once the base is working) if all requested types are fewer than available types
            {
               // TODO: Create a TypeRequest bean!!!
               // TODO: put in type name, requested number, available rooms of that type
               // TODO (restriction once the base is working) ignore types with 0 requests, *unless* we need to override booking due to limited availability
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
            
            // TODO (restriction once the base is working) check if the correct number of rooms for requests is given
            {
               // TODO assign them to the booking
               // TODO success message
               // TODO success fact passed on
               
            }
            // TODO else
            {
               // TODO error message, assigned does not match required, some are missing or something?
               //               RequestDispatcher rd = request.getRequestDispatcher("/assignrooms.jsp");
               //               rd.forward(request, response);
            }
         }
      }
      // TODO else {
      // TODO redirect to staff login servlet
      //}
   }
}
