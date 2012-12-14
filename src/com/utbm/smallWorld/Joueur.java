package com.utbm.smallWorld;

import java.util.Iterator;
import java.util.List;


public class Joueur {

	private String nom;
	private int argent;
	private Peuple peuple ;
	private Peuple peuplesDeclin;
	
	// Constructer
	public Joueur()
	{
		nom= null;
		argent=0;
		peuple= null;
		peuplesDeclin= null;
	}
	
	public Joueur(String n, int monnaie, Peuple p)
	{
		nom= n;
		argent= monnaie;
		peuple = p;
		peuplesDeclin= null;  
		
	}
	
	// Fonctions Setter
	public void setNom(String n)
	{
		nom = n;
	}
	
	public void setArgent(int n)
	{
		argent = n;
	}
	
	public void setPeuple(Peuple p)
	{
		peuple = p;
	}
	
	//Fonctions Getter
	public String getNom()
	{
		return nom;
	}
	public int getArgent()
	{
		return argent;
	}
	
	/**
	 * Lance l'attaque d'un territoire
	 */
	public boolean attaquer(Territoire to)
	{
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
	public void pertePeuple(Peuple peuple) {
		// TODO Auto-generated method stub
		
	}
	
}

