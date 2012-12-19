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

/**
 * @author Administrateur
 *
 */
public class WinMenu extends JDialog {
	/** Stub */
	private static final long serialVersionUID = 1L;
	/** Panel contenant les items du menu */
	private JPanel panContent;
	/** Choix de l'utilisateur sur le menu */
	private int lastChoice = -1;
	
	
	/**
	 * Constructeur
	 * @param title Titre de la fenêtre / menu
	 */
	public WinMenu(String title) {
		setModal(true);
		setTitle("SmallWorld UTBM - " + title);
		setSize(1280, 768);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
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
		JLabel labMenu = new JLabel(title);
		labMenu.setFont(new Font("Serif", 0, 60));
		labMenu.setForeground(Color.WHITE);
		
		// Conteneur des items du menu
		panContent = new JPanel(new FlowLayout());
		panContent.setPreferredSize(new Dimension(1274, 635));
		panContent.setBackground(new Color(0, 0, 0, 0));
		
		panTitle.add(labMenu);
		background.add(panTitle);
		background.add(panContent);
		setContentPane(background);
	}
	
	
	/**
	 * Ajout d'un élément au menu
	 * @param text Texte de l'option
	 * @param index Numero renvoyé lors du clique
	 */
	public void newItem(String text, int index) {
		panContent.add(menuItem(text, index));
	}

	
	/**
	 * Crée un élément pour le menu
	 * @param text Texte de l'option
	 * @param index Numero renvoyé lors du clique
	 */
	private JLabel menuItem(String title, final int index) {
		final JLabel btn = new JLabel(title);
		
		btn.setHorizontalAlignment(JLabel.CENTER);
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(0, 0, 0, 0));
		btn.setFont(new Font("Serif", 0, 40));
		btn.setPreferredSize(new Dimension(700, 60));
		btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		final WinMenu that = this;
		
		btn.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {
				cliqueMenu(index);
			}
			
			public void mouseEntered(MouseEvent arg0) {
				btn.setOpaque(true);
				btn.setBackground(new Color(0, 0, 0, 140));
				that.repaint();
			}
			
			public void mouseExited(MouseEvent arg0) {
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
