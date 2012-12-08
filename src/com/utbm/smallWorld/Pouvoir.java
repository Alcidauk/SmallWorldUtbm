package com.utbm.smallWorld;

import com.utbm.smallWorld.interfaces.Bonusable;

public abstract class Pouvoir implements Bonusable {
	
	/** nombre de pions de peuples qu'apporte le pouvoir en plus */
	protected int nbUniteApporte = 0;
	
	/** peuple auquel est lié le pouvoir spécial, null si lié à aucun. */
	protected Peuple peupleLie = null;
	
	protected boolean actifEnDeclin = false;

	
	/**
	 * constructeur
	 * permet d'instancier les pouvoirs au début.
	 * @param nbUnite nombre d'unités qu'apporte le pouvoir
	 */
	public Pouvoir(int nbUnite, boolean actifDeclin){
		this.nbUniteApporte = nbUnite;
		this.actifEnDeclin = actifDeclin;
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
	
	
	@Override
	public abstract int bonusGain(Territoire t); 

	@Override
	public abstract int bonusDefense(Territoire t, Peuple attaquant);

	@Override
	public abstract int bonusAttaque(Territoire t); 

	@Override
	public abstract boolean bonusSansLimite();

	@Override
	public abstract boolean bonusDefausseUnite();
	
}
