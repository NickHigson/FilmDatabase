package Controllers;

import DB.FilmDAO;

public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FilmDAO dao = new FilmDAO();
		
		System.out.println(dao.getAllFilms());
		System.out.println("?");
	}

}
