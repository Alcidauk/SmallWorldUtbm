package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.Nourriture;

public class PouvoirGlouton extends Pouvoir {
	
	public PouvoirGlouton(){
		nom = "Glouton";
		desc = "";
		nbUniteApporte = 5;
	}

	/**
	 * 
	 */
	public void actionPriseTerritoire(Territoire t) {
		t.addElement(new Nourriture(t));
	}
	
	

}
