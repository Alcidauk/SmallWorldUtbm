package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirFetard extends Pouvoir {

	public PouvoirFetard(int nbUnite, boolean actifDeclin) {
		super(nbUnite, actifDeclin);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int bonusAttaque(Territoire t){
		return 2;
	}

}
