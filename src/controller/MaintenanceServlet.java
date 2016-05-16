package controller;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.jdbc.MysqlOperation;
@WebServlet("/maintenance")
public class MaintenanceServlet extends HttpServlet {
	
	public ArrayList<String> maintenanceRoomNames(String hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT room_no FROM room WHERE room_id IN (SELECT room_id FROM room_status WHERE room_status.status = 'maintenance' AND room_status.hotel_id = " + hotel_id + ")";
		ResultSet rs = o.searchDB(connection, query);
		ArrayList<String> maintRooms = new ArrayList<>();
		while(rs.next()){
			String i = rs.getString(1);
			maintRooms.add(i);
		}
		return maintRooms;
	}
	
	public ArrayList<String> occupiedRoomNames(String hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT room_no FROM room WHERE room_id IN (SELECT room_id FROM room_status WHERE room_status.status = 'occupied' AND room_status.hotel_id = " + hotel_id + ")";
		ResultSet rs = o.searchDB(connection, query);
		ArrayList<String> occRooms = new ArrayList<>();
		while(rs.next()){
			String i = rs.getString(1);
			occRooms.add(i);
		}
		return occRooms;
	}
	
	public String getHotelName(String hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT hotel_name FROM hotel WHERE hotel_id = " + hotel_id +"";
		ResultSet rs = o.searchDB(connection, query);
		String hotelName = "";
		while(rs.next()){
			hotelName = rs.getString(1);
		}
		return hotelName;
	}
	
	public String getHotel_Id(String hotel_name) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT hotel_id FROM hotel WHERE hotel_name = " +"'"+ hotel_name +"'";
		ResultSet rs = o.searchDB(connection, query);
		String hotelName = "";
		while(rs.next()){
			hotelName = rs.getString(1);
		}
		return hotelName;
	}
	
	public String getRoom_Id(String hotel_id, String room_no) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT room_id FROM room WHERE (room.hotel_id = hotel_id AND room.room_no = room_no)";
		ResultSet rs = o.searchDB(connection, query);
		String roomID = "";
		while(rs.next()){
			roomID = rs.getString(1);
		}
		return roomID;
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hotel_id = ""; 
		hotel_id = request.getParameter("hotel");
		if (hotel_id.length() > 0){
		ArrayList<String> maintRooms = new ArrayList<>();
		ArrayList<String> occRooms = new ArrayList<>();
		String hotelName = "";
		try{
			maintRooms = maintenanceRoomNames(hotel_id);
			occRooms = occupiedRoomNames(hotel_id);
			hotelName = getHotelName(hotel_id);
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
		
//		INSERT INTO room_status (hotel_id, room_id,booking_id, status, start_date, end_date, booking_price) VALUES
//		(1,1,2,'Booked', '2016-07-28','2016-07-30','100');
		
		String maintHotelName = "";
		String maintRoomName = "";
		maintHotelName = request.getParameter("hidden");
		maintRoomName = request.getParameter("roomRepair");
		Statement stmt = null;
		String mHotelName = "";
		String roomID = "";
		if (maintHotelName.length() > 0){
			
			try{
				
			MysqlOperation o = new MysqlOperation();
			Connection connection = o.DBConnect();
			stmt = connection.createStatement();
			mHotelName = getHotel_Id(maintHotelName);
			String roomID = getRoom_Id();
			String sql = "INSERT INTO room_status (hotel_id, room_id, status, start_date, end_date)";
			}catch (Exception e){
				e.printStackTrace();
			}
		
		
		

}

}
}
