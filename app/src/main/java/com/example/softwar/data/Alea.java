package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class Alea extends Evenement {

	@ColumnInfo(name = "contexte")
	private String contexte;

	public String getContexte() {
		return this.contexte;
	}

	/**
	 * 
	 * @param contexte
	 */
	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

}