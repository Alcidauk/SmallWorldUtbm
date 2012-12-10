/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.Laboratoire;

/**
 * @author Administrateur
 *
 */
public class PeupleChercheur extends Peuple {
	public PeupleChercheur() {
		nom = "Chercheur";
		description = "";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusDefense(Territoire t, Peuple attaquant) {
		return t.has(Laboratoire.class) ? 2 : 0;
	}
}
