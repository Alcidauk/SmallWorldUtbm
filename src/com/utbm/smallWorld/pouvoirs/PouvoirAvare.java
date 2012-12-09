package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirAvare extends Pouvoir {
	
	static{ nbUniteApporte = 5; }

	
	/**
	 * @return
	 */
	public int bonusGain(Territoire t) {
		//if( this.peupleLie)
		return 0;
	}
	
	

}
