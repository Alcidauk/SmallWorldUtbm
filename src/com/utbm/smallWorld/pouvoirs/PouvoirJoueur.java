package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Pouvoir;

public class PouvoirJoueur extends Pouvoir {
	
	public PouvoirJoueur(){
		nom = "Joueur";
		desc = "Ils possèdent un talent né pour les jeux de hasard, ils arrivent même à faire +2 sur chaque valeur du dé";
		nbUniteApporte = 5;
	}
	
	/**
	 * @return
	 */
	public int bonusValeurDe() {
		return 2;
	}

}
