package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;

public class PouvoirBagarreur extends Pouvoir {
	
	public PouvoirBagarreur(){
		nom = "Bagarreur";
		desc = "Toujours prêts à se battre, ils rapportent +1 unité d'attaque bonus par tour";
		nbUniteApporte = 5;
	}
	
	/**
	 * @return
	 */
	public int bonusUniteAttaque() {
		return 1;
	}

}
