package main;

import java.util.ArrayList;

public class Matiere {
	private String nom;
	private ArrayList<Cours> listeCours;
	
	public Matiere(String nom) {
		this.nom = nom;
		this.listeCours = new ArrayList<Cours>();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Cours> getListeCours() {
		return listeCours;
	}
	public void setListeCours(ArrayList<Cours> listeCours) {
		this.listeCours = listeCours;
	}
	
}
