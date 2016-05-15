package businessLogic.jdbc;
import java.sql.*;

public class test {
	public static void main(String args[])  {
		UserDAO u = new UserDAO();
		int result = u.verifyEmail(2);
		System.out.println(result);
		
	}

}
