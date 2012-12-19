package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.utbm.smallWorld.Joueur;
import com.utbm.smallWorld.Partie;
import com.utbm.smallWorld.Plateau;

public class Game extends JFrame {
	/** Stub */
	private static final long serialVersionUID = 1L;

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
		setLayout(null);
		
		// Fond d'écran
		JLabel background = new JLabel();
		background.setIcon(Game.BACKGROUND);
		background.setBounds(0, 0, 1274, 740);
		
		setContentPane(background);
		
		partieEnCours = Partie.getInstance();
		
		askNbJoueur();
	}
	
	public void askNbJoueur() {
		WinMenu nbMenu = new WinMenuNbJoueur();
		
		setVisible(false);
		int nbJoueur = nbMenu.open();
		setVisible(true);
		
		loadPlateau(nbJoueur);
		
		for (int i = 0; i < nbJoueur; i++) {
			String name;
			
			do {
				name = Prompt.ask("Choissiez un nom pour le joueur n°" + (i + 1));
			} while (name.length() == 0);
			
			Joueur j = new Joueur(name, Partie.DEFAULT_MONNAIE);
			
			partieEnCours.ajouterJoueur(j);
		}
		
		begin();
	}

	private void loadPlateau(int nbJoueur) {
		// TODO
		try {
			Icon plIm = new ImageIcon(ImageIO.read(new File("res/maps/map2p.png")));
			
			Plateau plateau = new Plateau(plIm);
			
			partieEnCours.setPlateau(plateau);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void begin() {
		JLabel map = new JLabel();
		map.setIcon(partieEnCours.getPlateau().getIcon());
		map.setBounds(0, 0, 1274, 740);
		
		setContentPane(map);
		
		JPanel a = new JPanel();
		a.setBounds(0, 450, 385, 290);
		a.setPreferredSize(new Dimension(385, 290));
		a.setBackground(new Color(31, 31, 31));
		a.setForeground(Color.WHITE);
		a.setOpaque(true);
		
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		
		a.setLayout(flow);
		
		JLabel b = new JLabel("Joueur 1");
		b.setHorizontalAlignment(JLabel.CENTER);
		b.setPreferredSize(new Dimension(385, 30));
		b.setBackground(Color.YELLOW);
		b.setOpaque(true);
		
		a.add(b);
		
		JTextArea c = new JTextArea();
		c.append("\n");
		c.append("Argent........... 13 jetons\n");
		c.append("Territoire....... 4\n");
		c.append("Unités totales... 17\n");
		c.append("Unités en main... 0\n");
		c.setPreferredSize(new Dimension(340, 240));
		c.setForeground(Color.WHITE);
		c.setOpaque(false);
		c.setEditable(false);
		c.setFont(new Font(Font.MONOSPACED, 0, 15));
		
		a.add(c);
		
		getContentPane().add(a);
		
		repaint();
	}

	public static void main(String[] args) {
		JFrame a = new Game();
		a.setVisible(true);
	}
}
