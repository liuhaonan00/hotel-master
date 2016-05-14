package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import businessLogic.jdbc.*;
import businessLogic.javaClass.*;

public class AvailabilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AvailabilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/owner.jsp");
		int occupancy[] = {0,0,0,0,0,0,0,0,0};
		MysqlOperation o = new MysqlOperation();
		Connection connection = o.DBConnect();
		String query = "select hotel_id from booking";
		ResultSet rs = o.searchDB(connection, query);
		
		try{
		while (rs.next()){
			String f = rs.getString("hotel_id");
			int i = Integer.valueOf(f);
			occupancy[i]++;
		}
	}catch (SQLException e){
		e.printStackTrace();
	}
		HashMap<String, Integer> occs = new HashMap<String, Integer>();
		occs.put("Rampage-SYD-1", occupancy[1]);
		occs.put("Rampage-SYD-2", occupancy[2]);
		occs.put("Rampage-MEL-1", occupancy[3]);
		occs.put("Rampage-MEL-2", occupancy[4]);
		occs.put("Rampage-BRI-1", occupancy[5]);
		occs.put("Rampage-ADE-1", occupancy[6]);
		occs.put("Rampage-PER-1", occupancy[7]);
		occs.put("Rampage-HOB-1", occupancy[8]);
		
		request.setAttribute("occupancies", occs);
		rd.forward(request, response);
    }
}
