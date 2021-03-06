/**
 * 
 */
package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Fenêtre permettant d'offrir un choix à l'utilisateur
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public class WinMenu extends JDialog {
	/** Stub */
	private static final long serialVersionUID = 1L;
	
	/** Panel contenant les items du menu */
	private JPanel panContent;
	
	/** Choix de l'utilisateur sur le menu */
	private int lastChoice = -1;
	
	/** Header de la fenêtre */
	private JLabel labMenu;

	private JTextArea txInfo;

	private JPanel panBottom;
	
	
	/**
	 * Constructeur
	 * @param title Titre de la fenêtre / menu
	 */
	public WinMenu(String title) {
		super(Game.getInstance(), true);
		setTitle("SmallWorld UTBM - " + title);
		setSize(1280, 768);
		setLocationRelativeTo(Game.getInstance());
		setResizable(false);
		//setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		build(title);
	}
	
	
	/**
	 * Construction des éléments de la fenêtre
	 * @param title Titre du menu
	 */
	private void build(String title) {
		// Fond d'écran
		JLabel background = new JLabel();
		background.setIcon(Game.BACKGROUND);
		FlowLayout layBack = new FlowLayout();
		layBack.setVgap(0);
		background.setLayout(layBack);

		// Background du titre du haut
		JPanel panTitle = new JPanel(new FlowLayout());
		panTitle.setPreferredSize(new Dimension(1274, 105));
		panTitle.setBackground(new Color(0, 0, 0, 140));
		
		// Titre du haut
		labMenu = new JLabel(title);
		labMenu.setFont(new Font("Serif", 0, 60));
		labMenu.setForeground(Color.WHITE);
		
		// Conteneur des items du menu
		panContent = new JPanel(new FlowLayout());
		panContent.setPreferredSize(new Dimension(1274, 435));
		panContent.setBackground(new Color(0, 0, 0, 0));
		
		//

		// Background du panel du bas
		panBottom = new JPanel(new FlowLayout());
		panBottom.setPreferredSize(new Dimension(1274, 200));
		panBottom.setBackground(new Color(14, 14, 14));
		panBottom.setVisible(false);
		
		// Contenu du bas
		txInfo = new JTextArea();
		txInfo.setPreferredSize(new Dimension(1200, 190));
		txInfo.setBackground(new Color(14, 14, 14));
		txInfo.setForeground(Color.WHITE);
		txInfo.setFont(new Font("sherif", 0, 24));
		txInfo.setEditable(false);
		txInfo.setLineWrap(true);
		
		panTitle.add(labMenu);
		panBottom.add(txInfo);
		background.add(panTitle);
		background.add(panContent);
		background.add(panBottom);
		getContentPane().add(background);
	}
	
	
	/**
	 * @param title The title to set
	 */
	public void setHeadTitle(String title) {
		this.labMenu.setText(title);
	}
	
	
	/**
	 * Ajout d'un élément au menu
	 * @param text Texte de l'option
	 * @param index Numero renvoyé lors du clique
	 * @return 
	 */
	public JLabel newItem(String text, int index) {
		JLabel it = menuItem(text, null, index);
		panContent.add(it);
		
		return it;
	}
	
	
	/**
	 * Ajout d'un élément au menu
	 * @param text Texte de l'option
	 * @param index Numero renvoyé lors du clique
	 * @return 
	 */
	public JLabel newItem(String text, String desc, int index) {
		JLabel it = menuItem(text, desc, index);
		panContent.add(it);
		
		return it;
	}

	
	/**
	 * Crée un élément pour le menu
	 * @param text Texte de l'option
	 * @param index Numero renvoyé lors du clique
	 */
	private JLabel menuItem(String title, final String desc, final int index) {
		final JLabel btn = new JLabel(title);
		
		btn.setHorizontalAlignment(JLabel.CENTER);
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(0, 0, 0, 0));
		btn.setFont(new Font("Serif", 0, 40));
		btn.setPreferredSize(new Dimension(1000, 60));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		final WinMenu that = this;
		
		btn.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {
				cliqueMenu(index);
			}
			
			public void mouseEntered(MouseEvent arg0) {
				if (desc != null && desc.length() > 0) {
					txInfo.setText("\n" + desc);
					panBottom.setVisible(true);
				}
				
				btn.setOpaque(true);
				btn.setBackground(new Color(0, 0, 0, 140));
				that.repaint();
			}
			
			public void mouseExited(MouseEvent arg0) {
				if (desc != null && desc.length() > 0) {
					panBottom.setVisible(false);
				}
				
				btn.setBackground(new Color(0, 0, 0, 0));
				that.repaint();
			}
		});
		
		return btn;
	}
	
	
	/**
	 * Intercepte les cliques sur le menu
	 * @param index Id de l'item
	 */
	protected void cliqueMenu(int index) {
		this.lastChoice = index;
		
		setVisible(false);
	}
	
	
	/**
	 * Supprime tous les éléments
	 */
	public void clear() {
		panContent.removeAll();
		repaint();
	}
	
	/**
	 * Ferme la fenetre
	 */
	public void close() {
		setVisible(false);
	}

	/**
	 * Ouvre la fenetre
	 */
	public int open() {
		this.lastChoice = -1;
		
		setVisible(true);
		
		return this.lastChoice;
	}
}
