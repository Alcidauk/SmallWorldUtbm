package com.utbm.smallWorld;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Joueur {

	private String nom;
	private int argent;
	private Peuple peuple ;
	private LinkedList<Peuple> peuplesDeclin; /*je suis pas sure si c'est une linkedlist ou un tableau*/
	
	// Constructer
	public Joeur()
	{
		nom= null;
		argent=0;
		peuple= new Peuple(null);
		peuplesDeclin= null;
	}
	public Joeur(string n, int monnaie, Peuple p)
	{
		nom= n;
		argent= monnaie;
		peuple =new Peuple(p);
		this.peuplesDeclin= new LinkedList<Peuple>;  
		
	}
	
	// Fonctions Setter
	public void setNom(string n)
	{
		nom = n;
	}
	public void setArgent(int n)
	{
		argent = n;
	}
	public void setPeuple(Peuple p)
	{
		peuple = new peuple(p);
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

