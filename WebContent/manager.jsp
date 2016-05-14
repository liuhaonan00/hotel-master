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

   <!-- Important TODO: password protect this with some stored password -->
   <!-- manager and owner servlets should redirect to staff servlet without a valid staff session -->
   
   <form action="assignroom" method="POST">
      <h2>Managing hotel: ${hotel_name}</h2>
      <h3>Bookings:</h3>
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

After authentication, the manager is presented with a page that enables them to view all the rooms occupied and bookings made by customer
The manager can assign a room to a booking; they select a booking, view the appropriate rooms available (e.g. if a booking is for 2 twin rooms, then all the twin rooms available would show up), and assigns rooms to the booking as per the number specified. You could imagine this happens when the customer shows up to check-in at the hotel.
When a customer checks out, the manager returns the occupied room to the available list.

</body>
</html>