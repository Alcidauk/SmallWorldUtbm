package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirFetard extends Pouvoir {
	
	public PouvoirFetard(){ 
		nbUniteApporte = 5;
		nom = "Fêtard";
		desc = "A chaque tour, ils rapportent +2 unités d'attaque bonus";
	}
	
	@Override
	public double bonusAttaque(Territoire from, Territoire to){
		return 2.0;
	}

}
