package businessLogic.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.javaClass.ShoppingCart;
import businessLogic.javaClass.Room;
public class ShoppingCartDAO {
	public ShoppingCart addToCart(String check_in,String check_out,Room room)
	{
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setcheck_in(check_in);
		shoppingCart.setcheck_out(check_out);
		shoppingCart.sethotel_id(room.getHotelId());
		shoppingCart.setroom_no(room.getRoomNo());
		shoppingCart.setprice(room.getPrice()); //todo offer
		shoppingCart.setroom_id(room.getRoomId());
		
		return shoppingCart;
		
	}
}
