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
         // TODO if newly chosen booking
         {
            // TODO: get booking ID from request
            // TODO: retrieve booking
            // TODO: get requested room types
            // TODO: find available rooms of required types
            // TODO: pass on each relevant type...
         }
         // TODO else, asking to select the rooms
         {
            // TODO get all the selected rooms
            // TODO verify they match
            // TODO assign them to the booking
            // TODO success message
         }
         RequestDispatcher rd = request.getRequestDispatcher("/assignrooms.jsp");
         rd.forward(request, response);
      }
      // TODO else {
      // TODO login error
      // TODO response.sendRedirect("/staff");
      //}
   }
}
