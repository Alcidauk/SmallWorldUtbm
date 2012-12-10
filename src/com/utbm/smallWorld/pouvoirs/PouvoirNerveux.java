package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.MachineACafe;

public class PouvoirNerveux extends Pouvoir {
	
	public PouvoirNerveux(){
		nom = "nerveux";
		desc = "";
		nbUniteApporte = 5;
	}

	/**
	* @return
	*/
	public int bonusDefense(Territoire t, Peuple attaquant) {
		if( t.has(MachineACafe.class) )
			return 2;
		return 0;
	}
	
	

}
