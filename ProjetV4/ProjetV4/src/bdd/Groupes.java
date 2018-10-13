package bdd;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.Statement;

import main.Absence;
import main.Cours;
import main.Etudiant;
import main.Groupe;
import main.Matiere;
import main.Professeur;
import main.Seance;

public class Groupes {
	public ArrayList<Groupe> recupererGroupes(){
		ArrayList<Groupe> listeGroupes = new ArrayList<Groupe>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			
			resultat = statement.executeQuery("SELECT nomGrp FROM Groupe WHERE semestre=1 AND annee=2018;");
			
			while(resultat.next()) {
				String nomGrp = resultat.getString("nomGrp");
				
				Groupe groupe = new Groupe(nomGrp,1,2018);
				/*
				Statement statement1 = null;
				ResultSet resultat1 = null;
				statement1 = connexion.createStatement();
				ArrayList<Etudiant> listeEtudiants = new ArrayList<Etudiant>();
				
				
				resultat1 = statement1.executeQuery("SELECT numEtu, nomEtu, prenomEtu FROM Etudiant WHERE nomGrp="+nomGrp+";");
				while(resultat1.next()) {
					int numEtu = resultat1.getInt("numEtu");
					String nomEtu = resultat1.getString("nomEtu");
					String prenomEtu = resultat1.getString("prenomEtu");
					
					Etudiant etudiant = new Etudiant(numEtu,nomEtu,prenomEtu);
					listeEtudiants.add(etudiant);
				}
				*/
				listeGroupes.add(groupe);
			}
		}catch(Exception e) {}
		
		return listeGroupes;
	}
	public Groupe recupererInfosGroupe(String nomGrp) {
		Groupe groupe=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {}
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		
		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee","root","admin");
			statement = connexion.createStatement();
			
			resultat = statement.executeQuery("SELECT * FROM Groupe WHERE nomGrp= '"+nomGrp+"' ;");
			
			if(resultat.next()) {
				int semestre = resultat.getInt("semestre");
				int annee = resultat.getInt("annee");
				
				groupe = new Groupe(nomGrp,semestre,annee);
				
				Statement statementEtudiant = null;
				ResultSet resultatEtudiant = null;
				statementEtudiant = connexion.createStatement();
				
				
				resultatEtudiant = statementEtudiant.executeQuery("SELECT * FROM Etudiant WHERE nomGrp= '"+nomGrp+"' ;");
				while(resultatEtudiant.next()) {
					int numEtu = resultatEtudiant.getInt("numEtu");
					String nomEtu = resultatEtudiant.getString("nomEtu");
					String prenomEtu = resultatEtudiant.getString("prenomEtu");
					
					Etudiant etudiant = new Etudiant(numEtu,nomEtu,prenomEtu);
					groupe.getListeEtudiant().add(etudiant);
				}
				
				Statement statementCours = null;
				ResultSet resultatCours  = null;
				
				statementCours = connexion.createStatement();
				resultatCours = statementCours.executeQuery("SELECT * FROM Cours WHERE nomGrp= '"+nomGrp+"' ;");
				while(resultatCours.next()) {
						int numSeance = resultatCours.getInt("numSeance");
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
							
							System.out.println(numProf+"---"+ nomProf+"---"+ prenomProf);
							
							Statement statementSeance = null;
							ResultSet resultatSeance = null;
							
							statementSeance = connexion.createStatement();
							resultatSeance = statementSeance.executeQuery("SELECT * FROM Seance WHERE numSeance= "+numSeance+" ;");
							
							if(resultatSeance.next()) {
								String dateSeanced = resultatSeance.getString("dateSeanced");
								String dateSeancef = resultatSeance.getString("dateSeancef");
								
								System.out.println(dateSeanced+"---"+ dateSeancef);
								
								Cours cours = new Cours(new Seance(dateSeanced,dateSeancef,numSeance),new Groupe(nomGrp,1,2018),new Professeur(nomProf,prenomProf),new Matiere(nomMatiere));
								
								groupe.getListeCours().add(cours);
							}
						}
					}
				}
		}catch(Exception e) {}
		return groupe;
	}
}
