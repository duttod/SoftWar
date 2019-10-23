package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

public class Evenement {

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo(name = "nbUtilisateurs")
	private int nbUtilisateurs;

	@ColumnInfo(name = "argent")
	private int argent;

	@ColumnInfo(name = "rentabilite")
	private int rentabilite;

	@ColumnInfo(name = "securite")
	private int securite;

	@ColumnInfo(name = "puissance")
	private int puissance;

	@ColumnInfo(name = "ergonomie")
	private int ergonomie;

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public int getNbUtilisateurs() {
		return this.nbUtilisateurs;
	}

	/**
	 * 
	 * @param nbUtilisateurs
	 */
	public void setNbUtilisateurs(int nbUtilisateurs) {
		this.nbUtilisateurs = nbUtilisateurs;
	}

	public int getArgent() {
		return this.argent;
	}

	/**
	 * 
	 * @param argent
	 */
	public void setArgent(int argent) {
		this.argent = argent;
	}

	public int getRentabilite() {
		return this.rentabilite;
	}

	/**
	 * 
	 * @param rentabilite
	 */
	public void setRentabilite(int rentabilite) {
		this.rentabilite = rentabilite;
	}

	public int getSecurite() {
		return this.securite;
	}

	/**
	 * 
	 * @param securite
	 */
	public void setSecurite(int securite) {
		this.securite = securite;
	}

	public int getPuissance() {
		return this.puissance;
	}

	/**
	 * 
	 * @param puissance
	 */
	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public int getErgonomie() {
		return this.ergonomie;
	}

	/**
	 * 
	 * @param ergonomie
	 */
	public void setErgonomie(int ergonomie) {
		this.ergonomie = ergonomie;
	}

}