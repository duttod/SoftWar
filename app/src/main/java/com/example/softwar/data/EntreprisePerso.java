package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EntreprisePerso extends Entreprise {

	public EntreprisePerso(String nomEntreprise,String nomLogiciel,long argentEntreprise,int nbContrats ,int productivite){
		super(nomEntreprise,nomLogiciel);
		setArgentEntreprise(argentEntreprise);
		setNbContrats(nbContrats);
		setProductivite(productivite);

		employes = new ArrayList<EmployeDansEntreprise>();
	}

	public EntreprisePerso (DatabaseClient mdb, String nomEntreprise, String nomLogiciel, long argentEntreprise, int nbContrats, int productivite) {

		super(nomEntreprise,nomLogiciel);
		this.mdb = mdb;

		setArgentEntreprise(argentEntreprise);
		setNbContrats(nbContrats);
		setProductivite(productivite);

		setEmployes();
		setLogiciel(this.getNomLogiciel());
	}

	@Ignore
	private DatabaseClient mdb;

	@Ignore Logiciel logiciel;

	@Ignore
	private List<EmployeDansEntreprise> employes;


	@ColumnInfo(name = "nbContrats")
	private int nbContrats;

	@ColumnInfo(name = "productivite")
	private int productivite;

	public void setEmployes() {
		employes =  mdb.getAppDatabase().employeDansEntrepriseDao().getEmployeDuneEntreprise(getNomEntreprise());
	}

	public List<EmployeDansEntreprise> getEmployes() {
		return this.employes;
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

	@Override
	public void setLogiciel(String nom) {
		logiciel = mdb.getAppDatabase().logicieldao().getByEntreprise(nom);
	}

	@Override
	public Logiciel getLogiciel() {
		return this.logiciel;
	}

}