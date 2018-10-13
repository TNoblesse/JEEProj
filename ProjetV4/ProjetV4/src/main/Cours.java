package main;

import java.util.ArrayList;

public class Cours {
	private Seance seance;
	private Groupe groupe;
	private Professeur prof;
	private Matiere matiere;
	
	public Cours(Seance seance, Groupe groupe, Professeur prof, Matiere matiere) {
		this.seance=seance;
		this.groupe=groupe;
		this.prof = prof;
		this.matiere = matiere;
	}
	
	public Professeur getProf() {
		return prof;
	}
	public void setProf(Professeur prof) {
		this.prof = prof;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	
	
}
