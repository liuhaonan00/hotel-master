
	<%if (request.getSession().getAttribute("current_user_id") == null) {%>
		<p>Please login in or sign up! </p>
			<form action = "login.jsp">
				<input type="submit"  value="Login" class="button"/>	
			</form>
			<form action = "signup.jsp">
				<input type="submit"  value="Sign up" class="button"/>	
			</form><br>
	<%} else {%>
		<p>Hi <%=(String)request.getSession().getAttribute("current_user")%>:</p>
		<form action = "update.jsp">
			<input type = "hidden" name = "user_id" value =<%=request.getSession().getAttribute("current_user_id")%>>
			<input type="submit"  value="Update profile" class="button"/>	
		</form>
		<form action = "shoppingcart.jsp">
			<input type = "hidden" name = "user_id" value =<%=request.getSession().getAttribute("current_user_id")%>>
			<input type="submit"  value="View shoppingcart" class="button"/>	
		</form><br>
	<%} %>
	
	