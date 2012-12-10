package com.utbm.smallWorld;

public abstract class Element implements Comparable<Element> {
	protected Territoire territoire;
	
	protected String nom;
	
	public Element(Territoire t) {
		this.territoire = t;
	}
	
	/**
	 * Si retourne Integer.MAX_VALUE, alors inattaquable
	 */
	public int bonusDefense(Peuple attaquant) {
		return 0;
	}
	
	public int bonusGain() {
		return 0;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int compareTo(Element e) {
		return nom.compareTo(e.nom);
	}
	
	public boolean equals(Element e) {
		return nom.equals(e.nom);
	}
}
