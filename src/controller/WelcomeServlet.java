package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import businessLogic.javaClass.Room;
import businessLogic.jdbc.RoomDAO;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 * Servlet implementation class welcome
 */
@WebServlet(name = "WelcomeServlet", urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("System started!");
		RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
		
		//create random room list and send to login.jsp for display
		RoomDAO roomdao = new RoomDAO();
		ArrayList<Room> roomList = new ArrayList<>();
		try {
		roomList = roomdao.randomRoom(3);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	request.setAttribute("roomList", roomList);
	rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
