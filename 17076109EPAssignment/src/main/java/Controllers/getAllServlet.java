package Controllers;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import DB.FilmDAO;
import Models.Film;
import Models.FilmList;

/**
 * Servlet implementation class HomePage
 */
public class getAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String format;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		format = request.getParameter("format");
		System.out.println(format);
		
		// Connect to database and get all films
		FilmDAO dao = new FilmDAO();
		ArrayList<Film> allFilms = dao.getAllFilms();
		String data = "", address = "";
		// Handle JSON
		// Handle XML
		if (format.equals("xml")) {
				try {
				FilmList filmlistmarshal = new FilmList();
				filmlistmarshal.setName("XML Output");
				filmlistmarshal.setFilmList(allFilms);
					
				JAXBContext context = JAXBContext.newInstance(FilmList.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				
				StringWriter sw = new StringWriter();
                m.marshal(filmlistmarshal, sw);
                String xmlString = sw.toString();
                
				address = "xmlhome";
				response.setContentType("application/xml");
				request.setAttribute("xml", xmlString);
				}
				catch(JAXBException e){
					e.printStackTrace();
				}
			}
		else if(format.equals("text")) {
			data = "";
		        for (Film f : allFilms) {
		            data += "<li>Film: " + f.getId() + "<br>Title: " + f.getTitle() + "<br>Year: " + f.getYear() + "<br>Director: "
		                    + f.getDirector() + "<br>Stars: " + f.getStars() + "<br>Review: " + f.getReview() + "</li>";
		        }

			
			
			address = "texthome";
			response.setContentType("text/html");
			request.setAttribute("text", data);
		}
		else{
			Gson gson = new Gson();
			data = gson.toJson(allFilms);
			address = "home";
			response.setContentType("application/json");
			request.setAttribute("json", data);
		}

		// output to browser
		RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/"+address+".jsp");
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
