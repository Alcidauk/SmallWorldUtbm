package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;

public class PouvoirOpportuniste extends Pouvoir {
	
	public PouvoirOpportuniste(){
		nom = "Opportuniste";
		desc = "";
		nbUniteApporte = -5;
	}
	
	/**
	 * @return
	 */
	public boolean bonusLanceDe() {
		return true;
	}

}
