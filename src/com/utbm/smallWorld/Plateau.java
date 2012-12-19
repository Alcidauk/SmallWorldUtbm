package com.utbm.smallWorld;

import java.util.List;

import javax.swing.Icon;

public class Plateau {
	/** Image du plateau */
	private Icon image;
	
	/** Territoires du plateau */
	private List<Territoire> territoires;
	
	/**
	 * Constructeur
	 */
	public Plateau(Icon image) {
		this.image = image;
	}
	
	public Icon getIcon() {
		return image;
	}
	
	public List<Territoire> getTerritoires() {
		return territoires;
	}
}
