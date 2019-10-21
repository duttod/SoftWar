package com.example.softwar.data;

public class Logiciel {

	private string nomLogiciel;
	private int nbUtilisateurs;
	private int niveauSecurite;
	private int niveauRentabilite;
	private int niveauErgonomie;
	private int niveauPuissance;

	public string getNomLogiciel() {
		return this.nomLogiciel;
	}

	/**
	 * 
	 * @param nomLogiciel
	 */
	public void setNomLogiciel(string nomLogiciel) {
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