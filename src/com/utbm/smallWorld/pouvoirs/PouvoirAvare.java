package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirAvare extends Pouvoir {
	
	static{ nbUniteApporte = 5; }

	public PouvoirAvare(int nbUnite, boolean actifDeclin) {
		super(nbUnite, actifDeclin);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return
	 */
	public int bonusGain(Territoire t) {
		//if( this.peupleLie)
		return 0;
	}
	
	

}
