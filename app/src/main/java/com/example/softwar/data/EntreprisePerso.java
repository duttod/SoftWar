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

		setNomLogiciel(nomLogiciel);
		setNomEntreprise(nomEntreprise);
		setArgentEntreprise(argentEntreprise);
		setNbContrats(nbContrats);
		setProductivite(productivite);
		setLogiciel(nomLogiciel);

		employes = new ArrayList<EmployeDansEntreprise>();
	}

	public EntreprisePerso (DatabaseClient mdb, String nomEntreprise, String nomLogiciel, long argentEntreprise, int nbContrats, int productivite) {

		super(nomEntreprise,nomLogiciel);
		this.mdb = mdb;

		setArgentEntreprise(argentEntreprise);
		setNbContrats(nbContrats);
		setProductivite(productivite);
		setNomLogiciel(nomLogiciel);

		setEmployes();
		setLogiciel();
	}

	@Ignore
	private DatabaseClient mdb =DatabaseClient.getInstance(MyApplication.getInstance());

	@Ignore
	private List<EmployeDansEntreprise> employes;


	@ColumnInfo(name = "nbContrats")
	private int nbContrats;

	@ColumnInfo(name = "productivite")
	private int productivite;

	public void setIdEmployeActif1(int idEmployeActif1) {
		this.idEmployeActif1 = idEmployeActif1;
	}

	public void setIdEmployeActif2(int idEmployeActif2) {
		this.idEmployeActif2 = idEmployeActif2;
	}

	public void setIdEmployeActif3(int idEmployeActif3) {
		this.idEmployeActif3 = idEmployeActif3;
	}

	@ColumnInfo(name = "employeActif1")
	private int idEmployeActif1;

	@ColumnInfo(name = "employeActif2")
	private int idEmployeActif2;

	@ColumnInfo(name = "employeActif3")
	private int idEmployeActif3;

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

	public void setEmployeActif(int numEmp, int idEmployeActif) {
		switch (numEmp) {
			case 0:
				this.idEmployeActif1 = idEmployeActif;
				break;
			case 1:
				this.idEmployeActif2 = idEmployeActif;
				break;
			case 2:
				this.idEmployeActif3 = idEmployeActif;
				break;
		}
	}


	public int getIdEmployeActif2() {
		return idEmployeActif2;
	}

	public int getIdEmployeActif3() {
		return idEmployeActif3;
	}

	public int getIdEmployeActif1() {
		return idEmployeActif1;
	}

}