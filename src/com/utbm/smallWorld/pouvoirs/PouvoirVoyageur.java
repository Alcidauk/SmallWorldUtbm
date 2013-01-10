package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirVoyageur extends Pouvoir {
	
	public PouvoirVoyageur(){
		nom = "Voyageur";
		desc = "Ayant explosé leurs crédits SMILE, ils peuvent à présent attaquer n'importe quel territoire";
		nbUniteApporte = 2;
	}

	/**
	 * 
	 */
	public boolean bonusPeutAttaquer(Territoire from, Territoire to){
		return true;
	}
	
	

}
