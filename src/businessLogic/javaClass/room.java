package businessLogic.javaClass;

public class Room {
	private int roomID;
	private int hotelID;
	private String roomType;
	private String roomNo;
	private float price;
	private String roomDescription;
	
	public Room() {
		this.roomID = 0;
		this.hotelID = 0;
		this.roomType = "";
		this.roomNo = "";
		this.price = 0;
		this.roomDescription = "";
	}
	
	public int getRoomId() {
		return roomID;
	}
	public void setRoomId(int RoomID) {
		this.roomID = RoomID;
	}
	
	public int getHotelId() {
		return hotelID;
	}
	public void setHotelId(int HotelID) {
		this.hotelID = HotelID;
	}
	
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String RoomType) {
		this.roomType = RoomType;
	}
	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String RoomNo) {
		this.roomNo = RoomNo;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float Price) {
		this.price = Price;
	}

	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String RoomDescription) {
		this.roomDescription = RoomDescription;
	}
}
