package com.utbm.smallWorld;

import com.utbm.smallWorld.interfaces.Bonusable;

public abstract class Pouvoir implements Bonusable {
	
	/** nombre de pions de peuples qu'apporte le pouvoir en plus */
	protected static int nbUniteApporte = 0;
	
	/** peuple auquel est lié le pouvoir spécial, null si lié à aucun. */
	protected Peuple peupleLie = null;
	
	/** indique si le pouvoir est encore comptabilisé si son peuple associé est en déclin */
	protected boolean actifEnDeclin = false;
	
	protected static String nom;
	
	protected static String desc;

	public Pouvoir(){
		
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
	
	
	/**
	 * @return
	 */
	public int bonusUnite() {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusUniteAttaque() {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusAttaque(Territoire from, Territoire to) {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusDefense(Territoire t, Peuple attaquant) {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusGain(Territoire t) {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusValeurDe() {
		return 0;
	}
	
	/**
	 * @return
	 */
	public boolean bonusLanceDe() {
		return false;
	}
	
	/**
	 * @return
	 */
	public boolean bonusSansLimite() {
		return false;
	}
	
	/**
	 * 
	 */
	public boolean bonusPeutAttaquer(Territoire t){
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
