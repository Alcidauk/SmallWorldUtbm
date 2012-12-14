package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirVoyageur extends Pouvoir {
	
	public PouvoirVoyageur(){
		nom = "Voyageur";
		desc = "";
		nbUniteApporte = 5;
	}

	/**
	 * 
	 */
	public boolean bonusPeutAttaquer(Territoire from, Territoire to){
		return true;
	}
	
	

}
