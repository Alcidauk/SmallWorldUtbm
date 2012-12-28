package com.utbm.smallWorld;

import java.util.List;

import javax.swing.Icon;

/**
 * Représentation d'un plateau
 * Contient les territoires, et la sprite de la map
 * 
 * @author UTBM'Student
 * @version 1.0
 */
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
	
	/**
	 * @return the icon
	 */
	public Icon getIcon() {
		return image;
	}
	
	
	/**
	 * @return the territoires
	 */
	public List<Territoire> getTerritoires() {
		return territoires;
	}
}
