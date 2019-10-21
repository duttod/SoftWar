package com.example.softwar.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Entreprise.class, EntreprisePerso.class, Employe.class, Alea.class, ResultatJeu.class,Jeu.class, Pattern.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //Déclaration DAO's
    public abstract EntrepriseDAO entreprisedao();
    public abstract EntreprisePersoDAO entreprisepersodao();
    public abstract EmployeDao employeDao();
    public abstract AleaDAO aleadao();
    public abstract ResultatJeuDAO resultatjeudao();
    public abstract JeuDao jeuDao();
    public abstract PatternDao patternDao();

}