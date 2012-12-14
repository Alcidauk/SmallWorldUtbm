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
	
	public double bonusAttaque(Territoire from, Territoire to) {
		return to.coutAttaque(this) == Double.POSITIVE_INFINITY ? Double.POSITIVE_INFINITY : 0;
	}
}
