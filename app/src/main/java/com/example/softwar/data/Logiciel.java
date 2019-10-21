package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Logiciel {
	@PrimaryKey
	@NonNull
	private String nomLogiciel;
	@ColumnInfo(name = "nbUtilisateurs")
	private int nbUtilisateurs;
	@ColumnInfo(name = "niveauSecurite")
	private int niveauSecurite;
	@ColumnInfo(name = "niveauRentabilite")
	private int niveauRentabilite;
	@ColumnInfo(name = "niveauErgonomie")
	private int niveauErgonomie;
	@ColumnInfo(name = "niveauPuissance")
	private int niveauPuissance;

	public String getNomLogiciel() {
		return this.nomLogiciel;
	}

	/**
	 * 
	 * @param nomLogiciel
	 */
	public void setNomLogiciel(String nomLogiciel) {
		this.nomLogiciel = nomLogiciel;
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

	public int getNiveauSecurite() {
		return this.niveauSecurite;
	}

	/**
	 * 
	 * @param niveauSecurite
	 */
	public void setNiveauSecurite(int niveauSecurite) {
		this.niveauSecurite = niveauSecurite;
	}

	public int getNiveauRentabilite() {
		return this.niveauRentabilite;
	}

	/**
	 * 
	 * @param niveauRentabilite
	 */
	public void setNiveauRentabilite(int niveauRentabilite) {
		this.niveauRentabilite = niveauRentabilite;
	}

	public int getNiveauErgonomie() {
		return this.niveauErgonomie;
	}

	/**
	 * 
	 * @param niveauErgonomie
	 */
	public void setNiveauErgonomie(int niveauErgonomie) {
		this.niveauErgonomie = niveauErgonomie;
	}

	public int getNiveauPuissance() {
		return this.niveauPuissance;
	}

	/**
	 * 
	 * @param niveauPuissance
	 */
	public void setNiveauPuissance(int niveauPuissance) {
		this.niveauPuissance = niveauPuissance;
	}

}