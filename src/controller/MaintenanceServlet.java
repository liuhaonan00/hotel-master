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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hotel_id = request.getParameter("hotel");
		ArrayList<String> maintRooms = new ArrayList<String>();
		ArrayList<String> occRooms = new ArrayList<String>();
		try{
			maintRooms = maintenanceRoomNames(hotel_id);
			occRooms = occupiedRoomNames(hotel_id);
		}catch (Exception e){
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/ownerMaintenance.jsp");
		request.setAttribute("occupied", occRooms);
		request.setAttribute("maintenance", maintRooms);
		rd.forward(request, response);
		

}
}
