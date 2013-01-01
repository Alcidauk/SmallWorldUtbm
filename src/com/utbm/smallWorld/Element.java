package com.utbm.smallWorld;

import java.util.HashMap;
import java.util.Map;

import com.utbm.smallWorld.elements.EspacePleinAir;
import com.utbm.smallWorld.elements.Laboratoire;
import com.utbm.smallWorld.elements.MachineACafe;
import com.utbm.smallWorld.elements.Nourriture;
import com.utbm.smallWorld.elements.Photocopieuse;
import com.utbm.smallWorld.elements.SalleInfo;
import com.utbm.smallWorld.elements.SallePartiel;

/**
 * Représentation des éléments se trouvant sur les territoires
 * 
 * @author UTBM'Student
 * @version 1.0
 */
public abstract class Element implements Comparable<Element> {
	/** Liste des différents éléments de territoire du jeu */
	public static Map<String, Class<?>> ELEMENTS;
	
	/** Territoire sur lequel se trouve l'élément */
	protected Territoire territoire;
	
	/** Nom de l'élément */
	protected String nom;
	
	
	static {
		Class<?>[] classes = { EspacePleinAir.class, Laboratoire.class, MachineACafe.class, Nourriture.class, Photocopieuse.class, SalleInfo.class, SallePartiel.class };
		String[] noms = { "espacepleinair", "laboratoire", "machineacafe", "nourriture", "photocopieuse", "salleinfo", "sallepartiel" };
		
		ELEMENTS = new HashMap<String, Class<?>>();
		
		for (int i = 0; i < classes.length; i++) {
			ELEMENTS.put(noms[i], classes[i]);
		}
	};
	
	
	/**
	 * Constructeur
	 * @param t Territoire associé à l'élément
	 */
	public Element(Territoire t) {
		this.territoire = t;
	}
	
	/**
	 * @param attaquant
	 * @return Bonus de défense offert par l'élément contre l'attaquant
	 * Si retourne Integer.MAX_VALUE, alors inattaquable
	 */
	public double bonusDefense(Peuple attaquant) {
		return 0;
	}
	
	
	/**
	 * @return gain bonus offert par cet élément
	 */
	public int bonusGain() {
		return 0;
	}
	

	/**
	 * @param e Element à comparer à this
	 * @return this - e
	 */
	public int compareTo(Element e) {
		return nom.compareTo(e.nom);
	}
	
	
	/**
	 * @param e Element à comparer à this
	 * @return true si égalité
	 */
	public boolean equals(Element e) {
		return nom.equals(e.nom);
	}
	
	/**
	 * @return Le nom de l'élément
	 */
	public String getNom() {
		return this.nom;
	}
}
