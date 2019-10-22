package com.example.softwar;

import android.app.Application;
import android.content.Context;

import com.example.softwar.data.EntreprisePerso;

public class MyApplication extends Application {
    private static MyApplication instance;
    private EntreprisePerso entreprise_joueur ;

    public static MyApplication getInstance() {
        return instance;
    }
    public static Context getContext(){
        return instance.getApplicationContext();
        // or return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }


    public EntreprisePerso getEntreprise_joueur() {
        return entreprise_joueur;
    }

    public void setEntreprise_joueur(EntreprisePerso entreprise_joueur) {
        this.entreprise_joueur = entreprise_joueur;
    }
}
