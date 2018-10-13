package main;

public class Resultat {
	private float note;
	private Matiere matiere;
	
	public Resultat(float note, Matiere matiere) {
		this.note = note;
		this.matiere = matiere;
	}
	
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
}
