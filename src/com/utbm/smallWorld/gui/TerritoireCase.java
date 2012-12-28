package com.utbm.smallWorld.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.utbm.smallWorld.Element;
import com.utbm.smallWorld.Partie;
import com.utbm.smallWorld.Territoire;

/**
 * Représentation graphique d'un Territoire
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public class TerritoireCase extends JPanel implements MouseListener {
	/** Stub */
	private static final long serialVersionUID = 1L;
	/** Bordure des territoires en temps normal */
	private static Border thinBord;
	/** Bordure des territoires lors du hover */
	private static Border bigBord;
	
	/** Label du nombre d'unité */
	private JLabel tx;
	/** Territoire lié */
	private Territoire territoire;
	
	/** Initialisation des statiques */
	static {
		bigBord = BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED);
		thinBord = new LineBorder(Color.BLACK);
	};
	
	/**
	 * Constructeur
	 * @param pos Position du territoire sur la carte
	 */
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
	 * Met à jour les informations concernant le territoire
	 * Couleur / nombre d'unité
	 */
	public void majInfos() {
		int i;
		int nb;
		
		try {
			i = territoire.getOccupant().getJoueur().getIndice();
		}
		catch (Exception e) {
			i = Game.JOUEUR_BACKGROUND.length - 1;
		}
		
		nb = territoire.getNbUnite();
		
		tx.setBackground(Game.JOUEUR_BACKGROUND[i]);
		tx.setForeground(Game.JOUEUR_FOREGROUND[i]);
		tx.setText(nb + "");
	}

	
	/**
	 * @return the territoire lié
	 */
	public Territoire getTerritoire() {
		return territoire;
	}

	
	/**
	 * @param territoire the territoire lié to set
	 */
	public void setTerritoire(Territoire territoire) {
		this.territoire = territoire;
	}

	
	/**
	 * Action lors du hover: Changement de bordure
	 * @param arg0 useless
	 */
	public void mouseEntered(MouseEvent arg0) {
		setBorder(bigBord);
		
		Rectangle r = this.getBounds();
		
		double coutAttaque =  Partie.getInstance().coutAttaque(territoire);
		List<Element> elements = territoire.getElements();
		
		String txt = "Coût de l'attaque..." + coutAttaque + "\nEléments contenus...";
		
		Iterator<Element> it = elements.iterator();
		while (it.hasNext()) {
			txt += it.next().getNom() + "\n";
		}
		
		Game.getInstance().showInfo(txt);
	}


	/**
	 * Action lors du blur: Changement de bordure
	 * @param arg0 useless
	 */
	public void mouseExited(MouseEvent arg0) {
		setBorder(thinBord);
		
		Game.getInstance().hideInfo();
	}
	
	
	/**
	 * Action lors du clique: Délégation à Partie
	 * @param arg0 useless
	 */
	public void mousePressed(MouseEvent arg0) {
		Partie.getInstance().cliqueTerritoire(this.territoire);
	}
	
	
	/* Stub */
	public void mouseClicked(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
}
