package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;

public class PouvoirJoueur extends Pouvoir {
	
	public PouvoirJoueur(){
		nom = "joueur";
		desc = "";
		nbUniteApporte = 5;
	}
	
	/**
	 * @return
	 */
	public int bonusValeurDe() {
		return 2;
	}

}
