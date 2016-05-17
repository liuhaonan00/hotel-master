package businessLogic.jdbc;

import java.util.ArrayList;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
	
	public ArrayList<Booking> currentUnassignedBooking(int hotel_id) throws SQLException{
		//get current date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		
		ArrayList<Booking> bookings= new ArrayList<Booking>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * from booking join booking_detail on booking.booking_id = booking_detail.booking_id	 where booking.assign=0 and booking_detail.hotel_id ="+hotel_id+" and booking.checkin <='"+date1+"' and booking.checkout>='"+date1+"'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Booking booking = new Booking();
			booking.setBookingID(rs.getInt(1));
			booking.setBookingDetailID(rs.getInt(9));
			booking.setEndDate(rs.getString(3));
			booking.setStartDate(rs.getString(4));
			booking.setExtrabed(rs.getInt(14));
			booking.setHotel_id(rs.getInt(11));
			booking.setPrice(rs.getFloat(8));
			booking.setNo_of_room(rs.getInt(13));
			booking.setRoomTypeString(rs.getString(12));
			booking.setUser(rs.getString(2));
			bookings.add(booking);
		}
		return bookings;
		
	}
	
	public ArrayList<Booking> currentAssignedBooking(String start,String end,int hotel_id) throws SQLException{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String date1 = dateFormat.format(date);
		
		ArrayList<Booking> bookings= new ArrayList<Booking>();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "SELECT * from booking join booking_detail on booking.booking_id = booking_detail.booking_id	 where booking_detail.assign=1 and booking_detail.hotel_id ="+hotel_id+" and booking.checkin <='"+date1+"' and booking.checkout>='"+date1+"'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		while(rs.next()){
			Booking booking = new Booking();
			booking.setBookingID(rs.getInt(1));
			booking.setBookingDetailID(rs.getInt(9));
			booking.setEndDate(rs.getString(3));
			booking.setStartDate(rs.getString(4));
			booking.setExtrabed(rs.getInt(14));
			booking.setHotel_id(rs.getInt(11));
			booking.setPrice(rs.getFloat(8));
			booking.setNo_of_room(rs.getInt(13));
			booking.setRoomTypeString(rs.getString(12));
			booking.setUser(rs.getString(2));
			bookings.add(booking);
		}
		return bookings;
		
		
	}
	
	public void markBookingAssign(int bookingDetailID){
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "UPDATE booking_detail SET assign=1 WHERE booking_detail_id="+bookingDetailID;
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
	}
	
}
