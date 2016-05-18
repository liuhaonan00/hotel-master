package businessLogic.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import businessLogic.javaClass.*;
public class ReviewDAO {

	public ArrayList<Booking> getDetailbyID(String booking_id, String pin) throws SQLException{
		ArrayList<Booking> books= new ArrayList();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT pin FROM booking where booking_id="+booking_id+"and pin="+pin;
		System.out.println(query);
		
		ResultSet rs = o.searchDB(connection, query);
		
		while (rs.next()){
			Booking booking = new Booking();
			booking.setHotel_id(rs.getInt("hotel_id"));
			booking.setNo_of_room(rs.getInt("num_of_room"));
			booking.setRoomTypeString(rs.getString("room_type"));
			booking.setStartDate(rs.getString("checkin"));
			booking.setEndDate(rs.getString("checkout"));
			booking.setExtrabed(rs.getInt("extra_bed"));
			books.add(booking);
			
		}
		return books;
		
	}
}
