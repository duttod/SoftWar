package com.example.softwar.data;

import android.app.Application;

import com.example.softwar.MyApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tirage {
/*
*
* Amélioration possible : récupérer la liste des employé dans une list, tirageEmploye devient une list contenant seulement les id
* des employés.
* Le tirage ce fais seulement sur des id et il faut donc récupérer le bon employé dans la list exprimé ci-dessus
* moins lourd en mémoire mais peut-être un peu plus long en terme d'utilisation.
*
* */

    private List<Employe> tirageEmploye;
    private List<Employe> employeTire;
    private DatabaseClient mdb ;
    private Random random;
    EntreprisePerso entreprise_joueur;

    public Tirage(){
        this.mdb = DatabaseClient.getInstance(MyApplication.getContext());
        this.random = new Random();
        employeTire = new ArrayList<>();
        tirageEmploye = new ArrayList<>();
        ArrayList<Employe> allEmploye = new ArrayList<>();
        allEmploye.addAll(mdb.getAppDatabase().employeDao().getAll());
        for (int i = 0; i < allEmploye.size() ; i++)
        {
            switch (allEmploye.get(i).getRarete()) {
                case 1:
                    for (int j= 0; j <3s;j++){
                        tirageEmploye.add(allEmploye.get(i));
                    }
                    break;
                case 2:
                    for (int j= 0; j <2;j++){
                        tirageEmploye.add(allEmploye.get(i));
                    }
                    break;
                case 3:

                    tirageEmploye.add(allEmploye.get(i));
                    break;

            }

        }


    }

    public void tirageUnique(){

        getEmployeTire().add(tirageEmploye.get(random.nextInt(tirageEmploye.size())));
    }

    public List<Employe> Tirages(int nbTirage){

        getEmployeTire().clear();

        for (int i = 0; i < nbTirage; i++){
            tirageUnique();
        }
        entreprise_joueur = MyApplication.getInstance().getEntreprise_joueur();
        entreprise_joueur.setNbContrats(entreprise_joueur.getNbContrats()-nbTirage);

        return getEmployeTire();
    }

    public List<Employe> getEmployeTire() {
        return employeTire;
    }

    public void setEmployeTire(List<Employe> employeTire) {
        this.employeTire = employeTire;
    }
}
