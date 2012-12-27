package com.utbm.smallWorld;

import java.util.Iterator;
import java.util.LinkedList;

import java.util.List;


/**
 * Représentation d'un Territoire
 * 
 * @author LONGO Michael
 * @version 1.0
 */
public class Territoire {
	/** Nombre d'unité présentes sur le territoire */
	private int nbUnite = 0;
	
	/** Joueur présent sur le territoire */
	private Peuple occupant = null;
	
	/** Indique si le territoire est en bordure de carte */
	private boolean estEnBordure = false;

	/** Liste des territoires adjacants à l'actuel */
	private List<Territoire> territoiresAdjacents;
	
	/** Liste des éléments présents sur le territoire */
	private List<Element> elements;
	
	/** Historique des prises du territoire */
	private List<Peuple> prisesDuTerritoire[];
	
	/**
	 * Constructeur par défaut
	 * Initialise les listes
	 */
	public Territoire() {
		territoiresAdjacents = new LinkedList<Territoire>();
		elements = new LinkedList<Element>();
	}
	
	/**
	 * Constructeur, appel au constructeur par défaut
	 * @param enBordure Indique si le territoire est en bordure
	 */
	public Territoire(boolean enBordure) {
		this();
		
		this.estEnBordure = enBordure;
	}
	
	/**
	 * Calcule le cout de l'attaque pour ce territoire
	 * @param attaquant Peuple essayant d'attaquer le territoire
	 * @return cout en nombre d'unité, Integer.MAX_VALUE si le territoire est dit imprenable
	 */
	public double coutAttaque(Peuple attaquant) {
		double cout = this.nbUnite;
		
		Iterator<Element> it = this.elements.iterator();
		
		while (it.hasNext()) {
			cout += it.next().bonusDefense(attaquant);
		}
		
		if (this.occupant != null) {
			cout += this.occupant.bonusDefense(this, attaquant);
		}
		
		return cout;
	}
	
	/**
	 * @return Nombre de point victoire bonus sans compter le point normal
	 */
	public int bonusGain() {
		int bonus = this.nbUnite;
		
		Iterator<Element> it = this.elements.iterator();
		
		while (it.hasNext()) {
			bonus += it.next().bonusGain();
		}
		
		return bonus;
	}
	
	/**
	 * Ajoute des unités sur le territoire
	 * @param nbUnite Nombre d'unité à ajouter
	 */
	public void ajouterUnite(int nbUnite) {
		this.nbUnite += nbUnite;
	}
	
	/**
	 * Retire des unités sur le territoire
	 * @param nbUnite Nombre d'unité à retirer
	 */
	public void retirerUnite(int nbUnite) {
		this.nbUnite = Math.max(0, nbUnite);
	}
	
	/**
	 * Vérifie si le territoire est adjacent à un des territoires du peuple passé
	 * @param p Peuple a tester
	 * @return true/false
	 */
	public boolean estAdjacent(Peuple p) {
		// TODO
		return false;
	}
	
	/**
	 * Vérifie si le territoire est adjacent à un autre
	 * @param t Territoire a tester
	 * @return true/false
	 */
	public boolean estAdjacent(Territoire t) {
		return territoiresAdjacents.contains(t);
	}
	
	/**
	 * Perte du territoire
	 */
	public void priseTerritoire() {
		if (this.occupant != null) {
			occupant.perteTerritoire(this);
			occupant = null;
		}
		
		nbUnite = 0;
	}
	
	/**
	 * Abandon du territoire par le joueur actuel
	 */
	public void abandon() {
		
	}

	/**
	 * @return the nbUnite
	 */
	public int getNbUnite() {
		return nbUnite;
	}

	/**
	 * @return the occupant
	 */
	public Peuple getOccupant() {
		return occupant;
	}

	/**
	 * @return the estEnBordure
	 */
	public boolean isEstEnBordure() {
		return estEnBordure;
	}

	/**
	 * @return the territoiresAdjacents
	 */
	public List<Territoire> getTerritoiresAdjacents() {
		return territoiresAdjacents;
	}

	/**
	 * @return the elements
	 */
	public List<Element> getElements() {
		return elements;
	}
	
	/**
	 * 
	 * @return List prisesDuTerritoire
	 */
	public List<Peuple>[] getPrisesDuTerritoire() {
		return prisesDuTerritoire;
	}

	/**
	 * @param nbUnite the nbUnite to set
	 */
	public void setNbUnite(int nbUnite) {
		this.nbUnite = nbUnite;
	}

	/**
	 * @param occupant the occupant to set
	 */
	public void setOccupant(Peuple occupant) {
		this.occupant = occupant;
	}

	/**
	 * @param estEnBordure the estEnBordure to set
	 */
	public void setEstEnBordure(boolean estEnBordure) {
		this.estEnBordure = estEnBordure;
	}

	/**
	 * @param territoiresAdjacents the territoiresAdjacents to set
	 */
	public void setTerritoiresAdjacents(List<Territoire> territoiresAdjacents) {
		this.territoiresAdjacents = territoiresAdjacents;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}

	/**
	 * @param prisesDuTerritoire
	 */
	public void setPrisesDuTerritoire(List<Peuple>[] prisesDuTerritoire) {
		this.prisesDuTerritoire = prisesDuTerritoire;
	}
	
	
	
	public void ajouterElement(Element e) {
		
	}
	
	/**
	 * 
	 * @param type le type d'élément dont on veut savoir s'il est sur le territoire
	 * @return boolean si l'élément est dessus
	 */
	public boolean has(Class<? extends Element> type ){
		Iterator<Element> it = this.elements.iterator();
		
		while( it.hasNext() ){
			Element ele = it.next();
			
			if(  type.isInstance(ele) )
				return true;
		}
		
		return false;
	}


	
	/**
	 * TODO
	 */
	public void addTerritoireAdjacent(Territoire t) {
		this.territoiresAdjacents.add(t);
	}


	
}
