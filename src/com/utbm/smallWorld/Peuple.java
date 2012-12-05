package com.utbm.smallWorld;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Je sais, il n'y a pas de commentaire dans le code.
 * seulement je suis entrain de travailler dessus, et quand je travaille dessus,
 * je ne mets que les commentaires qui me sont importants.
 * Je commit juste pour sauvegarder mon travail
 * Je mettrai les commentaires ensuite, promis :)
 */
public class Peuple {
	/** Nombre d'unité pouvant au maximum être présentes en jeu */
	private int nbUniteMax = 0;
	/** Nombre d'unité conférées au joueur par défaut pour ce peuple - sans bonus du pouvoir special */
	private int nbUniteDepart = 0;
	/** Nombre d'unité actuellement possédées par le joueur sur le plateau */
	private int nbUnite = 0;
	/** Nombre d'unité en jeu, mais ne se trouvant pas sur un territoire */
	private int nbUniteEnMain = 0;
	/** Nombre d'unité bonus servant à augmenter notre attaque, mais qui ne peuvent pas être déployées */
	private int nbUniteBonus = 0;
	
	/** Nom du peuple */
	private String nom;
	/** Définit si le peuple est en déclin ou non */
	private boolean enDeclin = false;
	
	/** Ensemble des territoires occupées par ce peuple */
	private List<Territoire> territoiresOccupes;
	/** Différents bonus possédant l'espèce nativement */
	private List<Bonus> bonusInitial;
	/** Ensemble total des bonus de l'espèce à l'instant t (initial + ceux de pouvoirSpecial) */
	private List<Bonus> bonus;
	//private Bonus bonus;
	
	/** Pouvoir special associé -temporairement- au peuple */
	private PouvoirSpecial pouvoirSpecial = null;
	
	/** Joueur possédant actuellement le peuple */
	private Joueur joueur = null;

	/* ***  *** */
	
	/**
	 * Constructeur par défaut
	 * Initialise les territoires occupées et les bonus initiaux par des listes vides
	 */
	public Peuple() {
		territoiresOccupes = new LinkedList<Territoire>();
		bonusInitial = new LinkedList<Bonus>();
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
		this.bonusInitial = bonus;
		this.nom = nom;
	}
	
	
	/**
	 * Fonction étant appelée lorsque le peuple quitte un territoire
	 * Qu'il l'abandonne ou qu'il se fasse attaquer
	 * @param t Territoire perdu
	 */
	private void quitterTerritoire(Territoire t) {
		this.territoiresOccupes.remove(t);
		
		if (this.enDeclin && this.territoiresOccupes.size() == 0) {
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
			boolean defausseUnite = true;
			
			Iterator<Bonus> it = this.bonus.iterator();
			
			while (defausseUnite && it.hasNext()) {
				Bonus b = it.next();
				
				defausseUnite = b.defausseUnite();
			}
			
			if (defausseUnite) {
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
	 * @return the bonus
	 */
	public List<Bonus> getBonuses() {
		return bonus;
	}

	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return bonusInitial;
	}

	/**
	 * @return the pouvoirSpecial
	 */
	public PouvoirSpecial getPouvoirSpecial() {
		return pouvoirSpecial;
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
	 * @param bonus the bonus to set
	 */
	public void setBonus(List<Bonus> bonus) {
		this.bonusInitial = bonus;
	}

	/**
	 * @param pouvoirSpecial the pouvoirSpecial to set
	 */
	public void setPouvoirSpecial(PouvoirSpecial pouvoirSpecial) {
		this.pouvoirSpecial = pouvoirSpecial;
		
		this.bonus = new LinkedList<Bonus>(this.bonusInitial);
		this.bonus.addAll(pouvoirSpecial.getBonus());
	}

	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}

