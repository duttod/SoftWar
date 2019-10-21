package com.example.softwar.data;

public class EntreprisePerso extends Entreprise {

	private long argentEntreprise;
	private int nbContrats;
	private int productivite;

	public long getArgentEntreprise() {
		return this.argentEntreprise;
	}

	/**
	 * 
	 * @param argentEntreprise
	 */
	public void setArgentEntreprise(long argentEntreprise) {
		this.argentEntreprise = argentEntreprise;
	}

	public int getNbContrats() {
		return this.nbContrats;
	}

	/**
	 * 
	 * @param nbContrats
	 */
	public void setNbContrats(int nbContrats) {
		this.nbContrats = nbContrats;
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

}