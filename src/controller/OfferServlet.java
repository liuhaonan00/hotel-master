package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/offers")
public class OfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OfferServlet() {
        super();
    }
  //INSERT INTO offer (offer_price, hotel_id, room_type, start, end) VALUES (?,?,?,?,?)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hotel_id = ""; 
		hotel_id = request.getParameter("offerHotel");
		if ( hotel_id != null && hotel_id.length() > 0){
			RequestDispatcher rd = request.getRequestDispatcher("/ownerOffers.jsp");
			
			
			
			rd.forward(request, response);
		}

	}
}


