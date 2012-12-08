package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirGeek extends Pouvoir {

	public PouvoirGeek(int nbUnite, boolean actifDeclin) {
		super(nbUnite, actifDeclin);
		// TODO Auto-generated constructor stub
	}


	@Override
	public int bonusDefense(Territoire t, Peuple attaquant) {
		if( t.hasSalleInfo() ){
			return 1;
		}
		return 0;
	}

	@Override
	public int bonusAttaque(Territoire t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean bonusSansLimite() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bonusDefausseUnite() {
		// TODO Auto-generated method stub
		return false;
	}

}
