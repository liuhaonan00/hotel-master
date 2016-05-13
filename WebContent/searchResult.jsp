<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*" %>
<%@ page import = "businessLogic.javaClass.Room" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<Room> rooms = (ArrayList)request.getSession().getAttribute("roomResult");
		
	if(rooms.size()==0){%>
	<p>Sorry, no matching data was found!</p>
	<%}else{ %>
	
	<form action ="control" method="get">
	<table>
	<%
	for (int i =0;i<rooms.size();i++){
		int this_room = rooms.get(i).getRoomId();
	
	%>
	<tr>
	<td>
	<p><%=rooms.get(i).getRoomNo()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getHotelId()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getRoomType()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getPrice()%></p>
	</td>
	<td>
	<p><%=rooms.get(i).getRoomDescription()%></p>
	</td>
	<td>
		<input type="checkbox" name="bookRoom" value="<%=this_room%>">
	</td>
	</tr>
	<%}%>
	</table>
	<input type="submit" value="Book now!"> 
	<input type="hidden" name="op" value="add">
	</form>

<%}%>
</body>
</html>