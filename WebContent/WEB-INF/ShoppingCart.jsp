<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.ServletException"%>

<%@page import = "businessLogic.jdbc.*" %>
<%@page import = "businessLogic.javaClass.*" %>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShoppingCart</title>
</head>
<body>
 <H2>My Shopping Cart</H2>
    
    <%
    double totalprice = 0;
        ArrayList<ShoppingCart> list = (ArrayList)request.getSession().getAttribute("ShoppingCart");
        if(list.size() == 0) { 
    %><p>Shopping Cart is Empty!</p><% 
    } else {
    	%><table><%
    	for (int i =0;i<list.size();i++){%>
    <tr>
    <td>
    <p><%=list.get(i).getcheck_in()%></p>
    </td>
    <td>
    <p><%=list.get(i).getcheck_out()%></p>
    </td>
    <td>
    <p><%=list.get(i).gethotel_id()%></p>
    </td>
    <td>
    <p><%=list.get(i).getroom_no()%></p>
    </td>
    <td>
    <p><%=list.get(i).getprice()%></p>
    </td>
    <td>
    <input type="checkbox" name="del" value="<%=i%>">
    </td>
    </tr>
    <% } %>

	<table><%
    }%>
</body>
</html>