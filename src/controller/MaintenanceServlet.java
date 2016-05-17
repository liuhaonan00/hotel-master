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

import businessLogic.jdbc.MysqlOperation;
@WebServlet("/maintenance")
public class MaintenanceServlet extends HttpServlet {
	
	public ArrayList<String> maintenanceRoomNames(String hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT room_no FROM room WHERE room_id IN (SELECT room_id FROM room_status WHERE room_status.status = 'maintenance' AND room_status.hotel_id = " + hotel_id + ")";
		ResultSet rs = o.searchDB(connection, query);
		ArrayList<String> maintRooms = new ArrayList<String>();
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
		ArrayList<String> occRooms = new ArrayList<String>();
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
		String query = "SELECT room_id FROM room WHERE (room.hotel_id = " + hotel_id + " AND room.room_no = " + room_no + ")";
		ResultSet rs = o.searchDB(connection, query);
		String roomID = "";
		while(rs.next()){
			roomID = rs.getString(1);
		}
		return roomID;
	}
	
	public int checkRecord(String room_id) throws SQLException {
		int i = 0;
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT COUNT(room_id) FROM room_status WHERE room_id = " + room_id + " AND status = 'maintenance'";
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			i = Integer.valueOf(rs.getString(1));
		}
		return i;
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//for availabilities 
		String hotel_id = ""; 
		hotel_id = request.getParameter("hotel");
		
		if ( hotel_id != null && hotel_id.length() > 0){
		ArrayList<String> maintRooms = new ArrayList<String>();
		ArrayList<String> occRooms = new ArrayList<String>();
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
			String mHotelId = getHotel_Id(maintHotelStringName);
			String roomID = getRoom_Id(mHotelId, roomString);

			int roomExist = checkRecord(roomID);
			
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
