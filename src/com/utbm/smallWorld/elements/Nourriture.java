// TODO à implémenter 

package com.utbm.smallWorld.elements;

import com.utbm.smallWorld.Element;
import com.utbm.smallWorld.Peuple;

import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.peuples.PeupleRats;

public class Nourriture extends Element {

	public Nourriture(Territoire t) {
		super(t);
		// TODO Auto-generated constructor stub
		
		nom = "Nourriture";
	}
	
	
	/*
	 *  Si le peuple attaquant le territoire possédant de la nourriture est un Rat alors,
	 *  aucun bonus de défense. Sinon, bonus de défense pour tous les autres peuples
	 */
	public int bonusDefense(Peuple attaquant){
		if(attaquant instanceof PeupleRats){
			return 0;
		}
		return 1;
		
	}

}
