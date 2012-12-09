/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;

/**
 * @author Administrateur
 *
 */
public class PeupleRats extends Peuple {
	static {
		nom = "Rat";
		description = "";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusUnite() {
		return 1;
	}
}
