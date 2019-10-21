package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;

public class Evenement {

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo(name = "nbUtilisateurs")
	private long nbUtilisateurs;

	@ColumnInfo(name = "argent")
	private long argent;

	@ColumnInfo(name = "productivite")
	private long productivite;

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

	public long getNbUtilisateurs() {
		return this.nbUtilisateurs;
	}

	/**
	 * 
	 * @param nbUtilisateurs
	 */
	public void setNbUtilisateurs(long nbUtilisateurs) {
		this.nbUtilisateurs = nbUtilisateurs;
	}

	public long getArgent() {
		return this.argent;
	}

	/**
	 * 
	 * @param argent
	 */
	public void setArgent(long argent) {
		this.argent = argent;
	}

	public long getProductivite() {
		return this.productivite;
	}

	/**
	 * 
	 * @param productivite
	 */
	public void setProductivite(long productivite) {
		this.productivite = productivite;
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