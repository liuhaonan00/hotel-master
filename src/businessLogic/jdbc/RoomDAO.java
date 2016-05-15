package businessLogic.jdbc;

import java.util.ArrayList;
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
		String query = "SELECT * FROM room natural join hotel WHERE room_id not in "+
				"(SELECT room.room_id FROM room natural join room_status "+
"where (room_status.end_date <= "+ check_out+" AND room_status.end_date > "+check_in+")"+
" OR (room_status.start_date < " + check_out+"AND room_status.start_date >= "+check_in+"))"; //todo sql query
		System.out.println(query);
		if(price>0){
			query=query+"and price <"+price;
		}
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room this_room = new Room();
			this_room.setRoomId(rs.getInt(2));
			System.out.println("RoonmID"+rs.getInt(2));
			this_room.setHotelId(rs.getInt(1));
			this_room.setRoomType(rs.getString(3));
			this_room.setRoomNo(rs.getString(4));
			this_room.setPrice(rs.getFloat(5));
			this_room.setRoomDescription(rs.getString(6));
			rooms.add(this_room);	
		}
		o.closeDB(connection);
		return rooms;
	}
	
	public Room findRoomById(int n) throws SQLException
	{
		Room room = new Room();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * FROM room WHERE room_id = "+n;
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			room.setRoomId(rs.getInt(1));
			room.setHotelId(rs.getInt(2));
			room.setRoomType(rs.getString(3));
			room.setRoomNo(rs.getString(4));
			room.setPrice(rs.getInt(5));
			room.setRoomDescription(rs.getString(6));
		}
		return room;
	}
	
	public ArrayList<Room> allOccRooms(String StartDate,String EndDate,int hotel_id) throws SQLException
	{
		ArrayList<Room> occRooms= new ArrayList<Room>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * FROM room where room_id in (SELECT room_id from room_status where "+
		"room_status.end_date <= '"+EndDate+"' OR room_status.start_date >= '"+StartDate+"' and room_status.status = 'occupied' and hotel_id="+""+hotel_id+")";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room room = new Room();
			room.setRoomId(rs.getInt(1));
			room.setHotelId(rs.getInt(2));
			room.setRoomType(rs.getString(3));
			room.setRoomNo(rs.getString(4));
			room.setPrice(rs.getInt(5));
			room.setRoomDescription(rs.getString(6));
			occRooms.add(room);
		}
		return occRooms;
	}
	
	//returns the amount of rooms that are unavailable at a hotel id. 
	public int unavailablerooms(int hotel_id) throws SQLException
	{
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT COUNT(hotel_id) FROM room_status WHERE (status = 'occupied' OR status = 'maintenance') AND hotel_id = " + hotel_id;
		ResultSet rs = o.searchDB(connection, query);
		int i = 0;
		while(rs.next()){
			i = rs.getInt(1);
		}
		return i;
	}
	
	
}
