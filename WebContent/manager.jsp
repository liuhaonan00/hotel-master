<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager Interface</title>
</head>
<body>

   <h2>Managing hotel: ${manager_hotel.hotelName}</h2>
   <em>${manager_message}</em>

   <form action="manage" method="POST">
      <h3>Occupied rooms:</h3>
      <table>
         <tr>
            <!-- Room number, Room type, Empty this room -->
            <th>Room number</th>
            <th>Room type</th>
            <th>Empty this room</th>
         </tr>
         <c:forEach var="room" items="${manager_occupancy}">
            <tr>
               <td>${room.roomNo}</td>
               <td>${room.roomType}</td>
               <td><input type="checkbox" name="clear_rooms"
                  value='${room.roomId}' /></td>
            </tr>
         </c:forEach>
      </table>
      <input type="submit" value="Mark selected rooms as available" />
   </form>

   <h3>Pending Bookings:</h3>
   <table>
      <tr>
         <!-- Booking user, Start date, End date, Rooms -->
         <th>Customer</th>
         <th>Start date</th>
         <th>End date</th>
         <th>Rooms requested</th>
         <th>Assign rooms</th>
      </tr>
      <c:forEach var="booking" items="${manager_empty_bookings}">
         <tr>
            <td>${booking.user}</td>
            <td>${booking.startDate}</td>
            <td>${booking.endDate}</td>
            <td>${booking.roomString}</td>
            <td>
               <form action="assignrooms" method="POST">
                  <input type="hidden" name="id_to_book"
                     value="${booking.bookingID}" /> <input
                     type="submit" value="Fill rooms for this booking" />
               </form>
            </td>
         </tr>
      </c:forEach>
   </table>

   <h3>Filled Bookings:</h3>
   <table>
      <tr>
         <!-- Booking user, Start date, End date, Rooms -->
         <th>Customer</th>
         <th>Start date</th>
         <th>End date</th>
         <th>Rooms assigned</th>
      </tr>
      <c:forEach var="booking" items="${manager_filled_bookings}">
         <tr>
            <td>${booking.user}</td>
            <td>${booking.startDate}</td>
            <td>${booking.endDate}</td>
            <td>TODO</td>
         </tr>
      </c:forEach>
   </table>

   <%--  TODO future bookings? --%>
   <%--  TODO logout --%>

</body>
</html>