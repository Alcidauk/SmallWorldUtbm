package com.utbm.smallWorld;

import com.utbm.smallWorld.interfaces.Bonusable;

public class Bonus implements Bonusable {
	public int bonusGain(Territoire t) {
		return 0;
	}

	public int bonusDefense(Territoire t, Peuple attaquant) {
		return 0;
	}

	public int bonusAttaque(Territoire t) {
		return 0;
	}

	public boolean bonusSansLimite() {
		return false;
	}

	public boolean bonusDefausseUnite() {
		return false;
	}
}
