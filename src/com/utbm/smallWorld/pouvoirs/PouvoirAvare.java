package com.utbm.smallWorld.pouvoirs;

import java.util.Iterator;
import java.util.List;

import com.utbm.smallWorld.Partie;
import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.Territoire;

public class PouvoirAvare extends Pouvoir {
	
	public PouvoirAvare(){ 
		nbUniteApporte = 4;
		nom = "Avare";
		desc = "Chaque territoire conquis dans le tour rapport +1$";
	}

	
	/**
	 * @return
	 */
	public int bonusGain(Territoire t) {
		List<Peuple> lstTmp = t.getPrisesDuTerritoire(Partie.getInstance().getTourEnCours());
		
		Iterator<Peuple> it = lstTmp.iterator();
			
		while( it.hasNext() ){
			Peuple p = it.next();
				
			if( p == this.peupleLie){
				return 1;
			}
		}
		
		return 0;
	}
	
	

}

