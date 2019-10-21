package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;

@Entity
public class EntreprisePerso extends Entreprise {

	public void EntreprisePerso(DatabaseClient mdb, long argentEntreprise, int nbContrats, int productivite) {
		this.mdb = mdb;

		setArgentEntreprise(argentEntreprise);
		setNbContrats(nbContrats);
		setProductivite(productivite);
		setEmployes();
	}

	@Ignore
	private DatabaseClient mdb;

	@Ignore
	private HashMap<Employe,Integer> employes;

	@ColumnInfo(name = "argentEntreprise")
	private long argentEntreprise;

	@ColumnInfo(name = "nbContrats")
	private int nbContrats;

	@ColumnInfo(name = "productivite")
	private int productivite;

	public long getArgentEntreprise() {
		return this.argentEntreprise;
	}

	public void setEmployes() {
		List<EmployeDansEntreprise> empdansentreprise;
		empdansentreprise =  mdb.getAppDatabase().employeDansEntrepriseDao().getEmployeDuneEntreprise(getNomEntreprise());

		for (int i = 0; i < empdansentreprise.size(); i++) {
			
		}
	}

	public List<Employe> getEmployes() {
		return this.employes;
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