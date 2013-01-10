package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.utbm.smallWorld.Joueur;
import com.utbm.smallWorld.Partie;
import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Plateau;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

/**
 * Représentation graphique du jeu
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public class Game extends JFrame {
	/** Stub */
	private static final long serialVersionUID = 1L;

	/** Image de background de la fenêtre */
	public static Icon BACKGROUND = null;
	
	/** Couleur de background des différents joueurs */
	public static final Color[] JOUEUR_BACKGROUND = {Color.YELLOW, Color.PINK, Color.CYAN, Color.GREEN, Color.GRAY};

	/** Couleur d'écriture des différents joueurs */
	public static final Color[] JOUEUR_FOREGROUND = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.WHITE};
	
	/** Couleur de background des différents joueurs pour leur peuple en déclin */
	public static final Color[] JOUEUR_BACKGROUND_DECLIN = {new Color(255, 255, 153), new Color(255, 204, 255), 
		new Color(204, 255, 255), new Color(153, 255, 153), new Color(204, 204, 204)};
	
	/** Fenêtre en cours */
	private static Game instance;
	
	/** Partie en cours */
	private Partie partieEnCours;
	
	/** Zone contenant les informations sur le joueur en cours */
	private JTextArea playerInfo;
	
	/** Entete de la section contenant les infos joueurs */
	private JLabel headerJoueur;

	/** Entete de la section contenant les différentes actions possibles */
	private JLabel headerAction;
	
	/** Entete de la section contenant des informations, notamment sur les territoires */
	private JLabel headerInfo;
	
	/** Section contenant des informations sur les territories */
	private JPanel infoPanel;

	/** Zone de texte contenant les informations sur les territoires */
	private JTextArea infoTx;
	
	/** Liste des territoires de la map */
	private List<TerritoireCase> territoires;

	private JoueurAction btnDeclin;

	private JoueurAction btnTour;

	private JoueurAction btnDeploie;

	private JPanel infoTemp;

	private JLabel labInfoTemp;
	

	/** Génération de l'image de background */
	static {
		try {
			BACKGROUND = new ImageIcon(ImageIO.read(Game.class.getResource("/res/win/background.jpg")));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	
	/**
	 * Constructeur
	 */
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
		
		// Initialisation de la partie
		partieEnCours = Partie.getInstance();
		territoires = new LinkedList<TerritoireCase>();
		
		setVisible(true);
		
		// Affiche la fenêtre nombre de joueur
		askNbJoueur();
		
		// Construction des éléments de la fenêtre
		buildBackground();
		buildPlayerPanel();
		buildInfoPanel();
		buildActionPanel();
		buildTerritoires();
		
		// Mise à jour des informations affichées
		majInfos();
		
		partieEnCours.nouveauTour();
	}
	
	
	
	
	
	/* ### Initialisations ### */
	
	


	
	/**
	 * Chargement du plateau correspondant au paramètre
	 * @param nbJoueur nombre de joueur
	 */
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

	
	
	
	/**
	 * Création du fond de la fenêtre
	 */
	private void buildBackground() {
		JLabel map = new JLabel();
		map.setIcon(partieEnCours.getPlateau().getIcon());
		map.setBounds(0, 0, 1274, 740);
		
		setContentPane(map);
	}
	
	
	/**
	 * Création de la zone Information Player
	 */
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
		playerInfo.setPreferredSize(new Dimension(340, 150));
		playerInfo.setForeground(Color.WHITE);
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		playerInfo.setFont(new Font(Font.MONOSPACED, 0, 15));
		
		playerPanel.add(playerInfo);
		
		getContentPane().add(playerPanel);
	}
	
	
	/**
	 * Création de la zone Actions
	 */
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
		
		btnDeclin = new JoueurAction("Déclin", 2);
		btnTour = new JoueurAction("Finir tour", 0);
		btnDeploie = new JoueurAction("Fin redéploiement", 1);
		
		actions.add(btnDeclin, constraint);
		actions.add(btnTour, constraint);
		actions.add(btnDeploie, constraint);
		
		actionPanel.add(actions);

		getContentPane().add(actionPanel);
	}
	
	
	/**
	 * Création de la zone Information Territoire
	 */
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
		
		// Infos temporaires
		infoTemp = new JPanel();
		infoTemp.setBounds(0, 0, 1274, 740-290);
		infoTemp.setPreferredSize(new Dimension(1274, 740-290));
		infoTemp.setBackground(new Color(31, 31, 31));
		infoTemp.setOpaque(true);
		infoTemp.setVisible(false);
		
		labInfoTemp = new JLabel("");
		labInfoTemp.setForeground(Color.WHITE);
		labInfoTemp.setFont(new Font("sherif", 0, 60));
		labInfoTemp.setVerticalAlignment(JLabel.CENTER);
		labInfoTemp.setHorizontalAlignment(JLabel.CENTER);
		labInfoTemp.setPreferredSize(new Dimension(1274, 740-290));
		
		infoTemp.add(labInfoTemp);
		
		
		getContentPane().add(infoPanel);
		getContentPane().add(infoTemp);
	}
	
	
	/**
	 * Création des zones des territoires
	 */
	private void buildTerritoires() {
		// TODO nombre de player
		territoires = SQLite.createTerritoires(2);
		
		for (TerritoireCase t : territoires) {
			getContentPane().add(t);
		}
	}
	
	
	
	
	
	/* ### SOUS FENETRES ### */

	
	
	/**
	 * 
	 */
	public void selectionPeuple() {
		try {
			Joueur j = partieEnCours.getJoueurEnCours();
			
			WinMenu choixPeuple = new WinMenu(j.getNom() + " (" + j.getArgent() + " $), choisissez votre peuple :");
    		
    		int i = -1;
    		List<Class<? extends Peuple>> ppl = partieEnCours.getPeuplesDispo();
    		List<Class<? extends Pouvoir>> pov = partieEnCours.getPouvoirsDispo();
    		List<Integer> argent = partieEnCours.getArgentPeuple();
    		
    		Iterator<Class<? extends Peuple>> itPpl = ppl.iterator();
    		Iterator<Class<? extends Pouvoir>> itPov = pov.iterator();
    		Iterator<Integer> itArgent = argent.iterator();
    		
    		while (itPpl.hasNext() && ++i < 6) {
    			Class<? extends Peuple> clPpl = itPpl.next();
    			Class<? extends Pouvoir> clPov = itPov.next();
    			int arg = itArgent.next().intValue();
    			
    			Peuple insPpl = clPpl.newInstance();
    			Pouvoir insPov = clPov.newInstance();
    			
    			String tx = arg + "$ - " + insPpl.getNom() + " (" + insPpl.getNbUniteDepart() + " unit.) + " + insPov.getNom() + " (" + insPov.getNbUniteApporte() + " unit.)";
    			String desc = insPpl.getNom() + " : " + insPpl.getDescription() + "\n" + insPov.getNom() + " : " + insPov.getDesc();
    			
    			JLabel it = choixPeuple.newItem(tx, desc, i);
    			it.setHorizontalAlignment(JLabel.LEFT);
    		}
    		
    		// Affiche la fenêtre de choix
    		int indexPeuple;
    		int cout = 0;
    		
    		do {
    			indexPeuple = choixPeuple.open();
    			
    			if (indexPeuple >= 0) {
    				cout = indexPeuple - argent.get(indexPeuple).intValue();
    				
    				if (cout > j.getArgent()) {
    					choixPeuple.setHeadTitle("Vous n'avez pas assez d'argent ! (" + j.getArgent() + " $)");
    					indexPeuple = -1;
    				}
    			}
    		} while (indexPeuple < 0);
    		
    		// Création du peuple
    		Peuple pl = ppl.get(indexPeuple).newInstance();
    		pl.setPouvoir(pov.get(indexPeuple).newInstance());
    		pl.setNbUnite(pl.getNbUniteDepart() + pl.getPouvoir().getNbUniteApporte());
    		pl.setNbUniteEnMain(pl.getNbUnite());
    		
    		// Attribution du joueur
    		pl.setJoueur(j);
    		j.setPeuple(pl);
    		j.setArgent(j.getArgent() - cout);
    		
    		// Màj des listes
    		partieEnCours.getPeuplesPris().add(ppl.get(indexPeuple));
    		partieEnCours.getPouvoirsPris().add(pov.get(indexPeuple));
    		
    		ppl.remove(indexPeuple);
    		pov.remove(indexPeuple);
    		argent.remove(indexPeuple);
    		
    		// màj argent
    		for (int a = 0; a < indexPeuple; a++) {
    			argent.set(a, argent.get(a) + 1);
    		}
    		
    		majInfos();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Affiche la fenêtre de demande du nombre de joueur
	 */
	public void askNbJoueur() {
		// Création de la fenêtre de choix
		WinMenu nbMenu = new WinMenu("Combien de joueurs pour cette partie ?");

		nbMenu.newItem("2 joueurs", 2);
		nbMenu.newItem("3 joueurs", 3);
		nbMenu.newItem("4 joueurs", 4);
		
		// Affiche la fenêtre de choix
		int nbJoueur = nbMenu.open();
		
		// Si la fenêtre a été fermée, on termine le processus
		if (nbJoueur < 0) {
			System.exit(0);
		}
		
		// On charge le plateau correspondant au nom de joueur sélectionné
		loadPlateau(nbJoueur);
		
		// On crée les joueurs
		for (int i = 0; i < nbJoueur; i++) {
			String name;
			
			do {
				name = Prompt.ask("Choissiez un nom pour le joueur n°" + (i + 1));
			} while (name.length() == 0);
			
			Joueur j = new Joueur(name, Partie.DEFAULT_ARGENT);
			j.setIndice(i);
			
			partieEnCours.ajouterJoueur(j);
		}
	}
	
	
	/**
	 * Affiche la fenêtre de confirmation d'abandon d'un territoire
	 */
	public boolean askAbandon(Territoire t) {
		// Création de la fenêtre de choix
		WinMenu confMenu = new WinMenu("Abandonner le territoire ?");

		confMenu.newItem("Oui", 0);
		confMenu.newItem("Non", 1);
		
		// Affiche la fenêtre de choix
		int conf = confMenu.open();
		
		return conf == 0;
	}
	
	
	/**
	 * Affiche la fenêtre de confirmation d'attaque d'un territoire
	 */
	public boolean askAttaque(Territoire t) {
		// Création de la fenêtre de choix
		WinMenu confMenu = new WinMenu("Attaquer le territoire ?");

		confMenu.newItem("Oui", 0);
		confMenu.newItem("Non", 1);
		
		// Affiche la fenêtre de choix
		int conf = confMenu.open();
		
		return conf == 0;
	}
	
	/**
	 * Affiche la fenêtre de demande pour un lancé de dé lors du clic sur un bouton demandant une confirmation
	 */
	public boolean askConf(String message) {
		
		// Création de la fenêtre de choix
		WinMenu confMenu = new WinMenu(message);

		confMenu.newItem("Oui", 0);
		confMenu.newItem("Non", 1);
		
		// Affiche la fenêtre de choix
		int conf = confMenu.open();

		return conf == 0;
	}
	
	
	/**
	 * Affiche une fenêtre qui demande le nombre de pions à placer sur le territoire. Si 0, abandon.
	 * @param t
	 */
	public int askNbPion(Territoire t){
		int nbPion;
		int enMain = partieEnCours.getJoueurEnCours().getPeuple().getNbUniteEnMain();
		
		try {
    		do {
    			nbPion = Prompt.askInt("Nombre de pions à replacer sur ce territoire. Pour l'abandonner, choisir 0.");
    			
    		} while(nbPion > (enMain + t.getNbUnite()));
		}
		catch (Exception e) {
			return t.getNbUnite();
		}
		
		return nbPion;
	}
	
	

	public int lancerDe(int bonusDe) {
		showTemp("Lancement du dé...");
		
		int value = (int) Math.round(Math.random() * (3.0 - 1.0)) + 1;
		
		if (bonusDe == 0) {
			showTemp("Résultat: " + value);
		}
		else if (bonusDe > 0) {
			showTemp("Résultat: " + value + " (+ " + bonusDe + ")");
		}
		else {
			showTemp("Résultat: " + value + " (- " + bonusDe + ")");
		}
		
		return value + bonusDe;
	}
	
	
	/* ### Panel d'information ### */

	
	
	/**
	 * 
	 */
	public void showTemp(String message) {
		new WinWait(message, 2000);
	}
	
	
	
	/**
	 * 
	 */
	public void showTemp(String message, int time) {
		new WinWait(message, time);
	}
	
	
	
	
	/**
	 * Mise à jours des informations affichées sur la fenêtre
	 */
	public void majInfos() {
		// Un premier try au cas où on appelerait cette méthode un peu trop tot
		try {
			int i, 
				argent = 0,
				nbTerritoire = 0,
				nbTerritoireDeclin = 0,
				nbUnite = 0,
				nbUniteEnMain = 0;
			
			String name = "Undefined";
			String peuple = "Undefined";
			String pouvoir = "Undefined";
			
			try {
				// Récupération de l'indice du joueur en cours
				i = partieEnCours.getIndexJoueurEnCours();
				
				// Récupération des informations sur le joueur en cours
				Joueur j = partieEnCours.getJoueurEnCours();
				
				name = j.getNom();
				argent = j.getArgent();
				
				// Sur son peuple
				Peuple p = j.getPeuple();
				
				peuple = p.getNom();
				pouvoir = p.getPouvoir().getNom();
				
				if (p != null) {
					nbTerritoire = j.getPeuple().getTerritoiresOccupes().size();
					nbUnite = j.getPeuple().getNbUnite();
					nbUniteEnMain = j.getPeuple().getNbUniteEnMain();
				}
				
				// Sur son peuple en déclin
				p = j.getPeupleDeclin();
				
				if (p != null) {
					nbTerritoireDeclin = p.getTerritoiresOccupes().size();
				}
			}
			catch (Exception e) {
				i = Game.JOUEUR_BACKGROUND.length - 1;
			}
			
			// MAJ
			playerInfo.setText("");
			playerInfo.append("\n");
			playerInfo.append("$................ " + argent + "\n");
			playerInfo.append("Territoires...... " + nbTerritoire + (nbTerritoireDeclin > 0 ? " (+ " + nbTerritoireDeclin + ")" : "") + "\n");
			playerInfo.append("Unités totales... " + nbUnite + "\n");
			playerInfo.append("Unités en main... " + nbUniteEnMain + "\n");
			playerInfo.append("Tour............. " + (partieEnCours.getTourEnCours() + 1) + " / " + partieEnCours.getNbTours() + "\n");
			
			// Mise à jour des couleurs
			headerAction.setBackground(JOUEUR_BACKGROUND[i]);
			headerAction.setForeground(JOUEUR_FOREGROUND[i]);
			
			headerInfo.setBackground(JOUEUR_BACKGROUND[i]);
			headerInfo.setForeground(JOUEUR_FOREGROUND[i]);
			
			headerJoueur.setBackground(JOUEUR_BACKGROUND[i]);
			headerJoueur.setForeground(JOUEUR_FOREGROUND[i]);
			
			String txt = name + " (" + peuple + " : " + pouvoir + ") ";
			headerJoueur.setText(txt);
			
			// Mise à jour des territoires
			Iterator<TerritoireCase> it = territoires.iterator();
			while (it.hasNext()) {
				it.next().majInfos();
			}
			
			// Maj de l'étape
			switch (partieEnCours.getEtape()) {
				case 0:
					headerAction.setText("Conquête");
					
					btnDeclin.setVisible(true);
					btnTour.setVisible(true);
					btnDeploie.setVisible(false);
					
					break;

				case 1:
					headerAction.setText("Conquête");
					
					btnDeclin.setVisible(false);
					btnTour.setVisible(true);
					btnDeploie.setVisible(false);
					
					break;
				
				case 2:
				case 3:
					headerAction.setText("Redéploiement");
					
					btnDeclin.setVisible(false);
					btnTour.setVisible(false);
					btnDeploie.setVisible(true);
					
					break;
			}
			
			// Useless?
			repaint();
		}
		catch (Exception e) {}
	}
	
	
	
	/**
	 * Affiche la zone d'information
	 * @param tx Texte à afficher
	 */
	public void showInfo(String tx) {
		infoTx.setText("\n" + tx);
		
		infoPanel.setVisible(true);
	}
	
	
	/**
	 * Masque la zone d'information
	 */
	public void hideInfo() {
		infoPanel.setVisible(false);
	}
	
	
	
	/**
	 * La partie est finie
	 */
	public static void gameOver() {
		List<Joueur> joueurs = Partie.getInstance().getJoueurs();
		
		Collections.sort(joueurs, new Comparator<Joueur>() {
			public int compare(Joueur o1, Joueur o2) {
				return o2.getArgent() - o1.getArgent();
			}
		});
		
		String res = "Résultats :\n";
		
		for (int i = 0; i < joueurs.size(); i++) {
			Joueur j = joueurs.get(i);
			
			res += (i + 1) + ". " + j.getNom() + " (" + j.getArgent() + " $)\n";
		}
		
		getInstance().showTemp(res, 10000);
		
		if (getInstance().askConf("Voulez-vous rejouer ?")) {
			Partie.setPart(new Partie());
			
			new Game();
		}
		else {
			System.exit(0);
		}
	}
	
	
	
	
	
	/**
	 * @return the instance
	 */
	public static Game getInstance() {
		return instance;
	}

	
	
	/**
	 * Lance le jeu
	 */
	public static void main(String[] args) {
		new Game();
	}





}
