package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity
public class Alea extends Evenement {

	@ColumnInfo(name = "contexte")
	private String contexte;

	@ColumnInfo(name = "type")
	private String type;

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

	public String getType() {
		return this.type;
	}

	/**
	 *
	 * @param contexte
	 */
	public void setType(String type) {
		this.type = type;
	}

}