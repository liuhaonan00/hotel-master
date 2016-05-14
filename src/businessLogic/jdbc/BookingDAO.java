package businessLogic.jdbc;

import java.util.ArrayList;
import java.sql.*;
import businessLogic.javaClass.*;

public class BookingDAO {
	public int findbookingID() throws SQLException
	{
		int lastbookingid = 0;
		ArrayList<Room> rooms = new ArrayList<Room>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT booking_id FROM booking ORDER BY booking_id DESC LIMIT 1";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			lastbookingid = rs.getInt("booking_id");
		}
		return lastbookingid;
	}
}
