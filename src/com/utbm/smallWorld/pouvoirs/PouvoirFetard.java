package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;

public class PouvoirFetard extends Pouvoir {
	
	public PouvoirFetard(){ 
		nbUniteApporte = 5;
		nom = "Fêtard";
		desc = "A chaque tour, ils rapportent +2 unités d'attaque bonus";
	}
	
	@Override
	public int bonusUniteAttaque(){
		return 1;
	}

}
