package businessLogic.jdbc;


import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import java.sql.*;

import businessLogic.javaClass.*;

public class UserDAO {
	
	public int findUser(String user,String password) {
		int result = 0; //0 =  success;
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Md5Encryption md5 = new Md5Encryption();
//		password = md5.MD5(password);
		String query = "select * from user where "+ "username = '" + user + "'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		String pass = null;
		try {
			if(!rs.next()) {
				result = 1; // 1 = "error", "No such user"
				System.out.println("No such user!");
			}
			else {
				pass = rs.getString("password");
				if (pass.equals(md5.MD5(password))) {
					result = rs.getInt("user_id"); //return user ID;
					System.out.println("login successful!");
				} else {
					result = 2; //2 = "error", "Wrong pass word"
					System.out.println("wrong password!");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		o.closeDB(connection);
		return result;
	}
	
	//call this only if pass the above one
	//wait... I don't need it
	public String findOneUser(String user,String password) {
		User this_user= new User();
		Md5Encryption md5 = new Md5Encryption();
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select * from user where "+ "username = '" + user + "'";
		System.out.println(query);
		ResultSet rs = o.searchDB(connection, query);
		String pass = null;
		try {
			if(!rs.next()) {
			}
			else {
				pass = rs.getString("password");
				if (pass.equals(md5.MD5(password))) {
					
					this_user.setUserId(rs.getInt("user_id"));
					this_user.setEmail(rs.getString("email"));
					this_user.setUsername(rs.getString("Username"));
					
					
					
				} else {
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		o.closeDB(connection);
		return this_user.getUsername();
	}
	
	public void addUser(String username, String password, String email) 
	{
		MysqlOperation o = new MysqlOperation();
		Md5Encryption md5 = new Md5Encryption();
		PreparedStatement pst = null;
		try {
			Connection connection = o.DBConnect();
			String sqlInsert = "INSERT INTO user (username, password, email) VALUES (?,?,?)";
			pst = connection.prepareStatement(sqlInsert);
			pst.setString(1, username);
			pst.setString(2, md5.MD5(password));
			pst.setString(3, email);
//			full set
//			String username, String password,
//			String nickname, String firstname, String lastname, String email,
//			String birthday, String address, String credit_card_type, String credit_card_number,String credit_card_exp_month,String credit_card_exp_year, String credit_card_cvv
//			pst.setString(1, username);
//			pst.setString(2, password);
//			pst.setString(3, email);
//			pst.setString(4, nickname);
//			pst.setString(5, firstname);
//			pst.setString(6, lastname);
//			pst.setString(7, address);
//			pst.setString(8, credit_card_type);
//			pst.setString(9, credit_card_number);
//			pst.setString(10, credit_card_exp_month);
//			pst.setString(11, credit_card_exp_year);
//			pst.setString(12, credit_card_cvv);
//			pst.setString(13, "0");
			pst.executeUpdate();
			pst.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int checkDuplicate(String username,String email){
		int pass = 1;
		
		
		try {
			MysqlOperation o = new MysqlOperation();
			Connection connection = o.DBConnect();
			
			String query = "select COUNT(*) AS rowcount from user where username = '" + username + "'";
			System.out.println(query);
			ResultSet rs1 = o.searchDB(connection, query);
			rs1.next();
			int count1 = rs1.getInt("rowcount");
			
			query = "select COUNT(*) AS rowcount from user where email = '" + email + "'";
			System.out.println(query);
			ResultSet rs2 = o.searchDB(connection, query);
			rs2.next();
			int count2 = rs2.getInt("rowcount");
			
			pass=pass-count1-count2;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		//if < 1 means at least one of above has duplicate
		return pass;
	}
	
	public void updateUser(String userid,String username, String password, String email) 
	{
		MysqlOperation o = new MysqlOperation();
		PreparedStatement pst = null;
		try {
			Connection connection = o.DBConnect();
			String sqlInsert = "update user ( password, email) VALUES (?,?,?) where user_id ="+userid;
			pst = connection.prepareStatement(sqlInsert);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, email);
//			full set
//			String username, String password,
//			String nickname, String firstname, String lastname, String email,
//			String birthday, String address, String credit_card_type, String credit_card_number,String credit_card_exp_month,String credit_card_exp_year, String credit_card_cvv
//			pst.setString(1, username);
//			pst.setString(2, password);
//			pst.setString(3, email);
//			pst.setString(4, nickname);
//			pst.setString(5, firstname);
//			pst.setString(6, lastname);
//			pst.setString(7, address);
//			pst.setString(8, credit_card_type);
//			pst.setString(9, credit_card_number);
//			pst.setString(10, credit_card_exp_month);
//			pst.setString(11, credit_card_exp_year);
//			pst.setString(12, credit_card_cvv);
//			pst.setString(13, "0");
			pst.executeUpdate();
			pst.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
