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
	
	public static void insertBooking(int user_id,ArrayList<Room> room,String check_in,String check_out){
		float total_price = 0;
		MysqlOperation o = new MysqlOperation();
		for (int i = 0; i < room.size(); i++) {
			total_price =total_price+ room.get(i).getPrice(); //todo: add discount
		}
			PreparedStatement pst = null;
			try {
				Connection connection = o.DBConnect();
				String sqlInsert = "INSERT INTO booking (user_id, checkin, checkout,total_price) VALUES (?,?,?,?)";
				pst = connection.prepareStatement(sqlInsert);
				pst.setString(1, ""+user_id);
				pst.setString(2, check_in);
				pst.setString(3, check_out);
				pst.setString(4, ""+total_price);
				pst.executeUpdate();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	public static void insertRoom_status(int booking_id,ArrayList<Room> room,String check_in,String check_out){
		
		for (int i = 0; i < room.size(); i++) {
			MysqlOperation o = new MysqlOperation();
			PreparedStatement pst = null;
			try {
				Connection connection = o.DBConnect();
				String sqlInsert = "INSERT INTO booking (hotel_id, room_id, booking_id,status,start_date,end_date,price) VALUES (?,?,?,?,?,?,?)";
				pst = connection.prepareStatement(sqlInsert);
				pst.setString(1, ""+room.get(i).getHotelId());
				pst.setString(2, ""+room.get(i).getRoomId());
				pst.setString(3, ""+booking_id);
				pst.setString(4, "Booked");
				pst.setString(5, check_in);
				pst.setString(6, check_out);
				pst.setString(7, ""+room.get(i).getPrice()); // todo:offer on price
				pst.executeUpdate();
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
		}
	
	public ArrayList<Booking> currentUnassignedBooking(String start,String end,int hotel_id){
		
		ArrayList<Booking> bookings= new ArrayList<Booking>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Booking booking = new Booking();
			booking.setBookingID(bookingID);
			booking.setEndDate();
			booking.setStartDate();
			booking.setExtrabed(extrabed);
			booking.setHotel_id(hotel_id);
			booking.setPrice(price);
			booking.setNo_of_room(no_of_room);
			booking.setRoomTypeString(roomTypeString);
			booking.setUser(user);
			bookings.add(booking);
		}
		return bookings;
		
	}
	
	public ArrayList<Booking> currentAssignedBooking(String start,String end,int hotel_id){
		
		
		return null;
		
	}
	
	public void markBookingAssign(){
		
	}
	
}
