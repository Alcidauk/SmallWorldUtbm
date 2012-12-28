package com.utbm.smallWorld;

/**
 * Représentation des éléments se trouvant sur les territoires
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public abstract class Element implements Comparable<Element> {
	/** Territoire sur lequel se trouve l'élément */
	protected Territoire territoire;
	
	/** Nom de l'élément */
	protected String nom;
	
	/**
	 * Constructeur
	 * @param t Territoire associé à l'élément
	 */
	public Element(Territoire t) {
		this.territoire = t;
	}
	
	/**
	 * @param attaquant
	 * @return Bonus de défense offert par l'élément contre l'attaquant
	 * Si retourne Integer.MAX_VALUE, alors inattaquable
	 */
	public double bonusDefense(Peuple attaquant) {
		return 0;
	}
	
	
	/**
	 * @return gain bonus offert par cet élément
	 */
	public int bonusGain() {
		return 0;
	}
	

	/**
	 * @param e Element à comparer à this
	 * @return this - e
	 */
	public int compareTo(Element e) {
		return nom.compareTo(e.nom);
	}
	
	
	/**
	 * @param e Element à comparer à this
	 * @return true si égalité
	 */
	public boolean equals(Element e) {
		return nom.equals(e.nom);
	}
	
	/**
	 * @return Le nom de l'élément
	 */
	public String getNom() {
		return this.nom;
	}
}
