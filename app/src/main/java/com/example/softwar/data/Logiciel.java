package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.softwar.MyApplication;

import java.util.ArrayList;
import java.util.Random;

@Entity
public class Logiciel {

	public Logiciel(String nomLogiciel) {
		setNomLogiciel(nomLogiciel);
	}
	@Ignore
	private DatabaseClient mdb;
	@PrimaryKey
	@NonNull
	private String nomLogiciel;
	@ColumnInfo(name = "nbUtilisateurs")
	private int nbUtilisateurs;
	@ColumnInfo(name = "niveauSecurite")
	private int niveauSecurite;
	@ColumnInfo(name = "niveauRentabilite")
	private int niveauRentabilite;
	@ColumnInfo(name = "niveauErgonomie")
	private int niveauErgonomie;
	@ColumnInfo(name = "niveauPuissance")
	private int niveauPuissance;

	public void upSecu(){
		mdb=DatabaseClient.getInstance(MyApplication.getInstance());
		Random r = new Random();
		int val = r.nextInt(2)+5;
		this.niveauSecurite+=val;
		mdb.getAppDatabase().logicieldao().update(this);
	}
	public void upErgo(){
		mdb=DatabaseClient.getInstance(MyApplication.getInstance());
		Random r = new Random();
		int val = r.nextInt(2)+5;
		this.niveauErgonomie+=val;
		mdb.getAppDatabase().logicieldao().update(this);
	}
	public void upPuissance(){
		mdb=DatabaseClient.getInstance(MyApplication.getInstance());
		Random r = new Random();
		int val = r.nextInt(2)+5;
		this.niveauPuissance+=val;
		mdb.getAppDatabase().logicieldao().update(this);
	}
	public void upRenta(){
		mdb=DatabaseClient.getInstance(MyApplication.getInstance());
		Random r = new Random();
		int val = r.nextInt(2)+5;
		this.niveauRentabilite+=val;
		mdb.getAppDatabase().logicieldao().update(this);
	}


	public String getNomLogiciel() {
		return this.nomLogiciel;
	}

	/**
	 * 
	 * @param nomLogiciel
	 */
	public void setNomLogiciel(String nomLogiciel) {
		this.nomLogiciel = nomLogiciel;
	}

	public int getNbUtilisateurs() {
		return this.nbUtilisateurs;
	}

	/**
	 * 
	 * @param nbUtilisateurs
	 */
	public void setNbUtilisateurs(int nbUtilisateurs) {
		this.nbUtilisateurs = nbUtilisateurs;
	}

	public int getNiveauSecurite() {
		return this.niveauSecurite;
	}

	/**
	 * 
	 * @param niveauSecurite
	 */
	public void setNiveauSecurite(int niveauSecurite) {
		this.niveauSecurite = niveauSecurite;
	}

	public int getNiveauRentabilite() {
		return this.niveauRentabilite;
	}

	/**
	 * 
	 * @param niveauRentabilite
	 */
	public void setNiveauRentabilite(int niveauRentabilite) {
		this.niveauRentabilite = niveauRentabilite;
	}

	public int getNiveauErgonomie() {
		return this.niveauErgonomie;
	}

	/**
	 * 
	 * @param niveauErgonomie
	 */
	public void setNiveauErgonomie(int niveauErgonomie) {
		this.niveauErgonomie = niveauErgonomie;
	}

	public int getNiveauPuissance() {
		return this.niveauPuissance;
	}

	/**
	 * 
	 * @param niveauPuissance
	 */
	public void setNiveauPuissance(int niveauPuissance) {
		this.niveauPuissance = niveauPuissance;
	}

	public int getNiveauLogiciel() {
		return (int) (getNiveauErgonomie() + getNiveauPuissance() + getNiveauRentabilite() + getNiveauSecurite())/4;
	}

}