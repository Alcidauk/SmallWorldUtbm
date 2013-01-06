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
		description = "Rapporte 1$ de plus par territoire conquis durant le tour";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusGain(Territoire t) {
		// TODO Historique
		return 1;
	}
}
