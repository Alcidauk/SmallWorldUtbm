/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.SallePartiel;

/**
 * @author Administrateur
 *
 */
public class PeupleProfHumanite extends Peuple {
	static {
		nom = "Professeur d\'humanité";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusDefense(Territoire t, Peuple attaquant) {
		return t.has(SallePartiel.class) ? Integer.MAX_VALUE : 0;
	}
}
