<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Room Maintenance</title>
</head>
<body>

<% ArrayList<String> occs = (ArrayList) request.getAttribute("occupied");
for (String i : occs){
	out.println(i);
}
%>


</body>
</html>