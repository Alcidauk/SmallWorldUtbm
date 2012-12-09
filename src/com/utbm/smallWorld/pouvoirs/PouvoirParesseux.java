package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirParesseux extends Pouvoir {
	
	static{ nbUniteApporte = 5; }

	public PouvoirParesseux(int nbUnite, boolean actifDeclin) {
		super(nbUnite, actifDeclin);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int bonusDefense(Territoire t, Peuple attaquant){
		return 1;
	}

}
