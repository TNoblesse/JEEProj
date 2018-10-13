package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import main.Absence;
import main.Cours;
import main.Etudiant;
import main.Groupe;
import main.Matiere;
import main.Professeur;
import main.Resultat;
import main.Seance;

public class Etudiants {
	public ArrayList<Etudiant> recupererEtudiants(HttpServletRequest request) {
		ArrayList<Etudiant> listeEtudiants= new ArrayList<Etudiant>();
		String groupe = request.getParameter("groupe");
		String seance = request.getParameter("seance");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			seance= seance.substring(1, seance.indexOf(")"));
			resultat = statement.executeQuery("SELECT * FROM Cours WHERE nomGrp= '"+groupe+"' AND numSeance= "+seance+";");
			
			if(resultat.next()) {
				Statement statement1 = null;
				ResultSet resultat1 = null;
				try {
					statement1 = connexion.createStatement();
					resultat1 = statement1.executeQuery("SELECT * FROM Etudiant WHERE nomGrp= '"+groupe+"';");
					
					while(resultat1.next()) {
						String nomEtu = resultat1.getString("nomEtu");
						String prenomEtu = resultat1.getString("prenomEtu");
						int numEtu = resultat1.getInt("numEtu");
						
						Etudiant etudiant = new Etudiant(numEtu,nomEtu,prenomEtu);
						listeEtudiants.add(etudiant);
					}
					
				}catch(Exception e) {}
				
			}
		}catch(Exception e) {}
		
		return listeEtudiants;
	}
	public ArrayList<Etudiant> recupererToutLesEtudiants(){
		ArrayList<Etudiant> listeEtudiants= new ArrayList<Etudiant>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Etudiant;");
			
			while(resultat.next()) {
				String nomEtu = resultat.getString("nomEtu");
				String prenomEtu = resultat.getString("prenomEtu");
				int numEtu = resultat.getInt("numEtu");
							
				Etudiant etudiant = new Etudiant(numEtu,nomEtu,prenomEtu);
				listeEtudiants.add(etudiant);
			}
		
		}catch(Exception e) {}
				
		
		return listeEtudiants;
	}
	public void ajouterAbsence(int justifiee,int numEtu, String nomGrp, String numSeance) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Absence WHERE numEtu="+ numEtu +" AND nomGrp= '"+nomGrp+"' AND numSeance="+numSeance+" ;");
			if(!(resultat.next())) {
				PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO Absence(justifiee, numEtu, nomGrp, numSeance) VALUES(?, ?, ?, ?);");
				preparedStatement.setInt(1, justifiee);
				preparedStatement.setInt(2, numEtu);
				preparedStatement.setString(3, nomGrp);
				preparedStatement.setString(4, numSeance);
				preparedStatement.executeUpdate();
				if(justifiee==0) {
					try {
						PreparedStatement preparedStatement1 = connexion.prepareStatement("UPDATE Resultat SET note = note - 1.0 WHERE numEtu="+numEtu+" AND nomMatiere IN (SELECT nomMatiere FROM Cours WHERE nomGrp='"+nomGrp+"' AND numSeance="+numSeance+" );");
						preparedStatement1.executeUpdate();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Etudiant> ajouterEtudiantsAbsents(HttpServletRequest request){
		ArrayList<Etudiant> listeEtudiantsAbsents= new ArrayList<Etudiant>();
		ArrayList<Etudiant> toutLesEtudiants = this.recupererToutLesEtudiants();
		
		for(Etudiant u : toutLesEtudiants) {
			if( request.getParameter("AJ"+u.getNum())!=null ) {
				ajouterAbsence(1,u.getNum(),request.getAttribute("nomGroupe").toString(),request.getAttribute("numSeance").toString());
			}
			if( request.getParameter("ANJ"+u.getNum())!=null ) {
				ajouterAbsence(0,u.getNum(),request.getAttribute("nomGroupe").toString(),request.getAttribute("numSeance").toString());
			}
		}
		
		
		return listeEtudiantsAbsents;
	}
	public Etudiant recupererInfosEtudiant(String numEtu) {
		Etudiant etudiant=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT * FROM Etudiant WHERE numEtu= "+numEtu+" ;");
			
			if(resultat.next()) {
				String nomEtu = resultat.getString("nomEtu");
				String prenomEtu = resultat.getString("prenomEtu");
				etudiant = new Etudiant(Integer.parseInt(numEtu),nomEtu,prenomEtu);
				
				Statement statementAbsence = null;
				ResultSet resultatAbsence = null;
				
				statementAbsence = connexion.createStatement();
				resultatAbsence = statementAbsence.executeQuery("SELECT * FROM Absence WHERE numEtu= "+numEtu+" ;");
				while(resultatAbsence.next()) {
					int justifiee = resultatAbsence.getInt("justifiee");
					String nomGrp = resultatAbsence.getString("nomGrp");
					int numSeance = resultatAbsence.getInt("numSeance");
					
					//System.out.println(justifiee+"---"+ nomGrp+"---"+ numSeance);
					
					Statement statementCours = null;
					ResultSet resultatCours = null;
					
					statementCours = connexion.createStatement();
					resultatCours = statementCours.executeQuery("SELECT numProf, nomMatiere FROM Cours WHERE numSeance= "+numSeance+" AND nomGrp= '"+nomGrp+"' ;");
					
					if(resultatCours.next()) {
						int numProf= resultatCours.getInt("numProf");
						String nomMatiere = resultatCours.getString("nomMatiere");
						
						System.out.println(numProf+"---"+ nomMatiere);
						
						Statement statementProf = null;
						ResultSet resultatProf = null;
						
						statementProf = connexion.createStatement();
						resultatProf = statementProf.executeQuery("SELECT * FROM Professeur WHERE numProf= "+numProf+" ;");
						
						if(resultatProf.next()) {
							String nomProf = resultatProf.getString("nomProf");
							String prenomProf = resultatProf.getString("prenomProf");
							
							//System.out.println(numProf+"---"+ nomProf+"---"+ prenomProf);
							
							Statement statementSeance = null;
							ResultSet resultatSeance = null;
							
							statementSeance = connexion.createStatement();
							resultatSeance = statementSeance.executeQuery("SELECT * FROM Seance WHERE numSeance= "+numSeance+" ;");
							
							if(resultatSeance.next()) {
								String dateSeanced = resultatSeance.getString("dateSeanced");
								String dateSeancef = resultatSeance.getString("dateSeancef");
								
								//System.out.println(dateSeanced+"---"+ dateSeancef);
								
								Cours cours = new Cours(new Seance(dateSeanced,dateSeancef,numSeance),new Groupe(nomGrp,1,2018),new Professeur(nomProf,prenomProf),new Matiere(nomMatiere));
								
								Absence absence = new Absence(justifiee,cours);
								etudiant.getListeAbscence().add(absence);
							}
						}
					}
				}
				
				Statement statementResultat = null;
				ResultSet resultatResultat = null;
				
				statementResultat = connexion.createStatement();
				resultatResultat = statementResultat.executeQuery("SELECT * FROM Resultat WHERE numEtu= "+numEtu+" ;");
				while(resultatResultat.next()) {
					float note = resultatResultat.getFloat("note");
					String nomMatiere = resultatResultat.getString("nomMatiere");
					
					Resultat result = new Resultat(note,new Matiere(nomMatiere));
					
					etudiant.getListeResultat().add(result);
				}
			}
			
		}catch(SQLException e) {}
		
		return etudiant;
	}
}
