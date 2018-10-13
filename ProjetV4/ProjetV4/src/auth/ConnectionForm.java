package auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

public class ConnectionForm {
	private String result;
	
	public ConnectionForm() {
	}
	
	public void verifierIdentifiants(HttpServletRequest request) {
		String nom = request.getParameter("nom");
		String pass = request.getParameter("pass");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			
			resultat = statement.executeQuery("SELECT * FROM Identifiant WHERE login='"+nom+"' AND pass='"+pass+"' ;");
			
			if(resultat.next()) {
				this.setResult(nom+"Connection réussie etudiant.");
				if(nom.contains("admin")) {
					this.setResult("Connexion réussie admin. <a href="+"/Projet/EspaceAdmin"+">Continuer</a>");
				}	
			}
			else {
				this.setResult("Identifiants incorrects.");
			}
		}catch(SQLException e) {}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
