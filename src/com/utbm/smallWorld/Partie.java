package com.utbm.smallWorld;


import java.util.LinkedList;
import java.util.List;



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
		
	}
	
	public void initPouvoirs(){
		
	}
	
	
}
