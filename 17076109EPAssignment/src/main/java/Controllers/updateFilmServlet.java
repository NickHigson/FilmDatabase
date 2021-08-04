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

public class updateFilmServlet extends HttpServlet {
	String title;
	String format;
	String idString;
	String yearString;
	int id;
	int year;
	String review;
	String stars;
	String director;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		idString = request.getParameter("id");
		yearString = request.getParameter("year");
		

		
		idString.replaceAll("[^0-9]","");
		String idReady = idString.replaceAll("[^0-9]", "");
		yearString.replaceAll("[^0-9]","");
		String yearReady = yearString.replaceAll("[^0-9]", "");
		
		title = request.getParameter("title");
		director = request.getParameter("director");
		stars = request.getParameter("stars");
		review = request.getParameter("review");
		id = Integer.parseInt(idReady);
		year = Integer.parseInt(yearReady);
		
		System.out.println(title+director+stars+review+idString+yearString);
		
		format = request.getParameter("format");
		System.out.println(title);
		
		// Connect to database and get all films
		FilmDAO dao = new FilmDAO();
		
		dao.updateFilm(id, title, year, director, stars, review);
		
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
