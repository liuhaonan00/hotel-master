package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import businessLogic.javaClass.Offer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.jdbc.*;

@WebServlet("/offers")
public class OfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OfferServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hotel_id = ""; 
		hotel_id = request.getParameter("offerHotel");
		RoomDAO roomdao = new RoomDAO();
		if ( hotel_id != null && hotel_id.length() > 0){
			try{
			RequestDispatcher rd = request.getRequestDispatcher("/ownerOffers.jsp");
			String hotelName = roomdao.getHotelName(hotel_id);
			request.setAttribute("hotelName", hotelName);
			ArrayList<Offer> offers = roomdao.getOffersForHotel(hotel_id);
			request.setAttribute("hotelOffers", offers);
			rd.forward(request, response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
		String startDate = "";
		String endDate =  "";
		String hotelName = request.getParameter("hidden");
		String roomType = request.getParameter("roomType");
		float disc1; 
		
		startDate = request.getParameter("startDate");
		endDate = request.getParameter("endDate");
		
		if ( startDate != null && startDate.length() > 0){
			disc1 = Float.parseFloat(request.getParameter("price"));
			float discount = disc1 / 100;
			MysqlOperation o = new MysqlOperation();
			Connection connection = o.DBConnect();
			  //INSERT INTO offer (offer_price, hotel_id, room_type, start, end) VALUES (?,?,?,?,?)
			try{
				Statement stmt = connection.createStatement();
				RequestDispatcher rd = request.getRequestDispatcher("availability");
				String oHotelId = roomdao.getHotel_Id(hotelName);
				java.sql.Date dateStart = java.sql.Date.valueOf(startDate);
				java.sql.Date dateEnd = java.sql.Date.valueOf(endDate);
				String insert = "INSERT INTO offer (offer_price, hotel_id, room_type, start, end) VALUES ("
						+ discount +"," + oHotelId + ",'" + roomType + "','" + dateStart + "','" + dateEnd + "')";
				String delete = "DELETE FROM offer WHERE offer.hotel_id = " + oHotelId + " AND offer.room_type = '" + roomType + "'";		
				if ((roomdao.checkOffer(oHotelId, roomType)) == 0) {
					stmt.executeUpdate(insert);
				}
				if ((roomdao.checkOffer(oHotelId, roomType)) != 0) {
					stmt.executeUpdate(delete);	
					stmt.executeUpdate(insert);
					}

				rd.forward(request, response);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
}
}


