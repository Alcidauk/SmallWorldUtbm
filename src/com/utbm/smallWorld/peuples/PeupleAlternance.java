/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;

/**
 * @author Administrateur
 *
 */
public class PeupleAlternance extends Peuple {
	public PeupleAlternance() {
		nom = "Alternance";
		description = "";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusUnite() {
		// TODO: Historique Territoire
		
		return 0;
	}
}
