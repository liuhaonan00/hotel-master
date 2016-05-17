package businessLogic.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.javaClass.ShoppingCart;
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
	
}
