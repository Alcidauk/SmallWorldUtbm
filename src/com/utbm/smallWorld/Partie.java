package com.utbm.smallWorld;


import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.utbm.smallWorld.peuples.*;
import com.utbm.smallWorld.pouvoirs.*;


public class Partie {
	
	private static Partie part;
	
	protected int nbTours = 10;
	
	protected int tourEnCours = 0;
	
	protected List<Joueur> lstJoueurs = null;
	
	protected int joueurEnCours = 0;
	
	protected List<Class<? extends Peuple>> peuplesPris = null;
	
	protected List<Class<? extends Peuple>> peuplesDispo = null;
	
	protected List<Class<? extends Pouvoir>> pouvoirsPris = null;
	
	protected List<Class<? extends Pouvoir>> pouvoirsDispo = null;
	
	private Partie(){
		lstJoueurs = new LinkedList<Joueur>();
		peuplesPris = new LinkedList<Class<? extends Peuple>>();
		peuplesDispo = new LinkedList<Class<? extends Peuple>>();
		pouvoirsPris = new LinkedList<Class<? extends Pouvoir>>();
		pouvoirsDispo = new LinkedList<Class<? extends Pouvoir>>();
		
		initPeuples();
		initPouvoirs();
	}
	
	public static Partie getInstance(){
		if( part == null )
			part = new Partie();
		return part;
	}

	public void remettreBoite(Peuple peuple) {
		
		Iterator<Class<? extends Peuple>> it = peuplesPris.iterator();
		Class<? extends Peuple> c = null;
		
		
		while( it.hasNext() && c == null ){
			Class<? extends Peuple> tmp = it.next();
	
			if( tmp.isInstance(peuple) ){
				c = tmp;
			}			
			
		}
		
		peuplesPris.remove(c);
		peuplesDispo.add(c);
		
		
		
		Iterator<Class<? extends Pouvoir>> ite = pouvoirsPris.iterator();
		Class<? extends Pouvoir> cl = null;
		
		
		while( ite.hasNext() && cl == null ){
			Class<? extends Pouvoir> tmp = ite.next();
	
			if( tmp.isInstance(peuple.getPouvoir()) ){
				cl = tmp;
			}			
			
		}
		
		pouvoirsPris.remove(cl);
		pouvoirsDispo.add(cl);
		
	}
	
	public void ajouterJoueur(Joueur j){
		lstJoueurs.add(j);
	}
	
	public void initPeuples(){
		
		peuplesDispo.add(PeupleAdministration.class);

		peuplesDispo.add(PeupleAlternance.class);

		peuplesDispo.add(PeupleChercheur.class);
		
		peuplesDispo.add(PeupleCRI.class);
		
		peuplesDispo.add(PeupleDirecteur.class);
		
		peuplesDispo.add(PeupleEtudiantBranche.class);
		
		peuplesDispo.add(PeupleEtudiantTC.class);
		
		peuplesDispo.add(PeupleProfHumanite.class);
		
		peuplesDispo.add(PeupleProfScience.class);
		
		peuplesDispo.add(PeupleRats.class);
	
		peuplesDispo.add(PeupleServiceTechnique.class);
		
		/* pour randomizer le choix peuple/pouvoir */
		
		Collections.shuffle(peuplesDispo);
	}
	
	public void initPouvoirs(){
		
		pouvoirsDispo.add( PouvoirAvare.class);
		
		pouvoirsDispo.add( PouvoirGeek.class);
		
		pouvoirsDispo.add( PouvoirParesseux.class);
		
		pouvoirsDispo.add( PouvoirIntello.class);
		
		pouvoirsDispo.add( PouvoirFetard.class);
	}
	
	public void mettreCombinaisonEnJeu(int numCombinaison){
		pouvoirsPris.add( pouvoirsDispo.get(numCombinaison) );
		peuplesPris.add( peuplesDispo.get(numCombinaison) );
		
		pouvoirsDispo.remove(0);
		peuplesDispo.remove(0);
	}
	
	public int getNbTours(){
		return this.nbTours;
	}
	
	public int getTourEnCours(){
		return this.tourEnCours;
	}
	
	public void changerTour(){
		this.tourEnCours++;
	}
	
	public void passerJoueurSuivant(){
		
		if( this.joueurEnCours < this.lstJoueurs.size() ){
			this.joueurEnCours++;
		}else{
			this.joueurEnCours = 0;
		}
		
	}
	

	
	
}
