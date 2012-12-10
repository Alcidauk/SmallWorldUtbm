package com.utbm.smallWorld;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.utbm.smallWorld.peuples.*;
import com.utbm.smallWorld.pouvoirs.*;


public class Partie {
	
	private static Partie part;
	
	protected int nbTours = 10;
	
	protected int tourEnCours = 0;
	
	protected List<Joueur> lstJoueurs = null;
	
	protected Joueur joueurEnCours = null;
	
	protected List<Peuple> peuplesPris = null;
	
	protected List<Peuple> peuplesDispo = null;
	
	protected List<Pouvoir> pouvoirsPris = null;
	
	protected List<Pouvoir> pouvoirsDispo = null;
	
	private Partie(){
		lstJoueurs = new LinkedList<Joueur>();
		peuplesPris = new LinkedList<Peuple>();
		peuplesDispo = new LinkedList<Peuple>();
		pouvoirsPris = new LinkedList<Pouvoir>();
		pouvoirsDispo = new LinkedList<Pouvoir>();
		
		initPeuples();
		initPouvoirs();
	}
	
	public static Partie getInstance(){
		if( part == null )
			part = new Partie();
		return part;
	}

	public static void remettreBoite(Peuple peuple) {
		// TODO Auto-generated method stub
		
	}
	
	public void ajouterJoueur(Joueur j){
		lstJoueurs.add(j);
	}
	
	public void initPeuples(){
		
		peuplesDispo.add(new PeupleAdministration());

		peuplesDispo.add(new PeupleAlternance());

		peuplesDispo.add(new PeupleChercheur());
		
		peuplesDispo.add(new PeupleCRI());
		
		peuplesDispo.add(new PeupleDirecteur());
		
		peuplesDispo.add(new PeupleEtudiantBranche());
		
		peuplesDispo.add(new PeupleEtudiantTC());
		
		peuplesDispo.add(new PeupleProfHumanite());
		
		peuplesDispo.add(new PeupleProfScience());
		
		peuplesDispo.add(new PeupleRats());
	
		peuplesDispo.add(new PeupleServiceTechnique());
		
		/* pour randomizer le choix peuple/pouvoir */
		
		Collections.shuffle(peuplesDispo);
	}
	
	public void initPouvoirs(){
		
		pouvoirsDispo.add(new PouvoirAvare());
		
		pouvoirsDispo.add(new PouvoirGeek());
		
		pouvoirsDispo.add(new PouvoirParesseux());
		
		pouvoirsDispo.add(new PouvoirIntello());
		
		pouvoirsDispo.add(new PouvoirFetard());
	}
	
	public int getNbTours(){
		return this.nbTours;
	}
	
	public int getTourEnCours(){
		return this.tourEnCours;
	}
	

	
	
}
