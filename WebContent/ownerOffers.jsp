<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@  taglib  prefix="c"   uri="http://java.sun.com/jsp/jstl/core"  %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
ArrayList<String> rooms = new ArrayList(Arrays.asList("101", "102", "201", "202", "301", "302", "401", "501"));
String hotelName = (String) request.getAttribute("hotelName");
pageContext.setAttribute("hotel", hotelName);
%>
<html>
<head>
<script type="text/javascript">
function isNumberKey(evt){
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode != 46 &&(charCode < 48 || charCode > 57)))
        return false;
    return true;
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Special Offers</title>
</head>
<body>

<h1>Special Offers: <c:out value="${hotel}" /></h1>

<h2>Create Offer</h2>
<form action = "offers">
			<INPUT TYPE="radio" NAME="roomType" VALUE="Single" CHECKED>
             Single<BR>
            <INPUT TYPE="radio" NAME="roomType" VALUE="Twin Bed">
             Twin Bed<BR>
            <INPUT TYPE="radio" NAME="roomType" VALUE="Queen">
             Queen<BR>
            <INPUT TYPE="radio" NAME="roomType" VALUE="Executive">
            Executive<BR>
            <INPUT TYPE="radio" NAME="roomType" VALUE="Suite">
            Suite<BR>
            
            Price <input name="price" type="text" onkeypress="return isNumberKey(event)"/><BR>
            Start Date <input type="date" name="startDate"><BR>
            End Date <input type="date" name="endDate"><BR>

            <input type="hidden" name="hidden" value=<c:out value='${hotel}'/>>
  <input type="submit" value="Submit">

</form>







</body>
</html>