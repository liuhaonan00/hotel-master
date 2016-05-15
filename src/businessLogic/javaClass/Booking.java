package businessLogic.javaClass;

public class Booking {
   // incomplete, but these are the bits I need for manager.jsp
   private String user;
   private String startDate;
   private String endDate;
   private String roomString = ""; // the types of rooms requested, e.g. "2 Single, 1 Queen"

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

   public String getRoomString() {
      return roomString;
   }

   public void setRoomString(String roomString) {
      this.roomString = roomString;
   }
}
