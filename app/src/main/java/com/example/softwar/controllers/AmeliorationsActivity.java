package com.example.softwar.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Logiciel;

public class AmeliorationsActivity extends AppCompatActivity {
    ProgressBar barOpti,barSecu,barErgo,barPuissance;
    TextView tErgo,tOpti,tSecu,tPuissance,monargent;
    EntreprisePerso entreprise_joueur ;
    long argentpossede ;
    Logiciel log ;
    long coutErgo,coutOpti,coutSecu,coutPuissance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ameliorations);
        barErgo = findViewById(R.id.ergoBar);
        barOpti = findViewById(R.id.OfiscalBar);
        barSecu = findViewById(R.id.secuBar);
        barPuissance = findViewById(R.id.puissanceBar);

        tErgo = findViewById(R.id.Cergo);
        tOpti = findViewById(R.id.Copti);
        tSecu = findViewById(R.id.Csecu);
        tPuissance = findViewById(R.id.Cpuissance);
        monargent = findViewById(R.id.argentdispo);

        entreprise_joueur =((MyApplication)this.getApplication()).getEntreprise_joueur();
        argentpossede=entreprise_joueur.getArgentEntreprise();

        log = entreprise_joueur.getLogiciel();
        LoadData();
    }
    public void LoadData(){
        barErgo.setProgress(log.getNiveauErgonomie());
        barOpti.setProgress(log.getNiveauRentabilite());
        barSecu.setProgress(log.getNiveauSecurite());
        barPuissance.setProgress(log.getNiveauPuissance());

        coutErgo = log.getNiveauErgonomie()*888;
        coutOpti = log.getNiveauRentabilite()*888;
        coutSecu = log.getNiveauSecurite()*888;
        coutPuissance = log.getNiveauPuissance()*888;

        argentpossede=entreprise_joueur.getArgentEntreprise();
        monargent.setText(Long.toString(argentpossede));

        tErgo.setText(Long.toString(coutErgo)+"€");
        tOpti.setText(Long.toString(coutOpti)+"€");
        tSecu.setText(Long.toString(coutSecu)+"€");
        tPuissance.setText(Long.toString(coutPuissance)+"€");

    }

    public void upOptiFisc(View view) {
        if(argentpossede>=coutOpti){
            entreprise_joueur.setArgentEntreprise(argentpossede-coutOpti);
            log.upRenta();
            LoadData();
        }else{
            //TODO Afficher Popup PAS ASSEZ D ARGENT
        }

    }

    public void upSecu(View view) {
        if(argentpossede>=coutSecu){
            entreprise_joueur.setArgentEntreprise(argentpossede-coutSecu);
            log.upSecu();
            LoadData();
        }else{
            //TODO Afficher Popup PAS ASSEZ D ARGENT
        }
    }

    public void upErgo(View view) {
        if(argentpossede>=coutErgo){
            entreprise_joueur.setArgentEntreprise(argentpossede-coutErgo);
            log.upErgo();
            LoadData();
        }else{
            //TODO Afficher Popup PAS ASSEZ D ARGENT
        }
    }

    public void upPuissance(View view) {
        if(argentpossede>=coutPuissance){
            entreprise_joueur.setArgentEntreprise(argentpossede-coutPuissance);
            log.upPuissance();
            LoadData();
        }else{
            //TODO Afficher Popup PAS ASSEZ D ARGENT
        }
    }
}
