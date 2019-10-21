package com.example.softwar.data;

public class Employe {

	private string nomEmploye;
	private string prenomEmploye;
	private int ageEmploye;
	private int productivite;
	private int rapidite;
	private int rarete;

	public string getNomEmploye() {
		return this.nomEmploye;
	}

	/**
	 * 
	 * @param nomEmploye
	 */
	public void setNomEmploye(string nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public string getPrenomEmploye() {
		return this.prenomEmploye;
	}

	/**
	 * 
	 * @param prenomEmploye
	 */
	public void setPrenomEmploye(string prenomEmploye) {
		this.prenomEmploye = prenomEmploye;
	}

	public int getAgeEmploye() {
		return this.ageEmploye;
	}

	/**
	 * 
	 * @param ageEmploye
	 */
	public void setAgeEmploye(int ageEmploye) {
		this.ageEmploye = ageEmploye;
	}

	public int getProductivite() {
		return this.productivite;
	}

	/**
	 * 
	 * @param productivite
	 */
	public void setProductivite(int productivite) {
		this.productivite = productivite;
	}

	public int getRarete() {
		return this.rarete;
	}

	/**
	 * 
	 * @param rarete
	 */
	public void setRarete(int rarete) {
		this.rarete = rarete;
	}

	public int getRapidite() {
		return this.rapidite;
	}

	/**
	 * 
	 * @param rapidite
	 */
	public void setRapidite(int rapidite) {
		this.rapidite = rapidite;
	}

}