package controller;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.jdbc.*;
@WebServlet("/maintenance")
public class MaintenanceServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//for availabilities 
		String hotel_id = ""; 
		hotel_id = request.getParameter("hotel");
		RoomDAO roomdao = new RoomDAO();
		
		if ( hotel_id != null && hotel_id.length() > 0){
		ArrayList<String> maintRooms = new ArrayList<String>();
		ArrayList<String> occRooms = new ArrayList<String>();
		String hotelName = "";
		
		try{
			maintRooms = roomdao.maintenanceRoomNames(hotel_id);
			occRooms = roomdao.occupiedRoomNames(hotel_id);
			hotelName = roomdao.getHotelName(hotel_id);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/ownerMaintenance.jsp");
		request.setAttribute("occupied", occRooms);
		request.setAttribute("maintenance", maintRooms);
		request.setAttribute("hotel_name", hotelName);
		rd.forward(request, response);
		return;
		}
		
		//for maintenance setting
		String maintHotelStringName = "";
		String maintRoomStringNumbers[];
		maintHotelStringName = request.getParameter("hidden");
		maintRoomStringNumbers = request.getParameterValues("roomRepair");
		Statement stmt = null;
		
		java.sql.Date dateNow = new Date(Calendar.getInstance().getTimeInMillis());
		java.sql.Date dateEnd = java.sql.Date.valueOf("2100-01-01");
		
		
		if (maintHotelStringName.length() > 0){
			for (String roomString : maintRoomStringNumbers){
			
			try{
			MysqlOperation o = new MysqlOperation();
			Connection connection = o.DBConnect();
			stmt = connection.createStatement();
			String mHotelId = roomdao.getHotel_Id(maintHotelStringName);
			String roomID = roomdao.getRoom_Id(mHotelId, roomString);

			int roomExist = roomdao.checkRecord(roomID);
			
			if (roomExist == 0){
				String sql = "INSERT INTO room_status (hotel_id, room_id, status, start_date, end_date) VALUES"
				+ "(" + mHotelId + "," + roomID + ",'maintenance','" + dateNow + "','" + dateEnd + "')";
				stmt.executeUpdate(sql);
				connection.commit();

			} else if (roomExist != 0){
				String sql = "DELETE FROM room_status WHERE room_status.room_id = " + roomID;
				stmt.executeUpdate(sql);
				connection.commit();
		
			}
		
			}catch (Exception e){
				e.printStackTrace();
			}
			
			}
			RequestDispatcher rd2 = request.getRequestDispatcher("availability");
			rd2.forward(request, response);
		}

}
}
