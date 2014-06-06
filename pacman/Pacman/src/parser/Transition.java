package parser;

import java.util.ArrayList;


public class Transition {

	private int etatDebut;
	private int etatFin;
	private ArrayList<String> conditions;
	private ArrayList<String> actions;


	public Transition(int eD, ArrayList<String> conditions, ArrayList<String> actions, int eF) {
		this.etatDebut = eD;
		this.etatFin = eF;
		this.conditions = conditions;
		this.actions = actions;

	}

	public int getEtatInitial() {
		return etatDebut;
	}

	public int getEtatFinal() {
		return etatFin;
	}

	public ArrayList<String> getConditions() {
		return conditions;
	}

	public ArrayList<String> getActions() {
		return actions;
	}

	@Override
	public String toString() {
		return "Transition [etatDebut=" + etatDebut + ", etatFin=" + etatFin
				+ ", conditions=" + conditions + ", actions=" + actions + "]";
	}


}
