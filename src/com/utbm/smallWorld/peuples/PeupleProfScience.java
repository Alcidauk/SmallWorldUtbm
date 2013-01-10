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
	public PeupleProfScience() {
		nom = "Professeur scientifique";
		description = "Leurs connaissances leurs ont permis de trafiquer le dé, bonus de valeur +2 à chaque lancé de dé";
		nbUniteDepart = 5;
		nbUniteMax = 20;
	}
	
	public int bonusValeurDe() {
		return 2;
	}
}
