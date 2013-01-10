package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.SalleInfo;

public class PouvoirGeek extends Pouvoir {
	
	public PouvoirGeek(){ 
		nbUniteApporte = 5;
		nom = "Geek";
		desc = "Chaque territoire occupé étant une salle informatique se voit attribuer +1 en défense. L'attaque vers ces territoires se voit attribuer +2 de bonus";
	}

	@Override
	public double bonusDefense(Territoire t, Peuple attaquant){
		if( t.has(SalleInfo.class) )
			return 1.0;
		return 0.0;
	}

	@Override
	public double bonusAttaque(Territoire from, Territoire to) {
		if( to.has(SalleInfo.class) )
			return 2.0;
		return 0.0;
	}

}
