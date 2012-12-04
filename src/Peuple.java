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
	private int nbUniteMax = 0;
	private int nbUniteDepart = 0;
	private int nbUnite = 0;
	private int nbUniteEnMain = 0;
	private int nbUniteBonus = 0;
	
	private String nom;
	private boolean enDeclin = false;
	
	private List<Territoire> territoiresOccupes;
	private List<Bonus> bonus;
	//private Bonus bonus;
	
	private PouvoirSpecial pouvoirSpecial = null;
	
	private Joueur joueur = null;

	/* ***  *** */
	
	public Peuple() {
		territoiresOccupes = new LinkedList<Territoire>();
		bonus = new LinkedList<Bonus>();
	}
	
	
	public Peuple(String nom) {
		this();
		
		this.nom = nom;
	}
	
	public Peuple(String nom, List<Bonus> bonus) {
		this.territoiresOccupes = new LinkedList<Territoire>();
		this.bonus = bonus;
		this.nom = nom;
	}
	
	private void quitterTerritoire(Territoire t) {
		this.territoiresOccupes.remove(t);
		
		if (this.enDeclin && this.territoiresOccupes.size() == 0) {
			Partie.remettreBoite(this);
		}
	}
	
	public void abandonTerritoire(Territoire t) {
		this.nbUniteEnMain += t.getNbUnite();
		
		quitterTerritoire(t);
	}
	
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
	public List<Bonus> getBonus() {
		return bonus;
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
		this.bonus = bonus;
	}

	/**
	 * @param pouvoirSpecial the pouvoirSpecial to set
	 */
	public void setPouvoirSpecial(PouvoirSpecial pouvoirSpecial) {
		this.pouvoirSpecial = pouvoirSpecial;
	}

	/**
	 * @param joueur the joueur to set
	 */
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}

