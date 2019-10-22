package com.example.softwar;

import android.app.Application;
import android.content.Context;

import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    private EntreprisePerso entreprise_joueur ;
    private ArrayList<Entreprise> concurrents;

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

    public ArrayList<Entreprise> getConcurrents() {
        return concurrents;
    }

    public void setConcurrents(ArrayList<Entreprise> concurrents) {
        this.concurrents = concurrents;
    }
}
