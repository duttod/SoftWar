package com.example.softwar.data;

public class Employe {

	private String nomEmploye;
	private String prenomEmploye;
	private int ageEmploye;
	private int productivite;
	private int efficience;
	private int rarete;

	public String getNomEmploye() {
		return this.nomEmploye;
	}

	/**
	 * 
	 * @param nomEmploye
	 */
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getPrenomEmploye() {
		return this.prenomEmploye;
	}

	/**
	 * 
	 * @param prenomEmploye
	 */
	public void setPrenomEmploye(String prenomEmploye) {
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

	public int getEfficience() {
		return this.efficience;
	}

	/**
	 * 
	 * @param efficience
	 */
	public void setEfficience(int efficience) {
		this.efficience = efficience;
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

}