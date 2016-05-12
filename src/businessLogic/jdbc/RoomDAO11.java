package businessLogic.jdbc;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import java.sql.*;

import businessLogic.javaClass.*;


public class RoomDAO11 {
	public ArrayList<Room1> randomRoom(int n) throws SQLException
	{
		ArrayList<Room1> rooms = new ArrayList<Room1>();
		MysqlOperation1 o = new MysqlOperation1();
		Connection connection = o.DBConnect();
		String query = "select * from room ORDER BY RAND() limit "+n;
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room1 this_room = new Room1();
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
	
	
	public ArrayList<Room1> findAllRoom() throws SQLException
	{
		ArrayList<Room1> rooms = new ArrayList<Room1>();
		MysqlOperation1 o = new MysqlOperation1();
		Connection connection = o.DBConnect();
		String query = "select * from room ORDER BY RAND()"; //todo sql query
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Room1 this_room = new Room1();
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
