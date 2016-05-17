package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import businessLogic.jdbc.MysqlOperation;

public class reinaldotestclass {
	
	public static String getRoom_Id(String hotel_id, String room_no) throws SQLException
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
	
	public static void main(String[] args) throws SQLException {
		System.out.println(getRoom_Id("1", "501"));		
	}

}
