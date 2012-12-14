package com.utbm.smallWorld;

import com.utbm.smallWorld.interfaces.Bonusable;

public abstract class Pouvoir implements Bonusable {
	
	/** nombre de pions de peuples qu'apporte le pouvoir en plus */
	protected  int nbUniteApporte;
	
	/** peuple auquel est lié le pouvoir spécial, null si lié à aucun. */
	protected Peuple peupleLie;
	
	/** indique si le pouvoir est encore comptabilisé si son peuple associé est en déclin */
	protected boolean actifEnDeclin;
	
	/** nom du pouvoir */
	protected String nom;
	
	/** description du pouvoir */
	protected String desc;

	/* constructeur */
	
	public Pouvoir(){
		nom = "";
		desc = "";
		actifEnDeclin = false;
		nbUniteApporte = 0;
		peupleLie = null;
	}

	/* accesseurs peupleLie */
	
	/**
	 * setter du peupleLie
	 * @param peupleALier le peuple à associer au pouvoir
	 */
	public void setPeupleLie(Peuple peupleALier){
		this.peupleLie = peupleALier;
	}
	
	
	/**
	 * getter du peupleLie
	 * @return retourne le peuple lié au pouvoir dans la partie
	 */
	public Peuple getPeupleLie(){
		return this.peupleLie;
	}
	
	/* accesseurs actifEnDeclin */
	
	/**
	 * getter d'actifEnDeclin
	 * @return retourne actifEnDeclin
	 */
	public boolean getActifEnDeclin(){
		return this.actifEnDeclin;
	}
	
	/* accesseurs nom */
	
	public String getNom(){
		return nom;
	}
	
	/* accesseurs descripteur */
	
	public String getDesc(){
		return desc;
	}
	
	/* accesseurs nbUniteApporte */
	
	public int getNbUniteApporte(){
		return nbUniteApporte;
	}
	
	
	/** méthode de l'interface bonusable. Toutes définies pour être inactives ici et utilisées dans les filles */
	
	/**
	 * @return int le nombre d'unités en plus à la fin du tour
	 */
	public int bonusUnite() {
		return 0;
	}

	/**
	 * @return int le nombre d'unités en renfort pour une attaque
	 */
	public int bonusUniteAttaque() {
		return 0;
	}
	

	/**
	 * @return int le nombre de points d'attaque bonus
	 */
	public int bonusAttaque(Territoire from, Territoire to) {
		return 0;
	}

	/**
	 * @return int le nombre de points de défense supplémentaire 
	 */
	public int bonusDefense(Territoire t, Peuple attaquant) {
		return 0;
	}

	/**
	 * @return int le nombre de points de victoire en plus à ajouter à la fin du tour
	 */
	public int bonusGain(Territoire t) {
		return 0;
	}

	/**
	 * @return int le nombre de points en plus pour la valeur du dé lors d'un lancer.
	 */
	public int bonusValeurDe() {
		return 0;
	}
	
	/**
	 * @return boolean est-ce que le dé peut être lancé à chaque conquête
	 */
	public boolean bonusLanceDe() {
		return false;
	}
	
	/**
	 * 
	 */
	public boolean bonusPeutAttaquer(Territoire from, Territoire to){
		return false;
	}

	/**
	 * @return
	 */
	public boolean bonusDefausseUnite() {
		return false;
	}
	
	/**
	 * 
	 */
	public void actionPriseTerritoire(Territoire t) {
		
	}
}
