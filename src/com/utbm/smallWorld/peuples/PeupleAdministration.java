/**
 * 
 */
package com.utbm.smallWorld.peuples;

import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;
import com.utbm.smallWorld.elements.MachineACafe;
import com.utbm.smallWorld.elements.Photocopieuse;

/**
 * @author Administrateur
 *
 */
public class PeupleAdministration extends Peuple {
	public PeupleAdministration() {
		nom = "Administration";
		description = "Chaque territoire occupé contenant une photocopieuse ou une machine à café se voit attribuer +1 de défense";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public double bonusDefense(Territoire t, Peuple attaquant) {
		return (t.has(MachineACafe.class) ? 1.0 : 0.0) + (t.has(Photocopieuse.class) ? 1.0 : 0.0);
	}
}
