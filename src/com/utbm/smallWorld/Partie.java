package com.utbm.smallWorld;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.utbm.smallWorld.peuples.*;
import com.utbm.smallWorld.pouvoirs.*;


/**
 * Représentation d'une partie
 * Singleton
 * 
 */
public class Partie {
	public static final int DEFAULT_MONNAIE = 10;

	/** Singleton, instance de la partie en cours */
	private static Partie part;
	
	/** Nombre de tour total de la partie */
	protected int nbTours = 10;
	
	/** Tour en cours de jeu */
	protected int tourEnCours = 0;
	
	/** Liste des joueurs participant à la partie */
	protected List<Joueur> lstJoueurs = null;
	
	/** Index du joueur jouant à un instant donné */
	protected int joueurEnCours = 0;
	
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
	
	/**
	 * @return instance de la Partie en cours
	 */
	public static Partie getInstance(){
		if( part == null ) {
			part = new Partie();
		}
		
		return part;
	}
	
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
	 * Ajoute un joueur à la liste des joueurs participants
	 * @param j Joueur à ajouter
	 */
	public void ajouterJoueur(Joueur j){
		lstJoueurs.add(j);
	}
	
	/**
	 * Initialise les listes des peuples
	 */
	private void initPeuples(){
		
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
	}
	
	/**
	 * Marque une combinaison Peuple/Pouvoir comme utilisée
	 * @param numCombinaison index dans la liste
	 */
	public void mettreCombinaisonEnJeu(int numCombinaison) {
		pouvoirsPris.add( pouvoirsDispo.get(numCombinaison) );
		peuplesPris.add( peuplesDispo.get(numCombinaison) );
		
		pouvoirsDispo.remove(numCombinaison);
		peuplesDispo.remove(numCombinaison);
	}
	
	public void tourSuivant(){
		this.tourEnCours++;
	}
	
	public void passerJoueurSuivant(){
		
		if (this.joueurEnCours < this.lstJoueurs.size()) {
			this.joueurEnCours++;
		}
		else {
			this.joueurEnCours = 0;
		}
	}

	/**
	 * @return the defaultMonnaie
	 */
	public static int getDefaultMonnaie() {
		return DEFAULT_MONNAIE;
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
	public int getJoueurEnCours() {
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
	public void setJoueurEnCours(int joueurEnCours) {
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
	
}
