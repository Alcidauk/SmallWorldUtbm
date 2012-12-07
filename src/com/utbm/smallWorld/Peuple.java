package com.utbm.smallWorld;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.utbm.smallWorld.interfaces.Bonusable;

/**
 * Représentation d'un Peuple
 * Gestion des territoires, des bonus, du pouvoir spécial, etc.
 * 
 * @author LONGO Michael
 * @version 1.0
 */
public abstract class Peuple implements Bonusable {
	/** Nombre d'unité pouvant au maximum être présentes en jeu */
	protected int nbUniteMax = 0;
	/** Nombre d'unité conférées au joueur par défaut pour ce peuple - sans bonus du pouvoir  */
	protected int nbUniteDepart = 0;
	/** Nombre d'unité actuellement possédées par le joueur sur le plateau */
	protected int nbUnite = 0;
	/** Nombre d'unité en jeu, mais ne se trouvant pas sur un territoire */
	protected int nbUniteEnMain = 0;
	/** Nombre d'unité bonus servant à augmenter notre attaque, mais qui ne peuvent pas être déployées */
	protected int nbUniteBonus = 0;
	
	/** Nom du peuple */
	protected String nom;
	/** Définit si le peuple est en déclin ou non */
	protected boolean enDeclin = false;
	
	/** Ensemble des territoires occupées par ce peuple */
	protected List<Territoire> territoiresOccupes;
	
	/** Pouvoir associé -temporairement- au peuple */
	protected Pouvoir pouvoir = null;
	
	/** Joueur possédant actuellement le peuple */
	protected Joueur joueur = null;

	
	/**
	 * Constructeur par défaut
	 * Initialise les territoires occupées et les bonus initiaux par des listes vides
	 */
	public Peuple() {
		territoiresOccupes = new LinkedList<Territoire>();
	}
	
	
	/**
	 * Constructeur
	 * Identique au constructeur par défaut, au détail près que l'on spécifie un nom au peuple
	 * @param nom Nom du peuple
	 */
	public Peuple(String nom) {
		this();
		
		this.nom = nom;
	}
	
	/**
	 * Constructeur
	 * Définit un nom et des bonus initiaux au peuple
	 * @param nom Nom du peuple
	 * @param bonus Bonus initiaux du peuple
	 */
	public Peuple(String nom, List<Bonus> bonus) {
		this.territoiresOccupes = new LinkedList<Territoire>();
		this.nom = nom;
	}

	
	
	/**
	 * @return true si le peuple possède un pouvoir associé
	 */
	public boolean hasPower() {
		return this.pouvoir != null;
	}
	
	
	/**
	 * Fonction étant appelée lorsque le peuple quitte un territoire
	 * Qu'il l'abandonne ou qu'il se fasse attaquer
	 * @param t Territoire perdu
	 */
	protected void quitterTerritoire(Territoire t) {
		this.territoiresOccupes.remove(t);
		
		if (this.enDeclin && this.territoiresOccupes.size() == 0) {
			joueur.pertePeuple(this);
			
			Partie.remettreBoite(this);
		}
	}
	
	/**
	 * Abandon d'un territoire par le joueur
	 * Replace les unités en main
	 * @param t Territoire abandonné
	 */
	public void abandonTerritoire(Territoire t) {
		this.nbUniteEnMain += t.getNbUnite();
		
		quitterTerritoire(t);
	}
	
	/**
	 * Perte d'un territoire lors d'une attaque adverse
	 * Replace les unités en main en fonction des bonus
	 * Le territoire ne doit pas encore avoir été mis à jour
	 * @param t Territoire perdu
	 */
	public void perteTerritoire(Territoire t) {
		int unite = t.getNbUnite();
		
		/* Recherche dans les bonus s'il faut défausser une unité ou non */
		if (unite > 1) {
			if (this.bonusDefausseUnite() || (hasPower() && pouvoir.bonusDefausseUnite())) {
				unite--;
			}
		}
		
		this.nbUniteEnMain += unite;

		quitterTerritoire(t);
	}


