package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.Nourriture;

public class PouvoirGlouton extends Pouvoir {
	
	public PouvoirGlouton(){
		nom = "glouton";
		desc = "";
		nbUniteApporte = 5;
	}

	/**
	 * 
	 */
	public void actionPriseTerritoire(Territoire t) {
		t.ajouterElement(new Nourriture(t));
	}
	
	

}
