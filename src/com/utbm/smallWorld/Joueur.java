package com.utbm.smallWorld;

import java.util.Iterator;
import java.util.List;

import com.utbm.smallWorld.gui.Game;

/**
 * Représentation d'un Joueur
 * Gestion des peuples, de l'argent
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public class Joueur {
	/** Index du joueur dans la liste des joueurs de la partie */
	private int indice;
	
	/** Pseudonyme du joueur */
	private String nom;
	
	/** Argent possédée par le joueur */
	private int argent = 0;
	
	/** Peuple actif du joueur */
	private Peuple peuple = null;
	
	/** Peuple en déclin du joueur */
	private Peuple peupleDeclin = null;
	
	/** Points de victoire du joueur */
	private int ptsVictoire = 0;
	
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
	 * La méthode analyse les différentes possibilités d'attaque,
	 * et sélectionne la meilleure. Une fois sélectionnée,
	 * elle appelle les méthodes nécessaires des différentes classes.
	 * Si l'attaque n'est pas faisable, on le signale dans le return
	 * @param to Territoire à attaquer
	 * @return true si l'attaque a réussi
	 */
	public boolean attaquer(Territoire to) {
		List<Territoire> occupes = this.peuple.getTerritoiresOccupes();
		Territoire from = null;
		double bonus = 0.0;
		
		// Recherche du meilleur territoire pour attaquer
		if (occupes.size() > 0) {
			Iterator<Territoire> it = occupes.iterator();
			
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
			
			// Si aucune possibilité, on annule
			if (from == null) {
				return false;
			}
		}
		// Le peuple ne possède aucun territoire
		else if (! to.estEnBordure()) {
			// Le try est là au cas où, car on passe null
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
		if (Double.isInfinite(bonus)) {
			if (Double.isInfinite(cout)) {
				cout = to.getNbUnite();
			}
			
			bonus = 0;
		}
		
		// Regarde si le joueur a un bonus lancé de dé
		boolean lanceDe = false;
		int bonusDe = this.peuple.bonusValeurDe() + (this.peuple.hasPower() ? this.peuple.getPouvoir().bonusValeurDe() : 0);
		
		if (this.peuple.bonusLanceDe() || (this.peuple.hasPower() && this.peuple.getPouvoir().bonusLanceDe())) {
			lanceDe = true;
			
			int valeurDe = Game.getInstance().lancerDe(bonusDe);
			
			cout -= valeurDe;
			
			if (cout < 1.0) {
				cout = 1.0;
			}
		}
		
		// Etude de la faisabilité de l'attaque
		double forceAttaque = this.peuple.getNbUniteEnMain() + this.peuple.getNbUniteBonus() + bonus;
		
		boolean redeploiement = false;
		
		if (cout > forceAttaque) {
			// Lancé de dé possible ?
			if (! lanceDe && cout <= forceAttaque + (bonusDe + 3)) {
				if (! Game.getInstance().askConf("Unités insuffisantes, voulez-vous lancer le dé ?")) {
					return false;
				}
				else {
					// Lancé de dé
					int valeurDe = Game.getInstance().lancerDe(bonusDe);
					
					cout -= valeurDe;
					
					if (cout < 1.0) {
						cout = 1.0;
					}
					
					redeploiement = true;
				}
			}
			else {
				return false;
			}
		}
		
		// Prise du territoire
		to.priseTerritoire();
		
		this.peuple.priseTerritoire(to, (int) cout);
		
		if (redeploiement) {
			Partie.getInstance().redeploiement();
		}
		
		return true;
	}
	
	
	/**
	 * Indique au joueur que son peuple en déclin n'est plus
	 */
	public void pertePeupleEnDeclin() {
		this.peupleDeclin = null;
	}
	
	/**
	 * Calcule le nombre de pts de victoire gagnés à la fin d'un tour
	 * @return
	 */
	public int calculePtsVictoireTour(){
		
		int pts = 0;
		
		try{
			pts = peuple.getTerritoiresOccupes().size();
		}catch(NullPointerException e){}
		
		try{
			pts += peupleDeclin.getTerritoiresOccupes().size();
		}catch(NullPointerException e){}
				
		Territoire t = null;
		
		try{
			for(int i = 0; i < peuple.getTerritoiresOccupes().size(); i++){
				t = peuple.getTerritoiresOccupes().get(i);
				pts +=  peuple.getPouvoir().bonusGain(t);
				
				Element e = null;
				
				try{
					for(int j = 0; j < t.getElements().size(); j++){
						e = t.getElements().get(j);
						pts +=  e.bonusGain();
					}
				}catch(NullPointerException ex){}
				
			}
		}catch(NullPointerException e){}
		
		try{
			for(int i = 0; i < peupleDeclin.getTerritoiresOccupes().size(); i++){
				t = peupleDeclin.getTerritoiresOccupes().get(i);
				pts +=  peupleDeclin.getPouvoir().bonusGain(t);
	
				Element e = null;
				
				try{
					for(int j = 0; j < t.getElements().size(); j++){
						e = t.getElements().get(j);
						pts +=  e.bonusGain();
					}
				}catch(NullPointerException ex){}
			}
		}catch(NullPointerException e){}
		
		return pts;
	}
	
	
	/**
	 * Ajoute aux points de victoire ceux du tour
	 */
	public void majPtsVictoire(){
		ptsVictoire += calculePtsVictoireTour();
	}
	
	
	
	
	/* ### GETTER ### */
	
	
	
	
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
	public Peuple getPeupleDeclin() {
		return peupleDeclin;
	}

	/**
	 * @return the indice
	 */
	public int getIndice() {
		return indice;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public int getPtsVictoire(){
		return ptsVictoire;
	}
	


	
	
	
	
	/* ### SETTER ### */
	
	
	
	
	
	/**
	 * @param indice the indice to set
	 */
	public void setIndice(int indice) {
		this.indice = indice;
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
		this.peupleDeclin = peuplesDeclin;
	}
	
	/**
	 * @param ptsVictoire
	 */
	public void setPtsVictoire(int ptsVictoire){
		this.ptsVictoire = ptsVictoire;
	}
	
	/**
	 * passe le peuple en déclin
	 * @param peupleADecliner
	 */
	public void declinerPeuple(Peuple peupleADecliner){
		setPeuplesDeclin(peupleADecliner);
		setPeuple(null);
	}



}

