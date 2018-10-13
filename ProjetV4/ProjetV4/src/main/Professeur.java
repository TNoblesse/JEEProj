package main;

import java.util.ArrayList;

public class Professeur {
	private String nom;
	private String prenom;
	private ArrayList<Cours> listeCours;
	
	public Professeur(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
		this.listeCours = new ArrayList<Cours>();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public ArrayList<Cours> getListeCours() {
		return listeCours;
	}
	public void setListeCours(ArrayList<Cours> listeCours) {
		this.listeCours = listeCours;
	}
}
