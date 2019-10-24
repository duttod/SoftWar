package com.example.softwar.controllers;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Employe;
import com.example.softwar.data.EmployeDansEntreprise;
import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statistiques extends AppCompatActivity {

    ArrayList<Entreprise> liste_entreprises;
    EntreprisePerso entreprise_joueur;
    int users_total;
    LinearLayout eactif;
    DatabaseClient mdb = DatabaseClient.getInstance(MyApplication.getInstance());
    TextView tnomE,targent,tnbu,tnbe,tpartm,tvict,tnbj,tnbjg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_statistiques);
        getSupportActionBar().hide();
        entreprise_joueur =((MyApplication)this.getApplication()).getEntreprise_joueur();
        liste_entreprises = new ArrayList<>();

        tnomE = findViewById(R.id.snom_entreprise);
        targent = findViewById(R.id.sargent_entreprise);
        tnbu= findViewById(R.id.snb_utilisateurs);
        tnbe= findViewById(R.id.snombre_employes);
        tpartm =  findViewById(R.id.spart_marche);
        tnbj = findViewById(R.id.nbMinijeux);
        tnbjg = findViewById(R.id.nbMinijeuxG);
        tvict = findViewById(R.id.pourcentvictoire);
        setStatistiquesE();
    }

    public void setStatistiquesE() {
        tnomE.setText("Nom : "+entreprise_joueur.getNomEntreprise());
        targent.setText("Capital : "+Long.toString(entreprise_joueur.getArgentEntreprise()));
        tnbu.setText("Utilisateurs : "+Integer.toString(entreprise_joueur.getLogiciel().getNbUtilisateurs()));
        tnbe.setText("Employes : "+Integer.toString(entreprise_joueur.getEmployes().size()));

        tnbj.setText("Mini-Jeux effectués : "+Integer.toString(entreprise_joueur.getNbMiniJeux()));
        tnbjg.setText("Mini-Jeux gagnés : "+Integer.toString(entreprise_joueur.getNbMiniJeuxGagner()));
        int nbjeux= entreprise_joueur.getNbMiniJeux();
        int gagnejeux=entreprise_joueur.getNbMiniJeuxGagner();
        if(gagnejeux!=0){
            double res = (double)gagnejeux/nbjeux;
            tvict.setText("Pourcentage de victoire : "+(double) Math.round(res*100));
        }else{
            tvict.setText("Pourcentage de victoire : 0");
        }


        //part de marché
        for (int i = 0; i < ((MyApplication)this.getApplication()).getConcurrents().size(); i++) {
            liste_entreprises.add(((MyApplication)this.getApplication()).getConcurrents().get(i));
        }

        liste_entreprises.add(((MyApplication)this.getApplication()).getEntreprise_joueur());
        Collections.sort(liste_entreprises);
        for (int i = 0; i < liste_entreprises.size(); i++) {
            users_total += liste_entreprises.get(i).getLogiciel().getNbUtilisateurs();
        }
        tpartm.setText("Parts de marché : "+Double.toString((double)(entreprise_joueur.getLogiciel().getNbUtilisateurs()/(double)users_total)*100)+"%");

        TextView prod = findViewById(R.id.prodtot);
        prod.setText(entreprise_joueur.getStatEmployeActif().get(0).toString());
        TextView rap = findViewById(R.id.raptot);
        rap.setText(entreprise_joueur.getStatEmployeActif().get(1).toString());
    }

}
