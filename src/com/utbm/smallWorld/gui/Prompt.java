/* Confirm.java */

package com.utbm.smallWorld.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

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
		JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
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
		
		instance.setVisible(true);
		
		return instance.input.getText();
	}
	
	/**
	 * Affiche la fenêtre. Cas où l'on attend un int.
	 */
	public static int askInt(String question) {
		instance.input.setText("");
		instance.question.setText(question);
		
		instance.setVisible(true);
		
		int tmp = 0;
		
		try{
			tmp = Integer.parseInt(instance.input.getText()); 
		}catch( Exception e){
			askInt(question);
		}
		
		return tmp;
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
	
	
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
}