package parser;

import java.util.ArrayList;

public class Automate {

	final int idAutomate;
	final String nomAutomate ;
	final ArrayList<Transition> listeTransitions;

	public Automate(int id, String nom, ArrayList<Transition> liste) {
		this.listeTransitions = liste;
		this.idAutomate = id;
		this.nomAutomate = nom;
	}

	public int getIdentifiant() {
		return idAutomate;
	}

	public String getNom() {
		return nomAutomate;
	}

	public ArrayList<Transition> getListeTransitions() {
		return listeTransitions;
	}

	public ArrayList<Transition> getListeTransitions(int etatInitial){
		ArrayList<Transition> l = new ArrayList<Transition>();
		for(Transition t : this.listeTransitions)
			if (etatInitial==t.getEtatInitial())
				l.add(t);
		return l;
	}


	public String toString() {
		return "Automate [idAutomate=" + idAutomate + ", nomAutomate="
				+ nomAutomate + ", listeTransitions=" + listeTransitions + "]" + "\n";
	}
}
