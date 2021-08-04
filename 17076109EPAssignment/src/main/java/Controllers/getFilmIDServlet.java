package Controllers;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DB.FilmDAO;
import Models.Film;

/**
 * Servlet implementation class getFilmIDServlet
 */
public class getFilmIDServlet extends HttpServlet {
	String title;
	String format;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int filmID = Integer.parseInt(request.getParameter("film"));
		format = request.getParameter("format");
		System.out.println(title);

		// Connect to database and get all films
		FilmDAO dao = new FilmDAO();
		Film filmByID = dao.getFilmByID(filmID);
		String data = "";
		// Handle requested data type
		Gson gson = new Gson();
		data = gson.toJson(filmByID);
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
