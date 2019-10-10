package com.example.softwar.data;

import com.example.softwar.data.Evenement;

public class ResJeu extends Evenement {

	private int resultat;

	public int getResultat() {
		return this.resultat;
	}

	/**
	 * 
	 * @param resultat
	 */
	public void setResultat(int resultat) {
		this.resultat = resultat;
	}

}