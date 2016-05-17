<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	HashMap<String, Integer> occs = (HashMap<String, Integer>) request.getAttribute("occupancies"); 
	pageContext.setAttribute("occupancies", occs);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manager Page</title>
</head>
<body>

<h2> Room availabilities</h2>
 <table>
	<tr>
		<th>Hotel</th>
		<th>Occupied/Maintenance</th>
		<th>Available Rooms</th>
	</tr>
  <c:forEach items="${occupancies}" var="info">
    <tr>
      <td><c:out value="${info.getKey()}" /></td>
      <td><c:out value="${info.getValue()}" /></td>
      <td><c:out value="${8 - info.getValue()}" /></td>
    </tr>
    </c:forEach>
</table>

<h2>Manage Room Maintenance</h2>
<form action="maintenance">
  <select name="hotel">
    <option value="1">Rampage-SYD-1</option>
    <option value="2">Rampage-SYD-2</option>
    <option value="3">Rampage-MEL-1</option>
    <option value="4">Rampage-MEL-2</option>
    <option value="5">Rampage-BRI-1</option>
    <option value="6">Rampage-ADE-1</option>
    <option value="7">Rampage-PER-1</option>
    <option value="8">Rampage-HOB-1</option>
  </select>
  <input type="submit" value="Submit">
</form>

<h2>Manage Special Offers</h2>
<form action="offers">
  <select name="offerHotel">
    <option value="1">Rampage-SYD-1</option>
    <option value="2">Rampage-SYD-2</option>
    <option value="3">Rampage-MEL-1</option>
    <option value="4">Rampage-MEL-2</option>
    <option value="5">Rampage-BRI-1</option>
    <option value="6">Rampage-ADE-1</option>
    <option value="7">Rampage-PER-1</option>
    <option value="8">Rampage-HOB-1</option>
  </select>
  <input type="submit" value="Submit">
</form>





</body>
</html>