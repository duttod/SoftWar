package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.softwar.MyApplication;

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
		setLogiciel();

		employes = new ArrayList<EmployeDansEntreprise>();
	}

	public EntreprisePerso (DatabaseClient mdb, String nomEntreprise, String nomLogiciel, long argentEntreprise, int nbContrats, int productivite) {

		super(nomEntreprise,nomLogiciel);
		this.mdb = mdb;

		setArgentEntreprise(argentEntreprise);
		setNbContrats(nbContrats);
		setProductivite(productivite);

		setEmployes();
		setLogiciel();
	}

	@Ignore
	private DatabaseClient mdb = DatabaseClient.getInstance(MyApplication.getContext());

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



	public void setLogiciel() {
		logiciel = mdb.getAppDatabase().logicieldao().getByEntreprise(this.getNomLogiciel());
	}

	public void addEmploye(Employe emp) {
		if (mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId()) != null) {
			EmployeDansEntreprise employedansets = mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId());
			employedansets.setQuantite(employedansets.getQuantite()+1);

			mdb.getAppDatabase().employeDansEntrepriseDao().update(employedansets);
		} else {
			EmployeDansEntreprise employedansets = new EmployeDansEntreprise();
			employedansets.setIdEmploye(emp.getId());
			employedansets.setNomEntreprise(this.getNomEntreprise());
			employedansets.setQuantite(1);

			mdb.getAppDatabase().employeDansEntrepriseDao().insert(employedansets);
		}
	}

	public void removeEmploye(Employe emp) {
		if (mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId()) != null && mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId()).getQuantite() > 1) {
			EmployeDansEntreprise employedansets = mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId());
			employedansets.setQuantite(employedansets.getQuantite()-1);
		} else if (mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId()) != null && mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId()).getQuantite() == 1){

			EmployeDansEntreprise employedansets = mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(),emp.getId());
			mdb.getAppDatabase().employeDansEntrepriseDao().delete(employedansets);
		}
	}

}