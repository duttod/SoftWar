package com.example.softwar.data;

public class Evenement {

	private long nbUtilisateurs;
	private long argent;
	private long productivite;
	private int securite;
	private int puissance;
	private int ergonomie;

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