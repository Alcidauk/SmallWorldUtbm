package com.utbm.smallWorld.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import com.utbm.smallWorld.Partie;

/**
 * TODO commentaire
 *
 * @author UTBM'Student
 * @version 1.0
 */
public class JoueurAction extends JButton implements MouseListener {
	/** Stub */
	private static final long serialVersionUID = 1L;
	
	/** Indice du bouton */
	private int choixBouton;

	/**
	 * Constructeur
	 * @param nom Texte du bouton ?
	 * @param choixB indice du bouton
	 */
	public JoueurAction(String nom, int choixB){
		super(nom);
		
		choixBouton = choixB;
		
		this.addMouseListener(this);
	}

	
	/**
	 * 
	 */
	public void mousePressed(MouseEvent arg0) {
		switch( choixBouton ){
			case 0:
				Partie.getInstance().cliqueFinTour();
				break;
			case 1:
				Partie.getInstance().cliqueFinRedeploiement();
				break;
			case 2:
				Partie.getInstance().cliqueDeclin();
				break;
		}

	}

	
	/* STUBS */
	
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}

}
