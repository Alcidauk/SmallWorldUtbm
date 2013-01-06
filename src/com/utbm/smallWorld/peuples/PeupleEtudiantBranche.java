/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;

/**
 * @author Administrateur
 *
 */
public class PeupleEtudiantBranche extends Peuple {
	public PeupleEtudiantBranche() {
		nom = "Etudiant branche";
		description = "Lors de la perte d'un territoire, ils ne défaussent pas d'unité";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public boolean bonusDefausseUnite() {
		return true;
	}
}
