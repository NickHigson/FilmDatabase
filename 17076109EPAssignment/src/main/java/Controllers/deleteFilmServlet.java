package Controllers;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.FilmDAO;

public class deleteFilmServlet extends HttpServlet {
	
	String idString;
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		idString = request.getParameter("id");	
		idString.replaceAll("[^0-9]","");
		String idReady = idString.replaceAll("[^0-9]", "");
		id = Integer.parseInt(idReady);

		
		// Connect to database and get all films
		FilmDAO dao = new FilmDAO();
		
		dao.deleteFilmByID(id);
		
		String data = "";
		String address = "home";

		// output to browser
		response.setContentType("application/json");
		request.setAttribute("json", data);
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("./jsp/"+address+".jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		doGet(request, response);
	}

}
