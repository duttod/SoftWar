package com.example.softwar;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
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
    private int numero_tour;
    public MediaPlayer mediaPlayer;
    float volume = 1;
    float speed = 0.05f;

    public void FadeOut(float deltaTime)
    {
        mediaPlayer.setVolume(volume, volume);
        volume -= speed* deltaTime;

    }
    public void FadeIn(float deltaTime)
    {
        mediaPlayer.setVolume(volume, volume);
        volume += speed* deltaTime;

    }

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
        numero_tour = 1;
        mediaPlayer = MediaPlayer.create(this, R.raw.maintheme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
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

    public void addTour() {
        this.numero_tour++;
    }

    public int getTour() {
        return this.numero_tour;
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
