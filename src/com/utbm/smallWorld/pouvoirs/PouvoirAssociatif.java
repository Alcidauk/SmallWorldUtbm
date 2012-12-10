package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirAssociatif extends Pouvoir {

	/**
	 * @return
	 */
	public int bonusGain(Territoire t) {
		return 1;
	}
	
}
