package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Pattern {

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo
	private String langage;

	@ColumnInfo
	private String text;

	@ColumnInfo
	private String[] reponses;

	@ColumnInfo
	private String[] bonnesReponses;

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

	public String[] getBonnesReponses() {
		return this.bonnesReponses;
	}

	/**
	 * 
	 * @param bonnesReponses
	 */
	public void setBonnesReponses(String[] bonnesReponses) {
		this.bonnesReponses = bonnesReponses;
	}

}