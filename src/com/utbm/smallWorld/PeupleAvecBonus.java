package com.utbm.smallWorld;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.utbm.smallWorld.interfaces.Bonusable;

/**
 * Représentation d'un Peuple
 * Gestion des territoires, des bonus, du pouvoir spécial, etc.
 * 
 * @author LONGO Michael
 * @version 1.0
 */
public abstract class PeupleAvecBonus extends Peuple {
	/**
	 * @return
	 */
	public int bonusUnite() {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusUniteAttaque() {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusAttaque(Territoire t) {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusDefense(Territoire t, PeupleAvecBonus attaquant) {
		return 0;
	}

	/**
	 * @return
	 */
	public int bonusGain(Territoire t) {
		return 0;
	}

	/**
	 * @return
	 */
	public boolean bonusSansLimite() {
		return false;
	}

	/**
	 * @return
	 */
	public boolean bonusDefausseUnite() {
		return false;
	}
}

