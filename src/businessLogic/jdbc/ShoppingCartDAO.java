package businessLogic.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.ArrayList;

import businessLogic.javaClass.ShoppingCart;
import businessLogic.javaClass.Room;
import businessLogic.javaClass.Search;
public class ShoppingCartDAO {
	public ShoppingCart addToCart(String check_in,String check_out,Search room)
	{
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setcheck_in(check_in);
		shoppingCart.setcheck_out(check_out);
		shoppingCart.sethotel_id(room.getHotel_id());
		shoppingCart.setroomType(room.getRoomtype());
		shoppingCart.setprice(room.getPrice()); //todo offer
		shoppingCart.setno(room.getNo());
		return shoppingCart;
		
	}
	//insert to database
	public void insert(ArrayList<ShoppingCart> rooms,int user_id)
	{
		float total_price = 0;
		MysqlOperation o = new MysqlOperation();
		for (int i = 0; i < rooms.size(); i++) {
			total_price =total_price+ rooms.get(i).getprice(); //todo: add discount
		}
		
	//	insertBooking(user_id,Room,total_price);
		
	}
	
	public static void insertBooking(int user_id,ShoppingCart Room,float total_price){
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		int randomPIN = (int)(Math.random()*9000)+1000;
		String query = "INSERT INTO booking (user_id, checkin, checkout,pin,number_of_room,total_price) VALUES ("+user_id+","+Room.getcheck_in()+","+Room.getcheck_out()+","+randomPIN+","+Room.getno()+","+total_price+")";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
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
	
}
