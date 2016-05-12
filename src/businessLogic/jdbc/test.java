package businessLogic.jdbc;
import java.sql.*;

public class test {
	public static void main(String args[]) {
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		ResultSet rs = o.searchDB(connection, "select username, user_id from user where username = 'haoliu'");
//		o.closeDB(connection);
		
		
		
		try {
			if (!rs.next()) System.out.println("No result!");
			else System.out.println(rs.getString("username") + "," + rs.getInt("user_id"));
			
			while (rs.next()) {
				System.out.println(rs.getString("username") + "," + rs.getInt("user_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("get data error!");
			e.printStackTrace();
		}
		o.closeDB(connection);
	}

}
