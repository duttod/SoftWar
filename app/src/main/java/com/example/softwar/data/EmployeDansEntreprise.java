package com.example.softwar.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class EmployeDansEntreprise {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name ="nomEntreprise")
    private String nomEntreprise;

    @ColumnInfo(name = "idEmploye")
    private int idEmploye;

    @ColumnInfo(name = "quantite")
    private int quantite;

    @Ignore
    private List<Employe> employes=null;

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public int getQuantite() {
        return quantite;
    }

    public Employe getEmploye(DatabaseClient mdb) {
        Employe emp = mdb.getAppDatabase().employeDao().getAnEmploye(getIdEmploye());

        return emp;
    }

}
