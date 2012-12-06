package com.utbm.smallWorld;

import java.util.LinkedList;
import java.util.List;

/**
 * Représentation d'un Territoire
 * 
 * @author LONGO Michael
 * @version 1.0
 */
public class Territoire {
	private int nbUnite = 0;
	
	private Peuple occupant = null;
	
	private boolean estEnBordure = false;

	private List<Territoire> territoiresAdjacents;
	private List<Element> elements;
	
	public Territoire() {
		territoiresAdjacents = new LinkedList<Territoire>();
		elements = new LinkedList<Element>();
	}
	
	public Territoire(boolean enBordure) {
		this();
		
		this.estEnBordure = enBordure;
	}
	
	public int coutAttaque() {
		int cout = this.nbUnite;
		
		
		
		return 0;
	}
	
	public int bonusAttaque() {
		return 0;
	}
	
	public int bonusGain() {
		return 0;
	}
	
	public void ajouterUnite(int nbUnite) {
		this.nbUnite += nbUnite;
	}
	
	public void retirerUnite(int nbUnite) {
		this.nbUnite -= nbUnite;
	}
	
	public boolean estAdjacent(Peuple p) {
		return false;
	}
	
	public void priseTerritoire(Peuple attaquant, int nbUnite) {
		
	}
	
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
	
}
