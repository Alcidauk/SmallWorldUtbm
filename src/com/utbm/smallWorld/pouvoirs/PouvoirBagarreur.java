package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;

public class PouvoirBagarreur extends Pouvoir {
	
	public PouvoirBagarreur(){
		nom = "bagarreur";
		desc = "";
		nbUniteApporte = 5;
	}
	
	/**
	 * @return
	 */
	public int bonusUniteAttaque() {
		return 1;
	}

}
