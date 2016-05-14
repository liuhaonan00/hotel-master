<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.HashMap" %>
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

<%
out.println(occs.get("Rampage-SYD-1"));


%>


</body>
</html>