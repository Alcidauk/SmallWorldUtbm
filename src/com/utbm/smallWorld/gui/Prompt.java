/* Confirm.java */

package com.utbm.smallWorld.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Fenêtre permattant la saisie d'information par l'utilisateur
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public class Prompt extends JDialog implements MouseListener, KeyListener {
	/** Stub */
	private static final long serialVersionUID = 1L;
	/** Largeur par défaut */
	private static final int DEFAULT_WIDTH = 400;
	/** Hauteur par défaut */
	private static final int DEFAULT_HEIGHT = 140;
	
	private static Prompt instance;
	
	/** Response */
	private JTextField input;
	
	private JLabel question;
	private JPanel top;
	private JPanel bottom;
	
	static {
		instance = new Prompt();
	};
	
	/**
	 * Constructeur
	 */
	public Prompt() {
		setModal(true);
		setTitle("SmallWorld UTBM");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setResizable(false);
		
		build();
	}
	
	
	/**
	 * Construction
	 */
	private void build() {
		top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		input = new JTextField(30);
		
		JButton quit = new JButton("OK");
		
		input.addKeyListener(this);
		quit.addMouseListener(this);

		bottom.add(input);
		bottom.add(quit);
		
		question = new JLabel("");
		
		top.add(question);
		top.setPreferredSize(new Dimension(DEFAULT_WIDTH - 10, 30));
		bottom.setPreferredSize(new Dimension(DEFAULT_WIDTH - 10, 80));
		
		getContentPane().add(top);
		getContentPane().add(bottom);
	}
	
	
	/**
	 * Affiche la fenêtre
	 */
	public static String ask(String question) {
		instance.input.setText("");
		instance.question.setText(question);
		
		int width = Math.max(DEFAULT_WIDTH, question.length() * 7 + 40);
		
		instance.top.setPreferredSize(new Dimension(width - 20, 30));
		instance.bottom.setPreferredSize(new Dimension(width - 20, 80));
		instance.setSize(new Dimension(width, DEFAULT_HEIGHT));
		
		instance.setVisible(true);
		
		return instance.input.getText();
	}
	
	/**
	 * Affiche la fenêtre. Cas où l'on attend un int.
	 * @throws Exception si fenêtre fermée
	 */
	public static int askInt(String question) {
		instance.input.setText("");
		instance.question.setText(question);

		int width = Math.max(DEFAULT_WIDTH, question.length() * 7 + 40);
		
		instance.top.setPreferredSize(new Dimension(width - 20, 30));
		instance.bottom.setPreferredSize(new Dimension(width - 20, 80));
		instance.setSize(new Dimension(width, DEFAULT_HEIGHT));
		
		instance.setVisible(true);
		
		return Integer.parseInt(instance.input.getText()); 
	}
	
	/**
	 * Event
	 */
	public void mousePressed(MouseEvent e) {
		setVisible(false);
	}

	/**
	 * Event
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			setVisible(false);
		}
	}
	
	
	/* STUB */
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}