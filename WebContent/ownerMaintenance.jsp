<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<String> occs = (ArrayList) request.getAttribute("occupied");
	ArrayList<String> maints = (ArrayList) request.getAttribute("maintenance");
	ArrayList<String> rooms = new ArrayList(
			Arrays.asList("101", "102", "201", "202", "301", "302", "401", "501"));
	String hotelName = (String) request.getAttribute("hotel_name");
	pageContext.setAttribute("occs", occs);
	pageContext.setAttribute("maints", maints);
	pageContext.setAttribute("rooms", rooms);
	pageContext.setAttribute("hotel", hotelName);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Room Maintenance:</title>
</head>
<body>

	<h2>
		Room Statuses:
		<c:out value="${hotel}" />
	</h2>
	<table>
		<tr>
			<th>Room Number</th>
			<th>Status</th>
		</tr>
		<c:forEach items="${rooms}" var="room">
			<tr>
				<td><c:out value="${room}"></c:out></td>
				<td><c:choose>
						<c:when test="${fn:contains(occs, room)}">
							<c:out value="Occupied" />
						</c:when>
						<c:when test="${fn:contains(maints, room)}">
							<c:out value="Under Maintenance" />
						</c:when>
						<c:otherwise>Available
	</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
	
	<h2> Set Room for Maintenance</h2>
	<form action="maintenance">
  	<select name="roomRepair">
    <option value="101">101</option>
    <option value="102">102</option>
    <option value="201">201</option>
    <option value="202">202</option>
    <option value="301">301</option>
    <option value="302">302</option>
    <option value="401">401</option>
    <option value="501">501</option>
  </select>
  <input type="hidden" name="hidden" value=hotelName>
  <input type="submit" value="Submit">
</form>
	
</body>
</html>