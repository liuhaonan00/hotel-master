package businessLogic.javaClass;

public class Booking {
   private int bookingID;
   private String user;
   private String startDate;
   private String endDate;
   // TODO some storage of the individual types and numbers, to match the database's formatting
   private String roomTypeString = ""; // the types of rooms requested, e.g. "2 Single, 1 Queen"

   public int getBookingID() {
      return bookingID;
   }

   public void setBookingID(int bookingID) {
      this.bookingID = bookingID;
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
