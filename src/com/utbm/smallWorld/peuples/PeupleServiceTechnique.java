/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;

/**
 * @author Administrateur
 *
 */
public class PeupleServiceTechnique extends Peuple {
	public PeupleServiceTechnique() {
		nom = "Service technique";
		description = "";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusAttaque(Territoire from, Territoire to) {
		return to.coutAttaque(this) == Integer.MAX_VALUE ? Integer.MAX_VALUE : 0;
	}
}
