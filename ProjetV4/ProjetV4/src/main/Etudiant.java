package main;

import java.util.ArrayList;

public class Etudiant {
	private int num;
	private String nom;
	private String prenom;
	private ArrayList<Resultat> listeResultat;
	private ArrayList<Absence> listeAbscence;
	
	public Etudiant(int num, String nom, String prenom) {
		this.num = num;
		this.nom = nom;
		this.prenom = prenom;
		this.listeResultat = new ArrayList<Resultat>();
		this.listeAbscence = new ArrayList<Absence>();
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public ArrayList<Resultat> getListeResultat() {
		return listeResultat;
	}
	public void setListeResultat(ArrayList<Resultat> listeResultat) {
		this.listeResultat = listeResultat;
	}
	public ArrayList<Absence> getListeAbscence() {
		return listeAbscence;
	}
	public void setListeAbscence(ArrayList<Absence> listeAbscence) {
		this.listeAbscence = listeAbscence;
	}
	
}
