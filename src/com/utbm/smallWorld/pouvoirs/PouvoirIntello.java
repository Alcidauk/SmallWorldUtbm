package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.SallePartiel;

public class PouvoirIntello extends Pouvoir {
	
	public PouvoirIntello(){ 
		nbUniteApporte = 5;
		nom = "Intello";
		desc = "";
	}
	
	@Override
	public double bonusDefense(Territoire t, Peuple attaquant) {
		if( t.has(SallePartiel.class) )
			return 1.0;
		return 0.0;
	}

	@Override
	public double bonusAttaque(Territoire from, Territoire to) {
		if( to.has(SallePartiel.class) )
			return 2.0;
		return 0.0;
	}

}
