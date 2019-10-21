package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

@Entity
public class Entreprise implements Serializable {

    @PrimaryKey
    @NonNull
	private String nomEntreprise;
	@ColumnInfo(name = "nomLogiciel")
    private String nomLogiciel ;

	public Entreprise(String nomEntreprise,String nomLogiciel){
		this.setNomEntreprise(nomEntreprise);
		this.setNomLogiciel(nomLogiciel);
	}

	public String getNomEntreprise() {
		// TODO - implement Entreprise.getNomEntreprise
		return nomEntreprise;
	}

	/**
	 * 
	 * @param nomEntreprise
	 */
	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getNomLogiciel() {
		return nomLogiciel;
	}

	public void setNomLogiciel(String nomLogiciel) {
		this.nomLogiciel = nomLogiciel;
	}

	public Alea getEvenementAleatoire(DatabaseClient mdb) {
		int indicemin = 0;
		int indicemax = mdb.getAppDatabase().aleadao().getAll().size();

		int indice = (int) (Math.random() * ( indicemax - indicemin ));
		return mdb.getAppDatabase().aleadao().getAll().get(indice);

	}

}