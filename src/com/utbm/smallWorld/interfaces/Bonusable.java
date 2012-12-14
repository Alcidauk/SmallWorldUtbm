package com.utbm.smallWorld.interfaces;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;

public interface Bonusable {
	public int bonusGain(Territoire t);

	public double bonusDefense(Territoire t, Peuple attaquant);

	public double bonusAttaque(Territoire from, Territoire to);
	
	public int bonusUnite();
	
	public int bonusUniteAttaque();

	public int bonusValeurDe();

	public boolean bonusLanceDe();

	public boolean bonusPeutAttaquer(Territoire from, Territoire to);

	public boolean bonusDefausseUnite();
	
	public void actionPriseTerritoire(Territoire t);
}
