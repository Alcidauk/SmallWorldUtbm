package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirFetard extends Pouvoir {
	
	static{ nbUniteApporte = 5; }
	
	@Override
	public int bonusAttaque(Territoire from, Territoire to){
		return 2;
	}

}
