package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

public class Pattern {


	private String langage;

	private String text;

	private ArrayList<String> reponses;

	private ArrayList<String> bonnesReponses;

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

	public ArrayList<String> getReponses() {
		return this.reponses;
	}

	/**
	 * 
	 * @param reponses
	 */
	public void setReponses(ArrayList<String> reponses) {
		this.reponses = reponses;
	}

	public ArrayList<String> getBonnesReponses() {
		return this.bonnesReponses;
	}

	/**
	 * 
	 * @param bonnesReponses
	 */
	public void setBonnesReponses(ArrayList<String> bonnesReponses) {
		this.bonnesReponses = bonnesReponses;
	}

	public void addBonneReponse(String reponse){
		this.bonnesReponses.add(reponse);
		this.reponses.add(reponse);
	}
	public void addMauvaiseReponse(String reponse){
		this.reponses.add(reponse);
	}

}