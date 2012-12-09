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
	static {
		nom = "Administration";
		nbUniteDepart = 15;
		nbUniteMax = 20;
	}
	
	public int bonusDefense(Territoire t, Peuple attaquant) {
		return (t.has(MachineACafe.class) ? 1 : 0) + (t.has(Photocopieuse.class) ? 1 : 0);
	}
}
