package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

@Entity
public class Entreprise implements Serializable, Comparable<Entreprise> {

    @PrimaryKey
    @NonNull
	private String nomEntreprise;

	@Ignore
	Logiciel logiciel;

    @ColumnInfo(name = "nomLogiciel")
    private String nomLogiciel ;

	@ColumnInfo(name = "argentEntreprise")
	private long argentEntreprise;

	@ColumnInfo(name = "nbusers")
	private int nbusers;



	public Entreprise() {}

	public Entreprise(String nomEntreprise,String nomLogiciel){

		this.setNomEntreprise(nomEntreprise);
		this.setNomLogiciel(nomLogiciel);
		this.setLogiciel(nomLogiciel);

	}

	public String getNomEntreprise() {
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

	public void setArgentEntreprise(long argentEntreprise) {
		this.argentEntreprise = argentEntreprise;
	}

	public long getArgentEntreprise() {
		return this.argentEntreprise;
	}

	public Alea getEvenementAleatoire(DatabaseClient mdb) {
		int indicemin = 0;
		int indicemax = mdb.getAppDatabase().aleadao().getAll().size();

		int indice = (int) (Math.random() * ( indicemax - indicemin ));
		return mdb.getAppDatabase().aleadao().getAll().get(indice);

	}

	public void setLogiciel(String nom) {
		this.logiciel = new Logiciel(nom);
	}

	public Logiciel getLogiciel() {
		return this.logiciel;
	}

	public int getNbusers() {
		return this.nbusers;
	}

	public void setNbusers(int nb) {
		this.nbusers = nb;
	}

    @Override
    public int compareTo(@NonNull Entreprise entreprise) {
        return ((Integer) getLogiciel().getNbUtilisateurs()).compareTo(entreprise.getLogiciel().getNbUtilisateurs());
    }
}