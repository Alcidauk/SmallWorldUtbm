package com.utbm.smallWorld;

public abstract class Element {
	protected Territoire territoire;
	
	public Element(Territoire t) {
		this.territoire = t;
	}
	
	/**
	 * Si retourne Integer.MAX_VALUE, alors inattaquable
	 */
	public int bonusDefense(Peuple attaquant) {
		return 0;
	}
	
	public int bonusGain() {
		return 0;
	}
}
