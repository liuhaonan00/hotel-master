package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessLogic.javaClass.Room;
import businessLogic.jdbc.RoomDAO;


@WebServlet(name = "SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String[] bookroom = request.getParameterValues("bookroom");
		
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String check_in = "'"+request.getParameter("check_in")+"'";
		String check_out = "'"+request.getParameter("check_out")+"'";
		String city = request.getParameter("city");
		int price = Integer.parseInt(request.getParameter("price"));
		RoomDAO roomDAO = new RoomDAO();
		ArrayList<Room> rooms;
		try {
			
			rooms = roomDAO.findAllRoom(check_in,check_out,city,price);
			request.setAttribute("roomResult", rooms);
			request.getRequestDispatcher("searchResult.jsp").forward(request,response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
