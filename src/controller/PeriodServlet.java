package controller;
import java.io.IOException;

import businessLogic.javaClass.*;
import businessLogic.jdbc.*;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/peakperiods")
public class PeriodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public PeriodServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	RequestDispatcher rd = request.getRequestDispatcher("availability");
    	if(request.getParameter("pName") != null){
    	String pName = request.getParameter("pName");
    	String StringIncrease = request.getParameter("pIncrease");
    	String pStart = request.getParameter("pStart");
    	String pEnd = request.getParameter("pEnd");
    	
    	java.sql.Date dateStart = java.sql.Date.valueOf(pStart);
		java.sql.Date dateEnd = java.sql.Date.valueOf(pEnd);
		int pIncrease = Integer.valueOf(StringIncrease);
		
		try{
		
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		Statement stmt = connection.createStatement();
		String insert = "INSERT INTO peakperiod (period_name, price_increase, start, end) VALUES ('"
				+ pName + "'," + pIncrease + ",'" + dateStart + "','" + dateEnd + "')";
		stmt.executeUpdate(insert);
		rd.forward(request, response);
		
		}catch (Exception e){
			e.printStackTrace();
		}
    	}
		
		String rName = "";
		if(request.getParameter("removeName") != null){
			try{
			rName = request.getParameter("removeName");
			MysqlOperation o = new MysqlOperation();
			Connection connection = o.DBConnect();
			Statement stmt = connection.createStatement();
			String delete = "DELETE FROM peakperiod WHERE period_name = '" + rName + "'";
			stmt.executeUpdate(delete);
			rd.forward(request, response);
			}catch (Exception e){
				e.printStackTrace();
			}
		};
		 
    
}
}