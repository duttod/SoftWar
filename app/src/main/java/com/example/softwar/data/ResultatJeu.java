package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class ResultatJeu extends Evenement {

	@ColumnInfo(name = "resultat")
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