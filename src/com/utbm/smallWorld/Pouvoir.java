package com.utbm.smallWorld;

import java.util.List;

import com.utbm.smallWorld.interfaces.Bonusable;

public class Pouvoir implements Bonusable {

	@Override
	public int bonusGain(Territoire t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bonusDefense(Territoire t, Peuple attaquant) {
		// TODO Auto-generated method stub
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
