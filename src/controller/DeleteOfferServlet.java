package controller;

import java.io.IOException;

import businessLogic.javaClass.*;
import businessLogic.jdbc.*;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteoffers")
	public class DeleteOfferServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    public DeleteOfferServlet() {
	        super();
	    }

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String hotelName = request.getParameter("hidden");
			String roomType = request.getParameter("roomType");
			RoomDAO roomdao = new RoomDAO();
			RequestDispatcher rd = request.getRequestDispatcher("availability");
			try{
			String hotel_id = roomdao.getHotel_Id(hotelName);
			
			MysqlOperation o = new MysqlOperation();
			Connection connection = o.DBConnect();
			Statement stmt = connection.createStatement();
			String delete = "DELETE FROM offer WHERE offer.hotel_id = " + hotel_id + " AND offer.room_type = '" + roomType + "'";
			stmt.executeUpdate(delete);
			
			}catch (Exception e){
				e.printStackTrace();
			}
			rd.forward(request, response);
			
}
}
