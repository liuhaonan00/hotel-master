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
      <!--  TODO for each type of room, show desired number, list available rooms -->
   </form>

   <p>
      <a href="manage">Back to Manager Home</a>
   </p>
   <%--  TODO logout --%>

</body>
</html>