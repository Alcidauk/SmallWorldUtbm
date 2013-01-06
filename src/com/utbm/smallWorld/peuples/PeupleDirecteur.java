/**
 * 
 */
package com.utbm.smallWorld.peuples;

import java.util.Iterator;
import java.util.List;

import com.utbm.smallWorld.Partie;
import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;

/**
 * @author Administrateur
 *
 */
public class PeupleDirecteur extends Peuple {
	public PeupleDirecteur() {
		nom = "Directeur de département";
		description = "Rapporte 1$ de plus par territoire conquis durant le tour";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusGain(Territoire t) {
		List<Peuple> lstTmp = t.getPrisesDuTerritoire(Partie.getInstance().getTourEnCours());
		
		Iterator<Peuple> it = lstTmp.iterator();
			
		while( it.hasNext() ){
			Peuple p = it.next();
				
			if( p == this){
				return 1;
			}
		}
		
		return 0;
	}
}
