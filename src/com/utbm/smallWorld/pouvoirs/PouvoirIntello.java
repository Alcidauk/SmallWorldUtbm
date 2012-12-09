package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.SallePartiel;

public class PouvoirIntello extends Pouvoir {
	
	static{ nbUniteApporte = 5; }
	
	@Override
	public int bonusDefense(Territoire t, Peuple attaquant) {
		if( t.has(SallePartiel.class) )
			return 1;
		return 0;
	}

	@Override
	public int bonusAttaque(Territoire from, Territoire to) {
		if( to.has(SallePartiel.class) )
			return 2;
		return 0;
	}

}
