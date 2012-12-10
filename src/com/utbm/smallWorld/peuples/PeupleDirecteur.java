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
public class PeupleDirecteur extends Peuple {
	public PeupleDirecteur() {
		nom = "Directeur de département";
		description = "";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusGain(Territoire t) {
		return 1;
	}
}
