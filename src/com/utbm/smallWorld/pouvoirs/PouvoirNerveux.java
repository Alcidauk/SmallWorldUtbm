package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.MachineACafe;

public class PouvoirNerveux extends Pouvoir {
	
	public PouvoirNerveux(){
		nom = "Nerveux";
		desc = "Chaque territoire occupé contenant une machine café se voit attribuer +2 de bonus défense";
		nbUniteApporte = 4;
	}

	/**
	* @return
	*/
	public double bonusDefense(Territoire t, Peuple attaquant) {
		if( t.has(MachineACafe.class) )
			return 2.0;
		return 0.0;
	}
	
	

}
