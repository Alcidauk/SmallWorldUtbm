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
		description = "Possédant les clés de l'établissement, ils peuvent attaquer partout, même sur les territoires imprenables";
		nbUniteDepart = 5;
		nbUniteMax = 20;
	}
	
	public double bonusAttaque(Territoire from, Territoire to) {
		return to.coutAttaque(this) == Double.POSITIVE_INFINITY ? Double.POSITIVE_INFINITY : 0;
	}
	
	
	public boolean bonusPeutAttaquer(Territoire from, Territoire to) {
		return true;
	}
}
