package main;

import java.util.ArrayList;

public class Groupe {
	private String nom;
	private int semestre;
	private int annee;
	private ArrayList<Etudiant> listeEtudiant;
	private ArrayList<Seance> listeSeance;
	private ArrayList<Cours> listeCours;
	
	public Groupe(String nom, int semestre, int annee) {
		this.nom=nom;
		this.semestre=semestre;
		this.annee=annee;
		this.listeEtudiant = new ArrayList<Etudiant>();
		this.listeSeance = new ArrayList<Seance>();
		this.listeCours = new ArrayList<Cours>();
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public ArrayList<Etudiant> getListeEtudiant() {
		return listeEtudiant;
	}
	public void setListeEtudiant(ArrayList<Etudiant> listeEtudiant) {
		this.listeEtudiant = listeEtudiant;
	}
	public ArrayList<Seance> getListeSeance() {
		return listeSeance;
	}
	public void setListeSeance(ArrayList<Seance> listeSeance) {
		this.listeSeance = listeSeance;
	}

	public ArrayList<Cours> getListeCours() {
		return listeCours;
	}

	public void setListeCours(ArrayList<Cours> listeCours) {
		this.listeCours = listeCours;
	}
	
}
