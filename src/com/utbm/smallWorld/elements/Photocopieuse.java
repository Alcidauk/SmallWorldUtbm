// TODO à implémenter

package com.utbm.smallWorld.elements;

import com.utbm.smallWorld.Element;
import com.utbm.smallWorld.Territoire;

public class Photocopieuse extends Element {

	public Photocopieuse(Territoire t) {
		super(t);
		// TODO Auto-generated constructor stub
		
		nom = "Photocopieuse";
	}
	
	public int bonusGain(){
		return 1;
		
	}

}
