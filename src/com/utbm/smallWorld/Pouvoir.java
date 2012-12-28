package com.utbm.smallWorld;

import com.utbm.smallWorld.interfaces.Bonusable;

/**
 * Représentation des pouvoirs
 * Classe abstraite étant redéfinie par chaque pouvoir existant dans le jeu
 * 
 * @author UTBM'Student
 * @version 1.0
 */
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

	
	/**
	 * Constructeur par défaut
	 */
	public Pouvoir() {
		nom = "";
		desc = "";
		actifEnDeclin = false;
		nbUniteApporte = 0;
		peupleLie = null;
	}
	
	
	
	/* ### GETTER ### */
	
	/**
	 * @return the nbUniteApporte
	 */
	public int getNbUniteApporte() {
		return nbUniteApporte;
	}


	/**
	 * @return the peupleLie
	 */
	public Peuple getPeupleLie() {
		return peupleLie;
	}


	/**
	 * @return the actifEnDeclin
	 */
	public boolean isActifEnDeclin() {
		return actifEnDeclin;
	}


	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	

	/* ### SETTER ### */


	/**
	 * @param nbUniteApporte the nbUniteApporte to set
	 */
	public void setNbUniteApporte(int nbUniteApporte) {
		this.nbUniteApporte = nbUniteApporte;
	}


	/**
	 * @param peupleLie the peupleLie to set
	 */
	public void setPeupleLie(Peuple peupleLie) {
		this.peupleLie = peupleLie;
	}


	/**
	 * @param actifEnDeclin the actifEnDeclin to set
	 */
	public void setActifEnDeclin(boolean actifEnDeclin) {
		this.actifEnDeclin = actifEnDeclin;
	}

	
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}




	/* ### méthode de l'interface bonusable. Toutes définies pour être inactives ici et utilisées dans les filles ### */
	
	

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
	public double bonusAttaque(Territoire from, Territoire to) {
		return 0.0;
	}

	/**
	 * @return int le nombre de points de défense supplémentaire 
	 */
	public double bonusDefense(Territoire t, Peuple attaquant) {
		return 0.0;
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
