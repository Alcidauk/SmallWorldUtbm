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
		description = "Chaque territoire occupé contenant un laboratoire se voit attribuer +1 de défense";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public double bonusDefense(Territoire t, Peuple attaquant) {
		return t.has(Laboratoire.class) ? 2.0 : 0.0;
	}
}
