package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirParesseux extends Pouvoir {
	
	public PouvoirParesseux(){ 
		nbUniteApporte = 5; 
		nom = "Paresseux";
		desc = "";
	}
	
	@Override
	public double bonusDefense(Territoire t, Peuple attaquant){
		return 1.0;
	}

}
