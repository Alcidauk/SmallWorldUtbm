package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirParesseux extends Pouvoir {
	
	public PouvoirParesseux(){ 
		nbUniteApporte = 2; 
		nom = "Paresseux";
		desc = "Il est tellement difficile de les faire se déplacer qu'ils gagnent +1 de bonus défense partout";
	}
	
	@Override
	public double bonusDefense(Territoire t, Peuple attaquant){
		return 1.0;
	}

}
