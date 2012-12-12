// TODO a implémenter

package com.utbm.smallWorld.elements;

import com.utbm.smallWorld.Element;
import com.utbm.smallWorld.Peuple;

import com.utbm.smallWorld.Territoire;

public class MachineACafe extends Element {

	public MachineACafe(Territoire t) {
		super(t);
		// TODO Auto-generated constructor stub
		
		nom = "Machine à café";
	}
	
	
	public int bonusDefense(Peuple attaquant){
		return 1;
	}

}
