package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class Entreprise implements Serializable {

    @PrimaryKey
    @NonNull
	private String nomEntreprise;

	public String getNomEntreprise() {
		// TODO - implement Entreprise.getNomEntreprise
		return nomEntreprise;
	}

	/**
	 * 
	 * @param nomEntreprise
	 */
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

}