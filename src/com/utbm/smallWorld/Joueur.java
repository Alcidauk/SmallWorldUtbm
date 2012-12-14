package com.utbm.smallWorld;


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
	//Fonction Attaquer un Territoire
	public void attaquer(Territoire t)
	{
		
	}
	public void pertePeuple(Peuple peuple) {
		// TODO Auto-generated method stub
		
	}
	
}

