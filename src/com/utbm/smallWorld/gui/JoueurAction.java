package com.utbm.smallWorld.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import com.utbm.smallWorld.Partie;

public class JoueurAction extends JButton implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int choixBouton;

	public JoueurAction(String nom, int choixB){
		super(nom);
		
		choixBouton = choixB;
		
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		switch( choixBouton ){
			case 0:
				Partie.getInstance().cliqueFinTour();
			case 1:
				Partie.getInstance().cliqueFinRedeploiement();
			case 2:
				Partie.getInstance().cliqueDeclin();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
