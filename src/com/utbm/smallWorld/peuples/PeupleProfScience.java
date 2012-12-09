/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;

/**
 * @author Administrateur
 *
 */
public class PeupleProfScience extends Peuple {
	static {
		nom = "Professeur scientifique";
		description = "";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusValeurDe() {
		return 2;
	}
}
