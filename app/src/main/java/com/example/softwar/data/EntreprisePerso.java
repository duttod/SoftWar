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

	public void setEntreprisePersoDepart(String nomEntreprise, String nomLogiciel, long argentEntreprise, int nbContrats) {

		setNomEntreprise(nomEntreprise);
		setNomLogiciel(nomLogiciel);
		setArgentEntreprise(argentEntreprise);
		setNbContrats(nbContrats);
		setNbMiniJeuxGagner(0);
		setNbMiniJeux(0);
		setLogiciel(nomLogiciel);

		System.out.println("PASSE CHEZ TA MERE");

		for(int i = 0; i < 3; i++) {
			setEmployeActif(i,-1);
		}

		employes = new ArrayList<EmployeDansEntreprise>();

	}

	public void setEntreprisePersoCharge(DatabaseClient mdb) {

		System.out.println("PASSE CHEZ TON PERE");

		this.mdb = mdb;

		recupNbMiniJeux();
		recupNbMiniJeuxGagner();
		setEmployes();
		setLogiciel();

	}

	private void recupNbMiniJeux() {
		this.nbMiniJeux=mdb.getAppDatabase().entreprisepersodao().getAll().get(0).getNbMiniJeux();
	}

	private void recupNbMiniJeuxGagner() {
		this.nbMiniJeux=mdb.getAppDatabase().entreprisepersodao().getAll().get(0).getNbMiniJeuxGagner();
	}

	@Ignore
	private DatabaseClient mdb = DatabaseClient.getInstance(MyApplication.getInstance());

	@Ignore
	private List<EmployeDansEntreprise> employes;


	@ColumnInfo(name = "nbContrats")
	private int nbContrats;

	@ColumnInfo(name = "nbMiniJeux")
	private int nbMiniJeux = 0;

	@ColumnInfo(name = "nbMiniJeuxGagner")
	private int nbMiniJeuxGagner = 0;

	public void setIdEmployeActif1(int idEmployeActif1) {
		this.idEmployeActif1 = idEmployeActif1;
	}

	public void setIdEmployeActif2(int idEmployeActif2) {
		this.idEmployeActif2 = idEmployeActif2;
	}

	public void setIdEmployeActif3(int idEmployeActif3) {
		this.idEmployeActif3 = idEmployeActif3;
	}

	// id -1 == employé non set !

	@ColumnInfo(name = "employeActif1")
	private int idEmployeActif1;

	@ColumnInfo(name = "employeActif2")
	private int idEmployeActif2;

	@ColumnInfo(name = "employeActif3")
	private int idEmployeActif3;

	public Employe getEmployeById(int id) {
		EmployeDansEntreprise e = mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), id);
		if (e != null) {
			return e.getEmploye(mdb);
		} else {
			return null;
		}

	}

	public void setEmployes() {
		employes = mdb.getAppDatabase().employeDansEntrepriseDao().getEmployeDuneEntreprise(getNomEntreprise());
	}

	public List<EmployeDansEntreprise> getEmployes() {
		return this.employes;
	}

	public int getNbContrats() {
		return this.nbContrats;
	}

	/**
	 * @param nbContrats
	 */
	public void setNbContrats(int nbContrats) {
		this.nbContrats = nbContrats;
		mdb.getAppDatabase().entreprisepersodao().update(this);
	}

	public void setLogiciel() {
		logiciel = mdb.getAppDatabase().logicieldao().getByEntreprise(this.getNomLogiciel());
	}

	public void addEmploye(Employe emp) {
		if (mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId()) != null) {
			EmployeDansEntreprise employedansets = mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId());
			employedansets.setQuantite(employedansets.getQuantite() + 1);

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
		if (mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId()) != null && mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId()).getQuantite() > 1) {
			EmployeDansEntreprise employedansets = mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId());
			employedansets.setQuantite(employedansets.getQuantite() - 1);
		} else if (mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId()) != null && mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId()).getQuantite() == 1) {

			EmployeDansEntreprise employedansets = mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(this.getNomEntreprise(), emp.getId());
			mdb.getAppDatabase().employeDansEntrepriseDao().delete(employedansets);
		}
	}

	public void setEmployeActif(int numEmp, int idEmployeActif) {
		switch (numEmp) {
			case 0:
				setIdEmployeActif1(idEmployeActif);
				break;
			case 1:
				setIdEmployeActif2(idEmployeActif);
				break;
			case 2:
				setIdEmployeActif3(idEmployeActif);
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

	public ArrayList<Integer> getEmployeActif(){

		ArrayList<Integer> emps = new ArrayList<>();

		emps.add(getIdEmployeActif1());
		emps.add(getIdEmployeActif2());
		emps.add(getIdEmployeActif3());

		return emps;
	}

	public ArrayList<Integer> getStatEmployeActif(){
		ArrayList<Integer> statistiquesEmployes = new ArrayList<>();
		int sommeRapidite =0;
		int sommeProductivite =0;
		Employe emp;
		if (getIdEmployeActif1()!=-1){

			emp = mdb.getAppDatabase().employeDao().getAnEmploye(getIdEmployeActif1());
			sommeProductivite = sommeProductivite + emp.getProductivite();
			sommeRapidite = sommeRapidite + emp.getRapidite();

		}if (getIdEmployeActif2()!=-1){

			emp = mdb.getAppDatabase().employeDao().getAnEmploye(getIdEmployeActif2());
			sommeProductivite = sommeProductivite + emp.getProductivite();
			sommeRapidite = sommeRapidite + emp.getRapidite();

		}if (getIdEmployeActif3()!=-1){

			emp = mdb.getAppDatabase().employeDao().getAnEmploye(getIdEmployeActif3());
			sommeProductivite = sommeProductivite + emp.getProductivite();
			sommeRapidite = sommeRapidite + emp.getRapidite();
		}

		statistiquesEmployes.add(sommeProductivite);
		statistiquesEmployes.add(sommeRapidite);
		return statistiquesEmployes;
		/*
		* Faire juste la somme des statistiques !*/
	}

	public int getNbMiniJeux() {
		return nbMiniJeux;
	}

	public void setNbMiniJeux(int nb) {
		this.nbMiniJeux = nb;
		mdb.getAppDatabase().entreprisepersodao().update(this);
	}

	public int getNbMiniJeuxGagner() {
		return nbMiniJeuxGagner;
	}

	public void setNbMiniJeuxGagner(int nbMiniJeuxGagner) {
		this.nbMiniJeuxGagner = nbMiniJeuxGagner;
		mdb.getAppDatabase().entreprisepersodao().update(this);
	}
}