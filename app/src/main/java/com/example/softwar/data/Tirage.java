package com.example.softwar.data;

import java.util.List;
import java.util.Random;

public class Tirage {

    private List<Employe> tirageEmploye;
    private List<Employe> employeTire;
    private DatabaseClient mdb;
    private Random random;

    public Tirage(DatabaseClient mdb){
        this.mdb = mdb;
        this.random = new Random();
        List<Employe> allEmploye = mdb.getAppDatabase().employeDao().getAll();
        for (int i = 0; i < allEmploye.size() ; i++)
        {
            switch (allEmploye.get(i).getRarete()) {
                case 1:
                    for (int j= 0; j <7;j++){
                        tirageEmploye.add(allEmploye.get(i));
                    }
                    break;
                case 2:
                    for (int j= 0; j <3;j++){
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
        employeTire.add(tirageEmploye.get(random.nextInt(tirageEmploye.size())));
    }

    public List<Employe> Tirages(int nbTirage){
        employeTire.clear();
        for (int i = 0; i < nbTirage; i++){
            tirageUnique();
        }
        return employeTire;
    }
}
