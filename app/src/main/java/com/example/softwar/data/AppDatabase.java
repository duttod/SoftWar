package com.example.softwar.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Entreprise.class, EntreprisePerso.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    //Déclaration DAO's
    public abstract EntrepriseDAO entreprisedao();

}