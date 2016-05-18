package businessLogic.javaClass;

import java.util.Map;

public class Booking {
   private int bookingID;
   private int userID;
   private String startDate;
   private String endDate;
   // TODO some storage of the individual types and numbers, to match the database's formatting
   private int no_of_room;
   private int extrabed;
   private int hotel_id;
   private float price;
   private int bookingDetailID;
   private Map<String, Integer> roomTypes;

   public int getBookingID() {
      return bookingID;
   }

   public void setBookingID(int bookingID) {
      this.bookingID = bookingID;
   }

   public int getBookingDetailID() {
      return bookingID;
   }

   public void setBookingDetailID(int bookingDetailID) {
      this.bookingDetailID = bookingDetailID;
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

   public int getUserID() {
      return userID;
   }

   public void setUserID(int user) {
      this.userID = user;
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

   public Map<String, Integer> getRoomTypes() {
      return roomTypes;
   }

   public void setRoomTypes(Map<String, Integer> roomTypes) {
      this.roomTypes = roomTypes;
   }

   public String getRoomTypeString() {
      String roomTypeString = "";
      for (String type : roomTypes.keySet()) {
         if (roomTypeString.equals("")) {
            int amount = roomTypes.get(type);
            if (amount > 0) {
               roomTypeString = amount + ' ' + type;
            }
         } else {
            int amount = roomTypes.get(type);
            if (amount > 0) {
               roomTypeString = roomTypeString + ", " + amount + ' ' + type;
            }
         }
      }
      return roomTypeString;
   }
}
