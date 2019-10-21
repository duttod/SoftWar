package com.example.softwar.data;

public class Jeu {

	private String nomJeu;
	private int dureeJeu;
	private String description;

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

}