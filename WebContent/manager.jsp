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

   <h2>Managing hotel: ${manager_hotel.name}</h2>
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

   <form action="assignroom" method="POST">
      <h3>Current Bookings:</h3>
      <table>
         <tr>
            <!-- Booking user, Start date, End date, Rooms -->
            <th>Booking user</th>
            <th>Start date</th>
            <th>End date</th>
            <th>Rooms requested</th>
            <th>Assign rooms</th>
         </tr>
         <c:forEach var="booking" items="${manager_bookings}">
            <tr>
               <td>${booking.user}</td>
               <td>${booking.startDate}</td>
               <td>${booking.endDate}</td>
               <td>${booking.roomString}</td>
               <td>TODO submit button to edit this booking</td>
            </tr>
         </c:forEach>
      </table>
   </form>

   <%--  TODO future bookings? --%>
   <%--  TODO logout --%>

</body>
</html>