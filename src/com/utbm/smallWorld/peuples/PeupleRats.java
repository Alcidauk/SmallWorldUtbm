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
	public PeupleRats() {
		nom = "Rat";
		description = "Fidèles à leur réputation, ils gagnent +1 unité par tour";
		nbUniteDepart = 15;
		nbUniteMax = 25;
	}
	
	public int bonusUnite() {
		return 1;
	}
}
