/**
 * 
 */
package com.utbm.smallWorld.peuples;

import java.util.List;

import com.utbm.smallWorld.Partie;
import com.utbm.smallWorld.Peuple;
import com.utbm.smallWorld.Territoire;

/**
 * @author Administrateur
 *
 */
public class PeupleAlternance extends Peuple {
	public PeupleAlternance() {
		nom = "Alternance";
		description = "Gagne une unité par paire de territoire non-vide conquis durant le tour";
		nbUniteDepart = 6;
		nbUniteMax = 18;
	}
	
	public int bonusUnite() {
		int count = 0;
		List<Peuple> lstTmp;
		
		for (Territoire t : this.territoiresOccupes) {
			boolean vide = true;

    		for (int i = 0; i < Partie.getInstance().getTourEnCours(); i++) {
        		lstTmp = t.getPrisesDuTerritoire(i);

        		if (lstTmp.size() > 0) {
        			if (! lstTmp.contains(this) || lstTmp.size() > 1) {
        				vide = false;
        			}
        		}
    		}
    		
    		if (! vide) {
    			lstTmp = t.getPrisesDuTerritoire(Partie.getInstance().getTourEnCours());
    			
    			if (lstTmp.contains(this)) {
    				count++;
    			}
    		}
		}
		
		return count / 2;
	}
}
