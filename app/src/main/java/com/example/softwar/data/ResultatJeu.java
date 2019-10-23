package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class ResultatJeu extends Evenement {

	@ColumnInfo(name = "resultat")
	private int resultat;

	public void setResultat(int res) {
		this.resultat = res;
	}

	public int getResultat() {
		return this.resultat;
	}

}