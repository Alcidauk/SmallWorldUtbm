package com.utbm.smallWorld;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.utbm.smallWorld.gui.Game;
import com.utbm.smallWorld.gui.WinWarn;
import com.utbm.smallWorld.peuples.*;
import com.utbm.smallWorld.pouvoirs.*;


/**
 * Représentation d'une partie
 * Gestion du fonctionnement de la partie
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public class Partie {
	/** Argent par défaut possédé par les joueurs */
	public static final int DEFAULT_ARGENT = 2;
	
	/** Singleton, instance de la partie en cours */
	private static Partie part;
	
	/** Nombre de tour total de la partie */
	protected int nbTours = 10;
	
	/** Tour en cours de jeu */
	protected int tourEnCours = -1;
	
	/** Liste des joueurs participant à la partie */
	protected List<Joueur> lstJoueurs = null;
	
	/** Index du joueur jouant à un instant donné */
	protected int indexJoueurEnCours = 0;
	
	/** Index du joueur jouant à un instant donné */
	protected int indexSauvJoueurEnCours = 0;
	
	/** Index du joueur jouant à un instant donné */
	protected Joueur joueurEnCours = null;
	
	/** Etape du tour */
	protected int etape = 0;
	
	/** Liste des peuples actuellement en jeu */
	protected List<Class<? extends Peuple>> peuplesPris = null;
	
	/** Liste des peuples disponibles dans le tas */
	protected List<Class<? extends Peuple>> peuplesDispo = null;
	
	/** Liste des pouvoirs en jeu */
	protected List<Class<? extends Pouvoir>> pouvoirsPris = null;
	
	/** Liste des pouvoirs disponibles dans le tas */
	protected List<Class<? extends Pouvoir>> pouvoirsDispo = null;
	
	/** Argent placée sur les peuples de la file d'attente */
	protected List<Integer> argentPeuple = null;
	
	/** Plateau de la partie */
	protected Plateau plateau;
	
	
	
	/**
	 * Constructeur par défaut, initialise les listes
	 */
	private Partie() {
		lstJoueurs = new LinkedList<Joueur>();
		peuplesPris = new LinkedList<Class<? extends Peuple>>();
		peuplesDispo = new LinkedList<Class<? extends Peuple>>();
		pouvoirsPris = new LinkedList<Class<? extends Pouvoir>>();
		pouvoirsDispo = new LinkedList<Class<? extends Pouvoir>>();
		argentPeuple = new LinkedList<Integer>();
		
		initPeuples();
		initPouvoirs();
	}

	
	
	/* ### Méthodes relatives au fonctionnement des tours ### */
	
	
	
	/**
	 * Méthode permettant de passer au tour suivant
	 */
	public void nouveauTour() {
		tourEnCours++;
		
		if (tourEnCours > nbTours) {
			
		}
		else {
			// RAZ des étapes
    		etape = 0;
    		indexJoueurEnCours = 0;
    		
    		joueurEnCours = this.lstJoueurs.get(indexJoueurEnCours);
    		
    		// Si le peuple n'a pas de joueur, il doit en choisir un
    		if (joueurEnCours.getPeuple() == null) {
    			Game.getInstance().selectionPeuple();
    		}
    		
    		Game.getInstance().showTemp(joueurEnCours.getNom() + " attaque !");
    		
    		miseEnMain();
		}
	}
	
	
	
	/**
	 * Récupère toutes les unités placées sur les territoires du joueur
	 * à l'exception d'une par territoire
	 * et les places dans sa main
	 */
	public void miseEnMain() {
		Peuple p = joueurEnCours.getPeuple();
		
		// Récupération des unités du plateau		
		Iterator<Territoire> it = p.getTerritoiresOccupes().iterator();
		
		while (it.hasNext()) {
			Territoire t = it.next();
			
			int nb = t.getNbUnite();
			
			t.setNbUnite(1);
			
			p.addNbUniteEnMain(nb - 1);
		}
		
		// Application des bonus
		p.calcBonusUniteAttaque();
	}
	
	/**
	 * permet de fournir le coût de l'attaque d'un territoire donnée appelé depuis TerritoireCase
	 * @param t
	 * @return
	 */
	public double coutAttaque(Territoire t){
		// TODO Temp
		try {
			return t.coutAttaque(joueurEnCours.getPeuple());
		}
		catch (Exception e) {
			System.out.println(t);
			System.out.println(joueurEnCours);
		}
		
		return 0.0;
	}
	
	
	
	/* ### Methodes réagissant à des interactions utilisateur ### */
	
	
	
	/**
	 * Traitements en fonction du territoire cliqué
	 * @param territoire Territoire cliqué par l'utilisateur
	 */
	public void cliqueTerritoire(Territoire territoire) {
		if (etape == 0 || etape == 1) {
			// Phase d'attaque
			
			if ( joueurEnCours.getPeuple().equals(territoire.getOccupant()) ) {
				List<Peuple> ls = territoire.getPrisesDuTerritoire(tourEnCours);
				
				if (! ls.contains(getJoueurEnCours().getPeuple()) && Game.getInstance().askAbandon(territoire)) {
					getJoueurEnCours().getPeuple().abandonTerritoire(territoire);
				}
			}
			else {
				//if(coutAttaque(territoire) > joueurEnCours.getPeuple().)
				if (Game.getInstance().askAttaque(territoire)) {

					if (getJoueurEnCours().attaquer(territoire) && etape == 0) {
						etape = 1;
					}
				}
			}
			
			Game.getInstance().majInfos();
			
		}
		else if (etape == 2 || etape == 3) {
			// Phase de redéploiement
			
			if (joueurEnCours.getPeuple().equals(territoire.getOccupant())) {
				int nbPion = Game.getInstance().askNbPion(territoire);

				if (nbPion > 0) {
					getJoueurEnCours().getPeuple().addNbUniteEnMain(-(nbPion - territoire.getNbUnite()));
					territoire.setNbUnite(nbPion);
				}
				else if (nbPion == 0) {
					if (Game.getInstance().askAbandon(territoire)) {
						getJoueurEnCours().getPeuple().abandonTerritoire(territoire);
					}
				}
				
				Game.getInstance().majInfos();
			}else{
				new WinWarn("Vous ne pouvez que vous redéployer sur un de vos territoires");
			}
		}
	}
	
	
	
	
	/**
	 * Traitement lors d'un clic sur le bouton fin tour
	 */
	public void cliqueFinTour() {
		/* en mode conquete, confirmé, et possède au moins un territoire (sinon il ne peut pas redéployer...) */
		if( joueurEnCours.getPeuple().getTerritoiresOccupes().isEmpty() ){
			new WinWarn("Veuillez tout d'abord prendre au moins un territoire");
		}else if((etape == 0 || etape == 1) && Game.getInstance().askConf("Confirmer la fin du tour ?") ) {
			setEtape(2);
			miseEnMain();
			joueurEnCours.getPeuple().calcBonusUnite();
			
			Game.getInstance().majInfos();
			
    		Game.getInstance().showTemp(joueurEnCours.getNom() + " se redéploie.");
		}
	}
	
	
	public boolean deploiementSuivant() {
		int nextJoueur = (indexSauvJoueurEnCours + 1) == lstJoueurs.size() ? 0 : (indexSauvJoueurEnCours + 1);
		
		for (; indexJoueurEnCours < lstJoueurs.size(); indexJoueurEnCours++) {
			if (indexJoueurEnCours == nextJoueur) {
				continue;
			}
			
			joueurEnCours = lstJoueurs.get(indexJoueurEnCours);
			
			Peuple p = joueurEnCours.getPeuple();
			
			if (p != null && p.getNbUniteEnMain() > 0) {
	    		Game.getInstance().showTemp(joueurEnCours.getNom() + " se redéploie.");
	    		
				return true;
			}
		}
		
		indexJoueurEnCours = indexSauvJoueurEnCours;
		joueurEnCours = lstJoueurs.get(indexSauvJoueurEnCours);
		
		return false;
	}
	
	
	public void joueurSuivant() {
		setEtape(0);
		
		/* on calcule les gains du tour et on met à jour les pts de victoire */
		Game.getInstance().showTemp(joueurEnCours.calculePtsVictoireTour() + " point(s) gagné(s) durant ce tour !");
		joueurEnCours.majPtsVictoire();
		
		if (indexJoueurEnCours == lstJoueurs.size() - 1) {
			nouveauTour();
		}
		else {
			indexJoueurEnCours++;
			joueurEnCours = lstJoueurs.get(indexJoueurEnCours);
			
			if (joueurEnCours.getPeuple() == null || joueurEnCours.getPeuple().getNbUnite() == 0) { // TODO : 2ème partie du if OK ?
				Game.getInstance().selectionPeuple();
    		}
    		
    		miseEnMain();

    		Game.getInstance().showTemp(joueurEnCours.getNom() + " attaque !");
		}
		
	}
	
	/**
	 * Traitement lors d'un clic sur le bouton fin redéploiement
	 */
	public void cliqueFinRedeploiement() {
		if ((etape == 2 || etape == 3) && joueurEnCours.getPeuple().getNbUniteEnMain() == 0) {
			if (Game.getInstance().askConf("Confirmer la fin du redéploiement ?")) {
				if (etape == 2) {
					/* on passe à l'étape redéploiement des autres joueurs */
					setEtape(3);
					
					/* et on indique que le joueur passe au tour suivant pour après ne pas le faire rejouer */
					indexSauvJoueurEnCours = indexJoueurEnCours;
					
					indexJoueurEnCours = 0;

				}
				
				if (! deploiementSuivant()) {
					joueurSuivant();
				}
				
				Game.getInstance().majInfos();
			}
		}
	}
	
	
	/**
	 * Traitement lors d'un clic sur le bouton passer en déclin
	 */
	public void cliqueDeclin() {
		if( etape == 0 && joueurEnCours.getPeupleDeclin() == null && Game.getInstance().askConf("Confirmer le passage en déclin ?") ){
			joueurEnCours.getPeuple().decliner();
			joueurSuivant();
		}	
		Game.getInstance().majInfos();
	}
	
	
	
	/* ### Autres méthodes ### */
	
	
	/**
	 * Passe un peuple se trouvant sur le jeu dans le tas
	 * @param peuple Peuple à remettre
	 */
	public void remettreBoite(Peuple peuple) {
		// Recherche de la classe du peuple
		Iterator<Class<? extends Peuple>> it = peuplesPris.iterator();
		Class<? extends Peuple> peupleClass = null;
		
		while( it.hasNext() && peupleClass == null ){
			Class<? extends Peuple> tmp = it.next();
	
			if( tmp.isInstance(peuple) ){
				peupleClass = tmp;
			}
		}
		
		peuplesPris.remove(peupleClass);
		peuplesDispo.add(peupleClass);
		
		
		// Recherche de la classe du pouvoir
		Iterator<Class<? extends Pouvoir>> ite = pouvoirsPris.iterator();
		Class<? extends Pouvoir> pouvoirClass = null;
		
		while( ite.hasNext() && pouvoirClass == null ){
			Class<? extends Pouvoir> tmp = ite.next();
	
			if( tmp.isInstance(peuple.getPouvoir()) ){
				pouvoirClass = tmp;
			}			
			
		}
		
		pouvoirsPris.remove(pouvoirClass);
		pouvoirsDispo.add(pouvoirClass);
	}
	

	
	/**
	 * Marque une combinaison Peuple/Pouvoir comme utilisée
	 * @param numCombinaison index dans la liste
	 */
	public void mettreCombinaisonEnJeu(int numCombinaison) {
		pouvoirsPris.add(pouvoirsDispo.get(numCombinaison));
		peuplesPris.add(peuplesDispo.get(numCombinaison));
		
		pouvoirsDispo.remove(numCombinaison);
		peuplesDispo.remove(numCombinaison);
	}
	
	
	
	/**
	 * Ajoute un joueur à la liste des joueurs participants
	 * @param j Joueur à ajouter
	 */
	public void ajouterJoueur(Joueur j) {
		lstJoueurs.add(j);
	}
	
	
	
	
	/* ### Méthodes d'initialisations ### */
	
	/**
	 * Initialise les listes des peuples
	 */
	private void initPeuples() {
		peuplesDispo.add(PeupleAdministration.class);
		argentPeuple.add(0);

		peuplesDispo.add(PeupleAlternance.class);
		argentPeuple.add(0);

		peuplesDispo.add(PeupleChercheur.class);
		argentPeuple.add(0);
		
		peuplesDispo.add(PeupleCRI.class);
		argentPeuple.add(0);
		
		peuplesDispo.add(PeupleDirecteur.class);
		argentPeuple.add(0);
		
		peuplesDispo.add(PeupleEtudiantBranche.class);
		argentPeuple.add(0);
		
		peuplesDispo.add(PeupleEtudiantTC.class);
		argentPeuple.add(0);
		
		peuplesDispo.add(PeupleProfHumanite.class);
		argentPeuple.add(0);
		
		peuplesDispo.add(PeupleProfScience.class);
		argentPeuple.add(0);
		
		peuplesDispo.add(PeupleRats.class);
		argentPeuple.add(0);
	
		peuplesDispo.add(PeupleServiceTechnique.class);
		argentPeuple.add(0);
		
		/* pour randomizer le choix peuple/pouvoir */
		Collections.shuffle(peuplesDispo);
	}
	
	
	
	/**
	 * Initialisation des pouvoirs
	 */
	private void initPouvoirs(){
		pouvoirsDispo.add(PouvoirAssociatif.class);
		
		pouvoirsDispo.add(PouvoirAvare.class);
		
		pouvoirsDispo.add(PouvoirBagarreur.class);
		
		pouvoirsDispo.add(PouvoirFauxCul.class);
		
		pouvoirsDispo.add(PouvoirFetard.class);
		
		pouvoirsDispo.add(PouvoirFumeur.class);
		
		pouvoirsDispo.add(PouvoirGeek.class);
		
		pouvoirsDispo.add(PouvoirGlouton.class);
		
		pouvoirsDispo.add(PouvoirIntello.class);
		
		pouvoirsDispo.add(PouvoirJoueur.class);
		
		pouvoirsDispo.add(PouvoirNerveux.class);
		
		pouvoirsDispo.add(PouvoirOpportuniste.class);
		
		pouvoirsDispo.add(PouvoirParesseux.class);
		
		pouvoirsDispo.add(PouvoirVoyageur.class);
		
		/* pour randomizer le choix peuple/pouvoir */
		Collections.shuffle(pouvoirsDispo);
	}

	
	
	
	/* ### GETTER ### */
	
	
	
	
	/**
	 * @return the defaultMonnaie
	 */
	public static int getDefaultArgent() {
		return DEFAULT_ARGENT;
	}

	/**
	 * @return the part
	 */
	public static Partie getPart() {
		return part;
	}

	/**
	 * @return the nbTours
	 */
	public int getNbTours() {
		return nbTours;
	}

	/**
	 * @return the tourEnCours
	 */
	public int getTourEnCours() {
		return tourEnCours;
	}

	/**
	 * @return the lstJoueurs
	 */
	public List<Joueur> getLstJoueurs() {
		return lstJoueurs;
	}

	/**
	 * @return the joueurEnCours
	 */
	public int getIndexJoueurEnCours() {
		return indexJoueurEnCours;
	}

	/**
	 * @return the joueurEnCours
	 */
	public Joueur getJoueurEnCours() {
		return joueurEnCours;
	}

	/**
	 * @return the peuplesPris
	 */
	public List<Class<? extends Peuple>> getPeuplesPris() {
		return peuplesPris;
	}

	/**
	 * @return the peuplesDispo
	 */
	public List<Class<? extends Peuple>> getPeuplesDispo() {
		return peuplesDispo;
	}

	/**
	 * @return the pouvoirsPris
	 */
	public List<Class<? extends Pouvoir>> getPouvoirsPris() {
		return pouvoirsPris;
	}

	/**
	 * @return the pouvoirsDispo
	 */
	public List<Class<? extends Pouvoir>> getPouvoirsDispo() {
		return pouvoirsDispo;
	}

	/**
	 * @return the argentPeuple
	 */
	public List<Integer> getArgentPeuple() {
		return argentPeuple;
	}

	/**
	 * @return the plateau
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * @param i Index du joueur souhaité
	 * @return Joueur
	 */
	public Joueur getJoueur(int i) {
		return lstJoueurs.get(i);
	}
	
	
	/**
	 * @return the etape
	 */
	public int getEtape() {
		return etape;
	}


	/**
	 * @return instance de la Partie en cours
	 */
	public static Partie getInstance(){
		if( part == null ) {
			part = new Partie();
		}
		
		return part;
	}
	
	
	
	
	/* ### SETTER ### */
	
	
	

	/**
	 * @param part the part to set
	 */
	public static void setPart(Partie part) {
		Partie.part = part;
	}

	/**
	 * @param nbTours the nbTours to set
	 */
	public void setNbTours(int nbTours) {
		this.nbTours = nbTours;
	}

	/**
	 * @param tourEnCours the tourEnCours to set
	 */
	public void setTourEnCours(int tourEnCours) {
		this.tourEnCours = tourEnCours;
	}

	/**
	 * @param lstJoueurs the lstJoueurs to set
	 */
	public void setLstJoueurs(List<Joueur> lstJoueurs) {
		this.lstJoueurs = lstJoueurs;
	}

	/**
	 * @param joueurEnCours the joueurEnCours to set
	 */
	public void setIndexJoueurEnCours(int indexJoueurEnCours) {
		this.indexJoueurEnCours = indexJoueurEnCours;
	}

	/**
	 * @param joueurEnCours the joueurEnCours to set
	 */
	public void setJoueurEnCours(Joueur joueurEnCours) {
		this.joueurEnCours = joueurEnCours;
	}

	/**
	 * @param peuplesPris the peuplesPris to set
	 */
	public void setPeuplesPris(List<Class<? extends Peuple>> peuplesPris) {
		this.peuplesPris = peuplesPris;
	}

	/**
	 * @param peuplesDispo the peuplesDispo to set
	 */
	public void setPeuplesDispo(List<Class<? extends Peuple>> peuplesDispo) {
		this.peuplesDispo = peuplesDispo;
	}

	/**
	 * @param pouvoirsPris the pouvoirsPris to set
	 */
	public void setPouvoirsPris(List<Class<? extends Pouvoir>> pouvoirsPris) {
		this.pouvoirsPris = pouvoirsPris;
	}

	/**
	 * @param pouvoirsDispo the pouvoirsDispo to set
	 */
	public void setPouvoirsDispo(List<Class<? extends Pouvoir>> pouvoirsDispo) {
		this.pouvoirsDispo = pouvoirsDispo;
	}

	/**
	 * @param argentPeuple the argentPeuple to set
	 */
	public void setArgentPeuple(List<Integer> argentPeuple) {
		this.argentPeuple = argentPeuple;
	}

	/**
	 * @param plateau the plateau to set
	 */
	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
	
	/**
	 * 
	 * @param noEtape
	 */
	public void setEtape(int noEtape){
		this.etape = noEtape;
	}
	
	/**
	 * passe au tour suivant
	 */
	public void passeTourSuivant(){
		this.tourEnCours++;
	}

}
