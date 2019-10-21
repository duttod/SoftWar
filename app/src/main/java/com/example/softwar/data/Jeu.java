package com.example.softwar.data;

public class Jeu {

	private string nomJeu;
	private int dureeJeu;
	private string description;

	public string getNomJeu() {
		return this.nomJeu;
	}

	/**
	 * 
	 * @param nomJeu
	 */
	public void setNomJeu(string nomJeu) {
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

	public string getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(string description) {
		this.description = description;
	}

}