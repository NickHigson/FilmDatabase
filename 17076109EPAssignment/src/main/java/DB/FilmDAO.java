package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Film;

import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "root";
    String password = "nickroot";
    // Note none default port used, 6306 not 3306
    //String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;
   
    String url = String.format("jdbc:mysql://%s/%s?cloudSqlInstance=%s", "35.246.66.130", "films", "clean-respect-301812:europe-west2:epassignmentdb");



    
	public FilmDAO() {}

	
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }
   
   public ArrayList<Film> getFilm(String Film){
	   
	    ArrayList<Film> gotFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films WHERE title LIKE '%"+Film+"%';";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	gotFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return gotFilms;
   }

   public Film getFilmByID(int id){
	   
		openConnection();
		oneFilm=null;
	    // Create select statement and execute it
		try{
		    String selectSQL = "select * from films where id="+id;
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return oneFilm;
   }
   
   public int insertFilm(String title, int year, String director, String stars, String review){
	   int rs1 = 0;
		openConnection();
	    // Create select statement and execute it
		try{
		    String insertSQL = "INSERT INTO films (title, year, director, stars, review) VALUES ('"+title+"','"+year+"','"+director+"','"+stars+"','"+review+"');";
		    rs1 = stmt.executeUpdate(insertSQL);

	    // Retrieve the results
		    System.out.println("Inserted "+rs1+" records.");
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	return rs1;
   }
   
   public int updateFilm(int id, String title, int year, String director, String stars, String review){
	   int rs1 = 0;
		openConnection();
	    // Create select statement and execute it
		try{
		    String updateSQL = "UPDATE films SET id = '"+id+"', title = '"+title+"', year = '"+year+"', director = '"+year+"', stars = '"+stars+"', review = '"+review+"' WHERE id = '"+id+"';";
		    rs1 = stmt.executeUpdate(updateSQL);

	    // Retrieve the results
		    System.out.println("Updated "+rs1+" records.");
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	return rs1;
   }
   
   public int deleteFilmByID(int id){
	   int rs1 = 0;
		openConnection();
	    // Create select statement and execute it
		try{
		    String updateSQL = "DELETE FROM films WHERE id = '"+id+"';";
		    rs1 = stmt.executeUpdate(updateSQL);

	    // Retrieve the results
		    System.out.println("Deleted "+rs1+" records.");
		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	return rs1;
   }
   
   
   
   
}
