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
	public PeupleProfHumanite() {
		nom = "Professeur d\'humanité";
		description = "";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusDefense(Territoire t, Peuple attaquant) {
		return t.has(SallePartiel.class) ? Integer.MAX_VALUE : 0;
	}
}
