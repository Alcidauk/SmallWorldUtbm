package com.utbm.smallWorld.gui;

import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.utbm.smallWorld.Joueur;
import com.utbm.smallWorld.Partie;

public class Game extends JFrame {
	/** Image de background de la fenêtre */
	public static Icon BACKGROUND = null;
	
	/** Partie en cours */
	private Partie partieEnCours;

	/** Génération de l'image de background */
	static {
		try {
			BACKGROUND = new ImageIcon(ImageIO.read(new File("res/win/background.jpg")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	public Game() {
		// Config
		setTitle("SmallWorld UTBM");
		setSize(1280, 768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Fond d'écran
		JLabel background = new JLabel();
		background.setIcon(Game.BACKGROUND);
		FlowLayout layBack = new FlowLayout();
		layBack.setVgap(0);
		background.setLayout(layBack);
		
		setContentPane(background);
		
		partieEnCours = Partie.getInstance();
		
		askNbJoueur();
	}
	
	public void askNbJoueur() {
		WinMenu nbMenu = new WinMenuNbJoueur();
		
		setVisible(false);
		int nbJoueur = nbMenu.open();
		setVisible(true);
		
		for (int i = 0; i < nbJoueur; i++) {
			String name = Prompt.ask("Choissiez un nom pour le joueur n°" + (i + 1));
			Joueur j = new Joueur(name, Partie.DEFAULT_MONNAIE);
			
			partieEnCours.ajouterJoueur(j);
		}
	}
	
	
	public static void main(String[] args) {
		JFrame a = new Game();
		a.setVisible(true);
	}
}
