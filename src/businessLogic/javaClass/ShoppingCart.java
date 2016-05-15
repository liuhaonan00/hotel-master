package businessLogic.javaClass;

public class ShoppingCart {
	private int hotel_id;
	private int room_id;
	private String check_in;
	private String check_out;
	private float price;
	
	public ShoppingCart() {
		
	}	
	public int getroom_id() {
		return room_id;
	}
	public void setroom_id(int room_id) {
		this.room_id = room_id;
	}
	public int gethotel_id() {
		return hotel_id;
	}
	public void sethotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getcheck_in() {
		return check_in;
	}
	public void setcheck_in(String check_in) {
		this.check_in = check_in;
	}
	public String getcheck_out() {
		return check_out;
	}
	public void setcheck_out(String check_out) {
		this.check_out = check_out;
	}
	public float getprice() {
		return price;
	}
	public void setprice(float price) {
		this.price = price;
	}
	
}
