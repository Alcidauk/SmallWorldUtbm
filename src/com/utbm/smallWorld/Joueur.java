package com.utbm.smallWorld;

import java.util.Iterator;
import java.util.List;


public class Joueur {

	private String nom;
	private int argent = 0;
	private Peuple peuple = null;
	private Peuple peuplesDeclin = null;
	
	/**
	 * Constructeur par défaut
	 */
	public Joueur()	{
		this.nom = "Default";
	}
	
	/**
	 * Constructeur
	 * @param nom Nom du joueur
	 * @param monnaie Argent possédé par le joueur
	 */
	public Joueur(String nom, int monnaie) {
		this.nom = nom;
		this.argent = monnaie;
	}
	
	/**
	 * Lance l'attaque d'un territoire
	 */
	public boolean attaquer(Territoire to) {
		List<Territoire> occupes = this.peuple.getTerritoiresOccupes();
		
		Territoire from = null;
		double bonus = 0.0;
		
		// Recherche du meilleur territoire pour attaquer
		if (occupes.size() > 0) {
			Iterator<Territoire> it = occupes.iterator();
			
			bonus = 0.0;
			
			while (it.hasNext()) {
				Territoire tmp = it.next();
				
				if (this.peuple.peutAttaquer(tmp, to)) {
					double tmpBonus = this.peuple.calcBonusAttaque(from, to);
					
					if (tmpBonus > bonus || from == null) {
						from = tmp;
						bonus = tmpBonus;
					}
				}
			}
		}
		// Le peuple ne possède aucun territoire
		else if (! to.isEstEnBordure()) {
			try {
				if (! this.peuple.peutAttaquer(null, to)) {
					return false;
				}
			}
			catch (Exception e) {
				return false;
			}
			
		}
		
		// Calcule du malus d'attaque
		double cout = to.coutAttaque(this.peuple);
		
		// Regarde si le territoire peut être attaqué
		if (Double.isInfinite(cout)) {
			if (Double.isInfinite(bonus)) {
				bonus = 0;
				cout = to.getNbUnite();
			}
			else {
				return false;
			}
		}
		
		//
		if (cout > this.peuple.getNbUniteEnMain() + this.peuple.getNbUniteBonus()) {
			return false;
		}
		
		// Prise du territoire
		to.priseTerritoire();
		
		this.peuple.priseTerritoire(to, (int) cout);
		
		return true;
	}
	
	
	/**
	 * Indique au joueur que son peuple en déclin n'est plus
	 */
	public void pertePeupleEnDeclin() {
		this.peuplesDeclin = null;
	}
	
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the argent
	 */
	public int getArgent() {
		return argent;
	}

	/**
	 * @return the peuple
	 */
	public Peuple getPeuple() {
		return peuple;
	}

	/**
	 * @return the peuplesDeclin
	 */
	public Peuple getPeuplesDeclin() {
		return peuplesDeclin;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param argent the argent to set
	 */
	public void setArgent(int argent) {
		this.argent = argent;
	}

	/**
	 * @param peuple the peuple to set
	 */
	public void setPeuple(Peuple peuple) {
		this.peuple = peuple;
	}

	/**
	 * @param peuplesDeclin the peuplesDeclin to set
	 */
	public void setPeuplesDeclin(Peuple peuplesDeclin) {
		this.peuplesDeclin = peuplesDeclin;
	}
	
}

