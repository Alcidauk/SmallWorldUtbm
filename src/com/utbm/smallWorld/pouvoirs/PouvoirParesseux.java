package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirParesseux extends Pouvoir {
	
	public PouvoirParesseux(){ 
		nbUniteApporte = 5; 
		nom = "paresseux";
		desc = "";
	}
	
	@Override
	public int bonusDefense(Territoire t, Peuple attaquant){
		return 1;
	}

}
