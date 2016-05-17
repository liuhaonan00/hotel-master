<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>
	    <h2>Search for rooms</h2>
		Search by:
		<br> <br>
		<form action='search' method='POST'>
		<table>
		<tr>
			<td align="left">Check in time : </td> <td align="left"><input type="text" name="check_in" /></td>
		</tr>
		<tr>
			<td align="left">Check out time : </td> <td align="left"><input type="text" name="check_out" /></td>
		</tr>
		<tr>
			<td align="left">City : </td>
			<td><select name ="city">
  					<option value="Sydney">Sydney</option>
  					<option value="Melbourne">Melbourne</option>
  					<option value="Adelaide">Adelaide</option>
  					<option value="Brisbane">Brisbane</option>
				</select></td>
		</tr>
		<tr>
			<td align="left">Price(Less Than) : </td> <td align="left"><input type="text" name="price" /></td>
		</tr>
		</table>
		<p></p>
		<input type='submit' value='Search' class="button">
		</form>
		<br>
</body>
</html>