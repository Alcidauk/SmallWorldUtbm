package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
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

	private JLabel playerName;

	private JTextArea playerInfo;

	/** Génération de l'image de background */
	static {
		try {
			BACKGROUND = new ImageIcon(ImageIO.read(Game.class.getResource("/res/win/background.jpg")));
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

		buildBackground();
		buildPlayerPanel();
		buildInfoPanel();
		buildOptionPanel();
		buildTerritoires();
		
		repaint();
	}

	private void loadPlateau(int nbJoueur) {
		// TODO
		try {
			Icon plIm = new ImageIcon(ImageIO.read(Game.class.getResource("/res/maps/map2p.png")));
			
			Plateau plateau = new Plateau(plIm);
			
			partieEnCours.setPlateau(plateau);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void buildBackground() {
		JLabel map = new JLabel();
		map.setIcon(partieEnCours.getPlateau().getIcon());
		map.setBounds(0, 0, 1274, 740);
		
		setContentPane(map);
	}
	
	private void buildPlayerPanel() {
		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(0, 450, 385, 290);
		playerPanel.setPreferredSize(new Dimension(385, 290));
		playerPanel.setBackground(new Color(31, 31, 31));
		playerPanel.setForeground(Color.WHITE);
		playerPanel.setOpaque(true);
		
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		
		playerPanel.setLayout(flow);
		
		playerName = new JLabel("Joueur 1");
		playerName.setHorizontalAlignment(JLabel.CENTER);
		playerName.setPreferredSize(new Dimension(385, 30));
		playerName.setBackground(Color.YELLOW);
		playerName.setOpaque(true);
		
		playerPanel.add(playerName);
		
		playerInfo = new JTextArea();
		playerInfo.append("\n");
		playerInfo.append("Argent........... 13 jetons\n");
		playerInfo.append("Territoire....... 4\n");
		playerInfo.append("Unités totales... 17\n");
		playerInfo.append("Unités en main... 0\n");
		playerInfo.setPreferredSize(new Dimension(340, 240));
		playerInfo.setForeground(Color.WHITE);
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		playerInfo.setFont(new Font(Font.MONOSPACED, 0, 15));
		
		playerPanel.add(playerInfo);
		
		getContentPane().add(playerPanel);
	}
	
	private void buildOptionPanel() {
		
	}
	
	private void buildTerritoires() {
		// TODO
		getContentPane().add(new TerritoireCase(new Rectangle(206, 101, 206, 130)));
		getContentPane().add(new TerritoireCase(new Rectangle(412, 101, 60, 130)));

	}
	
	private void buildInfoPanel() {
		
	}

	
	
	
	
	public static void main(String[] args) {
		JFrame a = new Game();
		a.setVisible(true);
	}
}
