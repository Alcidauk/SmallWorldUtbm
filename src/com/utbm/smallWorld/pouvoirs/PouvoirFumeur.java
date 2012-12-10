package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.EspacePleinAir;

public class PouvoirFumeur extends Pouvoir {
	
	/**
	 * @return
	 */
	public int bonusGain(Territoire t) {
		if( t.has(EspacePleinAir.class) )
			return 2;
		return 0;
	}

}
