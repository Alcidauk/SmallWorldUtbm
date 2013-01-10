package com.utbm.smallWorld.pouvoirs;

import com.utbm.smallWorld.Partie;
import com.utbm.smallWorld.Pouvoir;
import com.utbm.smallWorld.gui.Game;

public class PouvoirOpportuniste extends Pouvoir {
	/** Dernier tour auquel le pouvoir a été utilisé */
	private int dernierTour = -1;
	
	public PouvoirOpportuniste(){
		nom = "Opportuniste";
		desc = "Toujours à l'affut de la bonne occasion, ils peuvent lancer le dé une fois de plus à chaque tour";
		nbUniteApporte = 2;
	}
	
	/**
	 * @return
	 */
	public boolean bonusLanceDe(double cout) {
		if (cout > 1 && this.dernierTour != Partie.getInstance().getTourEnCours()) {
			if (Game.getInstance().askConf("Voulez-vous lancer le dé ? (Pouvoir)")) {
				this.dernierTour = Partie.getInstance().getTourEnCours();
				
				return true;
			}
		}
		
		return false;
	}

}
