<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Profile</title>
</head>
<body>

<form>
	Username: <br>
	Email: <input type="text" name="email"><br>
	Nickname: <input type="text" name="nickname"><br>
	Firstname: <input type="text" name="firstname"><br>
	Lastname: <input type="text" name="lastname"><br>
	Address: <input type="text" name="address"><br><br>
	<p> Paymrnt Detail</p><br>
	Credit Card Type: 
	<select name="credit_card_type">
		<option value="mastercard">Mastercard</option>
		<option value="visa">VISA</option>
		<option value="amex">American Express</option>
	</select><br>
	Expire Year:
	<select name="credit_card_exp_year">
		<option value="16">2016</option>
		<option value="17">2017</option>
		<option value="18">2018</option>
		<option value="19">2019</option>
		<option value="20">2020</option>
	</select><br>
	Expire Year:
	<select name="credit_card_exp_month">
		<option value="1">Jan</option>
		<option value="2">Feb</option>
		<option value="2">Mar</option>
		<option value="4">Apr</option>
		<option value="5">May</option>
		<option value="6">Jun</option>
		<option value="7">Jul</option>
		<option value="8">Aug</option>
		<option value="9">Sep</option>
		<option value="10">Oct</option>
		<option value="11">Nov</option>
		<option value="12">Dec</option>
	</select><br>
	CVV: <input type="text" name="credit_card_cvv"><br>
	
	
	
	
</form>

</body>
</html>