package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirFauxCul extends Pouvoir {
	
	public PouvoirFauxCul(){
		nom = "Faux-cul";
		desc = "";
		nbUniteApporte = 5;
	}

	/**
	 * 
	 */
	public boolean bonusPeutAttaquer(Territoire from, Territoire to){
		if( to.isEstEnBordure() )
			return true;
		return false;
	}
	
	

}
