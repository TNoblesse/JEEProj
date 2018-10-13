package main;

public class Absence {
	private int justifiee;
	private Cours cours;
	
	public Absence(int justifiee, Cours cours) {
		this.justifiee = justifiee;
		this.cours = cours;
	}
	
	public int getJustifiee() {
		return justifiee;
	}
	public void setJustifiee(int justifiee) {
		this.justifiee = justifiee;
	}
	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
}
