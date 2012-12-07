package com.utbm.smallWorld.interfaces;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;

public interface Bonusable {
	public int bonusGain(Territoire t);

	public int bonusDefense(Territoire t, Peuple attaquant);

	public int bonusAttaque(Territoire t);

	public boolean bonusSansLimite();

	public boolean bonusDefausseUnite();
}
