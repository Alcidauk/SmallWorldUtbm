// TODO à implémenter

package com.utbm.smallWorld.elements;

import com.utbm.smallWorld.Element;
import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.peuples.PeupleEtudiantBranche;
import com.utbm.smallWorld.peuples.PeupleEtudiantTC;

public class SallePartiel extends Element {

	public SallePartiel(Territoire t) {
		super(t);
		// TODO Auto-generated constructor stub
		
		nom = "Salle partiel";
	}
	
	
	/*
	 *  si le peuple qui attaque un territoire possédant une salle de partiel
	 *  est un étudiant de Branche ou de Tronc commun, alors le territoire  
	 *  est inattaquable
	 */
	public int bonusDefense(Peuple attaquant){
		if ((attaquant instanceof PeupleEtudiantTC) || (attaquant instanceof PeupleEtudiantBranche)){
			return Integer.MAX_VALUE;
		}
		return 1;
	}
	

}
