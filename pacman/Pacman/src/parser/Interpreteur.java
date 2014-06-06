package parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

public class Interpreteur {

	private ArrayList<Automate> listeAutomates = new ArrayList<Automate>();

	private Element lireElementRacine(String nomDuFichierXML) {
		Element racine = new Element("Automates");
		org.jdom2.Document document = new Document(racine);
		Element element = null;
		try {
			SAXBuilder builder = new SAXBuilder();
			document = builder.build(new File(nomDuFichierXML));
			element = document.getRootElement();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.println("Problème à l'ouverture du fichier XML");
			System.out.println("Vérifier le chemin et le format du fichier");
		}
		return element;
	}

	private ArrayList<Transition> lireTransitions(Element e) {
		ArrayList<Transition> res = new ArrayList<Transition>();
		List<Element> transitions = e.getChildren("Transitions");
		ListIterator<Element> iterators = transitions.listIterator();
		while (iterators.hasNext()) {
			Element el = (Element) iterators.next();
			List<Element> transition = el.getChildren("Transition");
			ListIterator<Element> iterator = transition.listIterator();
			while (iterator.hasNext()) {
				Element el2 = (Element) iterator.next();
				res.add(new Transition((int) Integer.parseInt(el2
						.getChildText("EtatCourant")), lireConditions(el2),lireActions(el2),
						(int) Integer.parseInt(el2.getChildText("EtatFinal"))));
			}
		}
		return res;
	}

	private ArrayList<String> lireConditions(Element e) {
		ArrayList<String> res = new ArrayList<String>();
		Element conds = e.getChild("Conditions");
		List<Element> cond = conds.getChildren("Condition");
		ListIterator<Element> i = cond.listIterator();
		while (i.hasNext()) {
			Element el = (Element) i.next();
			res.add(el.getText());
		}
		return res;
	}

	private ArrayList<String> lireActions(Element e) {
		ArrayList<String> res = new ArrayList<String>();
		Element acts = e.getChild("Actions");
		List<Element> act = acts.getChildren("Action");
		ListIterator<Element> i = act.listIterator();
		while (i.hasNext()) {
			Element el = (Element) i.next();
			res.add(el.getText());
		}
		return res;
	}

	private void lireAutomates(Element e) {
		int cpt = 0;
		List<Element> automates = e.getChildren("Automate");
		ListIterator<Element> i = automates.listIterator();
		while (i.hasNext()) {
			Element el = (Element) i.next();
			listeAutomates.add(lireAutomate(el, cpt));
			cpt++;
		}
	}

	private Automate lireAutomate(Element e, int cpt) {
		String nom = e.getChildText("Nom");
		return new Automate(cpt, nom, lireTransitions(e));
	}

	public Interpreteur(String cheminVersXml) {
		Element e = lireElementRacine(cheminVersXml);
		lireAutomates(e);
	}

	public Automate getAutomate(String nomAutomate) throws Exception {
		//System.out.println(listeAutomates);
		for (Automate a : listeAutomates)
			if (nomAutomate.equals(a.nomAutomate))
				return a;
		throw new Exception(
				"getAutomate(nomAutomate) : Pas de nom d'automate associé a "
						+ nomAutomate);
	}


	@Override
	public String toString() {
		return "Interpreteur [listeAutomates=" + listeAutomates + "]";
	}

}