	/**
	 * Capture d'un territoire lors d'une attaque
	 * Soustrait les unités utilisées de la main,
	 * en utilisant au plus les unités bonus
	 * (Au moins 1 unité physique doit quand même être utilisée)
	 * @param t Territoire conquis
	 * @param nbUnite Nombre d'unité ayant pris le territoire
	 */
	public int priseTerritoire(Territoire t, int nbUnite) {
		// Utilisation prioritaire des unités bonus
		if (this.nbUniteBonus >= nbUnite - 1) {
			this.nbUniteBonus -= nbUnite - 1;
			
			nbUnite = 1;
		}
		else {
			nbUnite -= this.nbUniteBonus;
			
			this.nbUniteBonus = 0;
		}
		
		this.nbUniteEnMain -= nbUnite;
		
		this.territoiresOccupes.add(t);
		
		return nbUnite;
	}
	
	
	/**
	 * Calcule les gains en $ du peuple en fonction
	 * des territoires qu'il possède et des bonus
	 * @return Gain pour le peuple pour le tour en cours
	 */
	public int calculerGain() {
		int gains = 0;
		
		Iterator<Territoire> it = this.territoiresOccupes.iterator();
		
		// Calcul des gains pour chaque territoire
		while (it.hasNext()) {
			Territoire t = it.next();
			
			gains += 1 + this.bonusGain(t);
			
			if (hasPower()) {
				gains += this.pouvoir.bonusGain(t);
			}
		}
		
		return gains;
	}
	
	
	/**
	 * Calcule les éventuels bonus de défense accordés par les bonus du peuple
	 * @param t Territoire du peuple se faisant attaquer
	 * @param attaquant Peuple essayant de conquérir le territoire
	 * @return bonus d'unité de défense (Integer.MAX_VALUE si imprennable)
	 */
	public int bonusDefense(Territoire t, Peuple attaquant) {
		int bonus = this.bonusDefense(t, attaquant);
		
		if (hasPower()) {
			bonus += this.pouvoir.bonusDefense(t, attaquant);
		}
		
		return bonus;
	}
	
	
	/**
	 * Calcule les éventuels bonus d'attaque accordés par les bonus du peuple
	 * @param t Territoire se faisant attaquer
	 * @return bonus d'unité d'attaque (Integer.MAX_VALUE si prise "gratuite" (1 unité))
	 */
	public int bonusAttaque(Territoire t) {
		int bonus = this.bonusAttaque(t);
		
		if (hasPower()) {
			bonus += this.pouvoir.bonusAttaque(t);
		}
		
		return bonus;
	}
	
	
	/**
	 * @return si le peuple peut outre-passer les règles de déplacement standards
	 */
	public boolean estSansLimite() {
		return this.bonusSansLimite() || (hasPower() && this.pouvoir.bonusSansLimite());
	}
	
	
	/**
	 * Passe le peuple en déclin
	 */
	public void decliner() {
		this.enDeclin = true;
		
		if (this.territoiresOccupes.size() == 0) {
			joueur.pertePeuple(this);
			Partie.remettreBoite(this);
		}
	}
	
	
	/* *** GETTERS *** */
	
	/**
	 * @return the nbUniteMax
	 */
	public int getNbUniteMax() {
		return nbUniteMax;
	}

	/**
	 * @return the nbUniteDepart
	 */
	public int getNbUniteDepart() {
		return nbUniteDepart;
	}

	/**
	 * @return the nbUnite
	 */
	public int getNbUnite() {
		return nbUnite;
	}

	/**
	 * @return the nbUniteEnMain
	 */
	public int getNbUniteEnMain() {
		return nbUniteEnMain;
	}

	/**
	 * @return the nbUniteBonus
	 */
	public int getNbUniteBonus() {
		return nbUniteBonus;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the enDeclin
	 */
	public boolean isEnDeclin() {
		return enDeclin;
	}

	/**
	 * @return the territoiresOccupes
	 */
	public List<Territoire> getTerritoiresOccupes() {
		return territoiresOccupes;
	}

	/**
	 * @return the pouvoir
	 */
	public Pouvoir getPouvoir() {
		return pouvoir;
	}

	/**
	 * @return the joueur
	 */
	public Joueur getJoueur() {
		return joueur;
	}

	
	
	
	
	/* *** SETTERS *** */
	
	
	/**
	 * @param nbUniteMax the nbUniteMax to set
	 */
	public void setNbUniteMax(int nbUniteMax) {
		this.nbUniteMax = nbUniteMax;
	}

	/**
	 * @param nbUniteDepart the nbUniteDepart to set
	 */
	public void setNbUniteDepart(int nbUniteDepart) {
		this.nbUniteDepart = nbUniteDepart;
	}

	/**
	 * @param nbUnite the nbUnite to set
	 */
	public void setNbUnite(int nbUnite) {
		this.nbUnite = nbUnite;
	}

	/**
	 * @param nbUniteEnMain the nbUniteEnMain to set
	 */
	public void setNbUniteEnMain(int nbUniteEnMain) {
		this.nbUniteEnMain = nbUniteEnMain;
	}

	/**
	 * @param nbUniteBonus the nbUniteBonus to set
	 */
	public void setNbUniteBonus(int nbUniteBonus) {
		this.nbUniteBonus = nbUniteBonus;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param enDeclin the enDeclin to set
	 */
	public void setEnDeclin(boolean enDeclin) {
		this.enDeclin = enDeclin;
	}

	/**
	 * @param territoiresOccupes the territoiresOccupes to set
	 */
	public void setTerritoiresOccupes(List<Territoire> territoiresOccupes) {
		this.territoiresOccupes = territoiresOccupes;
	}

	/**
	 * @param pouvoir the pouvoir to set
	 */
	public void setPouvoir(Pouvoir pouvoir) {
		this.pouvoir = pouvoir;
	}

	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}

