package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.utbm.smallWorld.Territoire;

public class TerritoireCase extends JPanel implements MouseListener {
	/** Stub */
	private static final long serialVersionUID = 1L;
	private static Border thinBord;
	private static Border bigBord;
	private JLabel tx;
	private Territoire territoire;
	
	static {
		bigBord = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED);
		thinBord = new LineBorder(Color.BLACK);
	};
	
	public TerritoireCase(Rectangle pos) {
		this.setBounds(pos);
		this.setPreferredSize(new Dimension(pos.width, pos.height));
		this.setBorder(thinBord);
		this.setOpaque(false);
		this.addMouseListener(this);
		this.setLayout(new GridBagLayout());
		
		tx = new JLabel("0");
		tx.setBackground(Color.GRAY);
		tx.setForeground(Color.WHITE);
		tx.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		tx.setBorder(new LineBorder(Color.BLACK));
		tx.setPreferredSize(new Dimension(30, 30));
		tx.setHorizontalAlignment(JLabel.CENTER);
		tx.setVerticalAlignment(JLabel.CENTER);
		tx.setOpaque(true);
		
		this.add(tx, new GridBagConstraints());
	}

	/**
	 * @return the territoire
	 */
	public Territoire getTerritoire() {
		return territoire;
	}

	/**
	 * @param territoire the territoire to set
	 */
	public void setTerritoire(Territoire territoire) {
		this.territoire = territoire;
	}
	
	public void majInfos() {
		int i;
		int nb;
		
		try {
			i = territoire.getOccupant().getJoueur().getIndice();
			nb = territoire.getNbUnite();
		}
		catch (Exception e) {
			i = Game.JOUEUR_BACKGROUND.length - 1;
			nb = 0;
		}
		
		tx.setBackground(Game.JOUEUR_BACKGROUND[i]);
		tx.setForeground(Game.JOUEUR_FOREGROUND[i]);
		tx.setText(nb + "");
	}

	public void mouseEntered(MouseEvent arg0) {
		setBorder(bigBord);
		
		Rectangle r = this.getBounds();
		
		Game.getInstance().showInfo("Coord: (" + r.x + ", " + r.y + ")\nSize: (" + r.width + ", " + r.height + ")");
	}
	
	public void mouseExited(MouseEvent arg0) {
		setBorder(thinBord);
		
		Game.getInstance().hideInfo();
	}
	
	public void mouseClicked(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
