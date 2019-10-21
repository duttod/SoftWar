package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Jeu {

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo(name = "nom")
	private String nomJeu;

	@ColumnInfo(name = "dureeJeu")
	private int dureeJeu;

	@ColumnInfo(name = "description")
	private String description;


	public int getId() { return this.id;}

	public String getNomJeu() {
		return this.nomJeu;
	}

	/**
	 * 
	 * @param nomJeu
	 */
	public void setNomJeu(String nomJeu) {
		this.nomJeu = nomJeu;
	}

	public int getDureeJeu() {
		return this.dureeJeu;
	}

	/**
	 * 
	 * @param dureeJeu
	 */
	public void setDureeJeu(int dureeJeu) {
		this.dureeJeu = dureeJeu;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}
}