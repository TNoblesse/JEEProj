package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import main.Seance;

public class Seances {
	public ArrayList<Seance> recupererSeances(){
		ArrayList<Seance> listeSeances = new ArrayList<Seance>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			
			resultat = statement.executeQuery("SELECT dateSeanced, dateSeancef, numSeance FROM Seance;");
			
			while(resultat.next()) {
				String dateSeanced = resultat.getString("dateSeanced");
				String dateSeancef = resultat.getString("dateSeancef");
				int numSeance = resultat.getInt("numSeance");
				
				Seance seance = new Seance(dateSeanced,dateSeancef,numSeance);
				listeSeances.add(seance);
			}
		}catch(Exception e) {}
		
		return listeSeances;
	}
}

