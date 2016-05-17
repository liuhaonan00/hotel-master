package businessLogic.javaClass;

public class Booking {
   private int bookingID;
   private String user;
   private String startDate;
   private String endDate;
   // TODO some storage of the individual types and numbers, to match the database's formatting
   private String roomTypeString = ""; // the types of rooms requested, e.g. "2 Single, 1 Queen"
   private int no_of_room;
   private int extrabed;
   private int hotel_id;
   private float price;
   
   
   public int getBookingID() {
      return bookingID;
   }

   public void setBookingID(int bookingID) {
      this.bookingID = bookingID;
   }

   public int getNo_of_room() {
	      return no_of_room;
	   }

   public void setNo_of_room(int no_of_room) {
	   this.no_of_room = no_of_room;
   }
   public int getExtrabed() {
	      return extrabed;
	   }
   public void setExtrabed(int extrabed) {
	   this.extrabed = extrabed;
   }
   
   public float getPrice() {
	      return price;
	   }
   public void setPrice(float price) {
	   this.price = price;
   }
   public int getHotel_id() {
	      return hotel_id;
	   }
   public void setHotel_id(int hotel_id) {
	   this.hotel_id = hotel_id;
   }
   
   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getStartDate() {
      return startDate;
   }

   public void setStartDate(String startDate) {
      this.startDate = startDate;
   }

   public String getEndDate() {
      return endDate;
   }

   public void setEndDate(String endDate) {
      this.endDate = endDate;
   }

   public String getRoomTypeString() {
      // TODO calculate on the fly from the actual types?
      return roomTypeString;
   }

   public void setRoomTypeString(String roomTypeString) {
      // TODO remove in favour of setting the actual types
      this.roomTypeString = roomTypeString;
   }
}
