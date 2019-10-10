package com.example.softwar.data;

public class Pattern {

	private String langage;
	private String text;
	private String[] reponses;

	public String getLangage() {
		return this.langage;
	}

	/**
	 * 
	 * @param langage
	 */
	public void setLangage(String langage) {
		this.langage = langage;
	}

	public String getText() {
		return this.text;
	}

	/**
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}

	public String[] getReponses() {
		return this.reponses;
	}

	/**
	 * 
	 * @param reponses
	 */
	public void setReponses(String[] reponses) {
		this.reponses = reponses;
	}

}