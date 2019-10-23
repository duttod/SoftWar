package com.example.softwar.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Employe {

    @PrimaryKey(autoGenerate = true)
	private	int id;

    @ColumnInfo(name = "nom")
	private String nomEmploye;

    @ColumnInfo(name = "prenom")
	private String prenomEmploye;

    @ColumnInfo(name = "age")
	private int ageEmploye;

    @ColumnInfo(name = "productivite")
	private int productivite;

    @ColumnInfo(name = "rapidite")
	private int rapidite;

    @ColumnInfo(name = "rarete")
	private int rarete;

    public Employe(String nomEmploye,String prenomEmploye,int ageEmploye,int productivite,int rapidite,int rarete){
    	setAgeEmploye(ageEmploye);
    	setNomEmploye(nomEmploye);
    	setPrenomEmploye(prenomEmploye);
    	setProductivite(productivite);
    	setRapidite(rapidite);
    	setRarete(rarete);

	}

	public String getNomEmploye() {
		return this.nomEmploye;
	}


	public int getId(){
        return this.id;
    }


	public void setId(int id){
		this.id = id;
	}
	/**
	 * 
	 * @param nomEmploye
	 */
	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getPrenomEmploye() {
		return this.prenomEmploye;
	}

	/**
	 * 
	 * @param prenomEmploye
	 */
	public void setPrenomEmploye(String prenomEmploye) {
		this.prenomEmploye = prenomEmploye;
	}

	public int getAgeEmploye() {
		return this.ageEmploye;
	}

	/**
	 * 
	 * @param ageEmploye
	 */
	public void setAgeEmploye(int ageEmploye) {
		this.ageEmploye = ageEmploye;
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

	public int getRarete() {
		return this.rarete;
	}

	/**
	 * 
	 * @param rarete
	 */
	public void setRarete(int rarete) {
		this.rarete = rarete;
	}

	public int getRapidite() {
		return this.rapidite;
	}

	/**
	 * 
	 * @param rapidite
	 */
	public void setRapidite(int rapidite) {
		this.rapidite = rapidite;
	}

}