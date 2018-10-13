package main;

import java.util.ArrayList;
import java.util.Date;

public class Seance {
	private String[] dateSeance;
	private int num;
	private String description;
	private ArrayList<Groupe> listeGroupe;
	
	public Seance(String d1, String d2, int n){
		this.dateSeance= new String[] {d1,d2};
		this.num= n;
		this.listeGroupe= new ArrayList<Groupe>();
		this.setDescription("("+this.getNum()+") "+this.getDateSeance()[0]+" -- "+this.getDateSeance()[1]);
	}

	public String[] getDateSeance() {
		return dateSeance;
	}

	public void setDateSeance(String[] dateSeance) {
		this.dateSeance = dateSeance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<Groupe> getListeGroupe() {
		return listeGroupe;
	}

	public void setListeGroupe(ArrayList<Groupe> listeGroupe) {
		this.listeGroupe = listeGroupe;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
