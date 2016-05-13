package businessLogic.jdbc;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import java.sql.*;

import businessLogic.javaClass.*;


public class RoomDAO {
	public ArrayList<Room> randomRoom(int n) throws SQLException
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select * from room ORDER BY RAND() limit "+n;
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room this_room = new Room();
			this_room.setRoomId(rs.getInt(1));
			this_room.setHotelId(rs.getInt(2));
			this_room.setRoomType(rs.getString(3));
			this_room.setRoomNo(rs.getString(4));
			this_room.setPrice(rs.getInt(5));
			this_room.setRoomDescription(rs.getString(6));
			rooms.add(this_room);	
		}

		o.closeDB(connection);
		return rooms;
	}
	
	
	public ArrayList<Room> findAllRoom(String check_in,String check_out,String City,int price) throws SQLException
	{
		ArrayList<Room> rooms = new ArrayList<Room>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * FROM room natural join hotel WHERE room_id not in"+
				"(SELECT room.room_id FROM room natural join room_status"+
"where (room_status.end_date <="+ check_out+" AND room_status.end_date > "+check_in+")"+
"OR (room_status.start_date <" + check_out+"AND room_status.start_date >= "+check_in+"))"; //todo sql query
		
		if(price>0){
			query=query+"and price <"+price;
		}
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room this_room = new Room();
			this_room.setRoomId(rs.getInt(1));
			this_room.setHotelId(rs.getInt(2));
			this_room.setRoomType(rs.getString(3));
			this_room.setRoomNo(rs.getString(4));
			this_room.setPrice(rs.getInt(5));
			this_room.setRoomDescription(rs.getString(6));
			rooms.add(this_room);	
		}
		o.closeDB(connection);
		return rooms;
	}
}
