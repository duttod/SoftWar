package com.example.softwar.data;

public class Pattern {

	private string langage;
	private string text;
	private string[] reponses;
	private string[] bonnesReponses;

	public string getLangage() {
		return this.langage;
	}

	/**
	 * 
	 * @param langage
	 */
	public void setLangage(string langage) {
		this.langage = langage;
	}

	public string getText() {
		return this.text;
	}

	/**
	 * 
	 * @param text
	 */
	public void setText(string text) {
		this.text = text;
	}

	public string[] getReponses() {
		return this.reponses;
	}

	/**
	 * 
	 * @param reponses
	 */
	public void setReponses(string[] reponses) {
		this.reponses = reponses;
	}

	public string[] getBonnesReponses() {
		return this.bonnesReponses;
	}

	/**
	 * 
	 * @param bonnesReponses
	 */
	public void setBonnesReponses(string[] bonnesReponses) {
		this.bonnesReponses = bonnesReponses;
	}

}