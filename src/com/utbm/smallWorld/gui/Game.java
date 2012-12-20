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
import javax.swing.JButton;
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

	public static final Color[] JOUEUR_BACKGROUND = {Color.YELLOW, Color.PINK, Color.CYAN, Color.GREEN};
	public static final Color[] JOUEUR_FOREGROUND = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK};
	
	/** Fenêtre en cours */
	private static Game instance;
	
	/** Partie en cours */
	private Partie partieEnCours;

	private JTextArea playerInfo;

	private JLabel headerJoueur;

	private JLabel headerAction;

	private JLabel headerInfo;

	private JPanel infoPanel;

	private JTextArea infoTx;

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
		instance = this;
		
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
		buildActionPanel();
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
		playerPanel.setBounds(0, 450, 385, 200);
		playerPanel.setPreferredSize(new Dimension(385, 200));
		playerPanel.setBackground(new Color(31, 31, 31));
		playerPanel.setForeground(Color.WHITE);
		playerPanel.setOpaque(true);
		
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		playerPanel.setLayout(flow);
		
		headerJoueur = new JLabel("Joueur 1");
		headerJoueur.setHorizontalAlignment(JLabel.CENTER);
		headerJoueur.setPreferredSize(new Dimension(385, 30));
		headerJoueur.setBackground(Color.YELLOW);
		headerJoueur.setOpaque(true);
		
		playerPanel.add(headerJoueur);
		
		playerInfo = new JTextArea();
		playerInfo.append("\n");
		playerInfo.append("Argent........... 13 jetons\n");
		playerInfo.append("Territoire....... 4\n");
		playerInfo.append("Unités totales... 17\n");
		playerInfo.append("Unités en main... 0\n");
		playerInfo.setPreferredSize(new Dimension(340, 150));
		playerInfo.setForeground(Color.WHITE);
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		playerInfo.setFont(new Font(Font.MONOSPACED, 0, 15));
		
		playerPanel.add(playerInfo);
		
		getContentPane().add(playerPanel);
	}
	
	private void buildActionPanel() {
		JPanel actionPanel = new JPanel();
		actionPanel.setBounds(0, 650, 385, 90);
		actionPanel.setPreferredSize(new Dimension(385, 90));
		actionPanel.setBackground(new Color(31, 31, 31));
		actionPanel.setForeground(Color.WHITE);
		actionPanel.setOpaque(true);
		
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		actionPanel.setLayout(flow);

		headerAction = new JLabel("Actions");
		headerAction.setHorizontalAlignment(JLabel.CENTER);
		headerAction.setPreferredSize(new Dimension(385, 30));
		headerAction.setBackground(Color.YELLOW);
		headerAction.setOpaque(true);
		
		actionPanel.add(headerAction);
		
		JPanel actions = new JPanel(new GridBagLayout());
		actions.setPreferredSize(new Dimension(385, 60));
		actions.setOpaque(false);
		
		GridBagConstraints constraint = new GridBagConstraints();
		
		actions.add(new JButton("Déclin"), constraint);
		actions.add(new JButton("Finir tour"), constraint);
		actions.add(new JButton("Fin redéploiement"), constraint);
		
		actionPanel.add(actions);

		getContentPane().add(actionPanel);
	}
	
	private void buildInfoPanel() {
		infoPanel = new JPanel();
		infoPanel.setBounds(1274-400, 450, 400, 290);
		infoPanel.setPreferredSize(new Dimension(400, 290));
		infoPanel.setBackground(new Color(31, 31, 31));
		infoPanel.setForeground(Color.WHITE);
		infoPanel.setOpaque(true);
		infoPanel.setVisible(false);
		
		FlowLayout flow = new FlowLayout();
		flow.setVgap(0);
		infoPanel.setLayout(flow);

		headerInfo = new JLabel("Informations Territoire");
		headerInfo.setHorizontalAlignment(JLabel.CENTER);
		headerInfo.setPreferredSize(new Dimension(400, 30));
		headerInfo.setBackground(Color.YELLOW);
		headerInfo.setOpaque(true);
		
		infoPanel.add(headerInfo);
		
		infoTx = new JTextArea();
		
		infoTx.setPreferredSize(new Dimension(355, 240));
		infoTx.setForeground(Color.WHITE);
		infoTx.setOpaque(false);
		infoTx.setEditable(false);
		infoTx.setFont(new Font(Font.MONOSPACED, 0, 15));
		
		infoPanel.add(infoTx);
		
		getContentPane().add(infoPanel);
	}
	
	private void buildTerritoires() {
		// TODO
		getContentPane().add(new TerritoireCase(new Rectangle(5, 5, 191, 100)));
		getContentPane().add(new TerritoireCase(new Rectangle(5, 105, 191, 173)));
		
		getContentPane().add(new TerritoireCase(new Rectangle(196, 91, 216, 150)));
		getContentPane().add(new TerritoireCase(new Rectangle(412, 91, 60, 150)));
		
		getContentPane().add(new TerritoireCase(new Rectangle(472, 91, 137, 85)));
		getContentPane().add(new TerritoireCase(new Rectangle(609, 91, 138, 85)));
		getContentPane().add(new TerritoireCase(new Rectangle(747, 91, 190, 85)));

		getContentPane().add(new TerritoireCase(new Rectangle(472, 176, 158, 65)));
		getContentPane().add(new TerritoireCase(new Rectangle(630, 176, 157, 65)));
		getContentPane().add(new TerritoireCase(new Rectangle(787, 176, 150, 65)));

		getContentPane().add(new TerritoireCase(new Rectangle(937, 91, 165, 340)));
		getContentPane().add(new TerritoireCase(new Rectangle(1102, 91, 160, 170)));
		getContentPane().add(new TerritoireCase(new Rectangle(1102, 261, 160, 170)));

		getContentPane().add(new TerritoireCase(new Rectangle(196, 241, 187, 145)));
		getContentPane().add(new TerritoireCase(new Rectangle(383, 241, 108, 190)));
		getContentPane().add(new TerritoireCase(new Rectangle(491, 241, 99, 190)));
		getContentPane().add(new TerritoireCase(new Rectangle(590, 241, 99, 190)));
		getContentPane().add(new TerritoireCase(new Rectangle(689, 241, 99, 190)));
		getContentPane().add(new TerritoireCase(new Rectangle(788, 241, 149, 190)));

		getContentPane().add(new TerritoireCase(new Rectangle(410, 431, 150, 104)));
		getContentPane().add(new TerritoireCase(new Rectangle(410, 535, 150, 200)));
		getContentPane().add(new TerritoireCase(new Rectangle(560, 431, 170, 290)));

	}
	
	
	public void showInfo(String tx) {
		infoTx.setText("\n" + tx);
		
		infoPanel.setVisible(true);
	}
	
	
	public void hideInfo() {
		infoPanel.setVisible(false);
	}

	
	/**
	 * @return the instance
	 */
	public static Game getInstance() {
		return instance;
	}

	
	
	
	public static void main(String[] args) {
		JFrame a = new Game();
		a.setVisible(true);
	}
}
