package com.example.softwar;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;

import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    private EntreprisePerso entreprise_joueur ;
    private Entreprise entreprise_attaquer;
    private int compteur_action =2;
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

    public Entreprise getEntreprise_attaquer() {
        return entreprise_attaquer;
    }

    public void setEntreprise_attaquer(Entreprise entreprise_attaquer) {
        this.entreprise_attaquer = entreprise_attaquer;
    }

    public int getCompteur_action() {
        return compteur_action;
    }

    public void setCompteur_action(int compteur_action) {
        this.compteur_action = compteur_action;
    }

    public void decrementCompteur(){
        this.compteur_action = this.compteur_action -1;
    }
}
