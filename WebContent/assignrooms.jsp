<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager Interface: Assign rooms</title>
</head>
<body>

   <h2>Booking for ${assign_user}</h2>
   <em>${assign_message}</em>

   <form action="assignrooms" method="POST">
      <c:forEach var="type" items="${assign_requested_types}">
      <h3>${type.typeName}</h3>
         <%--
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
--%>
      </c:forEach>
   </form>

   <p>
      <a href="manage">Back to Manager Home</a>
   </p>
   <%--  TODO logout --%>

</body>
</html>