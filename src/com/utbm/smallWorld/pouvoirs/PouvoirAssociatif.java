package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirAssociatif extends Pouvoir {

	public PouvoirAssociatif(){
		nom = "Associatif";
		desc = "Tels une secte, ils gagnent +1 unité par tour";
		nbUniteApporte = 5;		
	}
	
	/**
	 * @return
	 */
	public int bonusGainUnite(Territoire t) {
		return 1;
	}
	
}
