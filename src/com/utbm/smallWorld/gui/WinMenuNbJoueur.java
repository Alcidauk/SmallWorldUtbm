package com.utbm.smallWorld.gui;

public class WinMenuNbJoueur extends WinMenu {
	/** Stub */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur
	 */
	public WinMenuNbJoueur() {
		super("Combien de joueur pour cette partie ?");
		
		newItem("2 joueurs", 2);
		newItem("3 joueurs", 3);
		newItem("4 joueurs", 4);
	}
	
	public static void main(String[] args) {
		WinMenu w = new WinMenuNbJoueur();
		System.out.println(w.open());
	}
}
