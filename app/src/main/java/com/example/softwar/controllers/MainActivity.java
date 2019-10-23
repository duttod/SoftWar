package com.example.softwar.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Jeu;
import com.example.softwar.data.Logiciel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EntreprisePerso entreprise_joueur;
    TextView argent, nbuser, nomE;
    private DatabaseClient mdb;
    ArrayList<Entreprise> concurrents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        argent = (TextView) findViewById(R.id.argent);
        nbuser = (TextView) findViewById(R.id.nbUtilisateurs);
        nomE = (TextView) findViewById(R.id.nomE);

        mdb = DatabaseClient.getInstance(getApplicationContext());
        concurrents = new ArrayList<>();


        //Récupérer la variable globale Application
        //!!!!!!!!!!!
        entreprise_joueur =((MyApplication)this.getApplication()).getEntreprise_joueur();
        CreerRandomConcurrents();
        setImageLogiciel();
        LoadDataEntreprise();

        ((MyApplication)this.getApplication()).setConcurrents(concurrents);
    }

    public void LoadDataEntreprise() {

        argent.setText(Long.toString(entreprise_joueur.getArgentEntreprise()));
        nomE.setText(entreprise_joueur.getNomEntreprise());
        nbuser.setText(Integer.toString(entreprise_joueur.getLogiciel().getNbUtilisateurs()));
        argent.setText("Argent:"+Long.toString(entreprise_joueur.getArgentEntreprise()));
        nomE.setText("Entreprise:"+entreprise_joueur.getNomEntreprise());
        nbuser.setText("Utilisateurs:"+Integer.toString(entreprise_joueur.getLogiciel().getNbUtilisateurs()));

    }

    public void CreerRandomConcurrents() {

        for (int i = 0; i < 5; i++) {
            Entreprise ets_conc = new Entreprise("Concurrent"+i,"Soft"+i);

            int argent_depart = (int) (Math.random() * (2000 - 500));
            int nb_utilisateurs_depart = (int) (Math.random() * (50000 - 1500));

            ets_conc.setArgentEntreprise(argent_depart);
            ets_conc.getLogiciel().setNbUtilisateurs(nb_utilisateurs_depart);

            concurrents.add(ets_conc);
        }
    }

    public void setImageLogiciel() {

        switch(entreprise_joueur.getLogiciel().getNiveauLogiciel()) {
            case 1 : ////
                break;
            case 2 : ////
                break;
            case 3 : ////
                break;
            case 4 : ////
                break;
            case 5 : ////
                break;
        }

    }

    public void GoToInvocation(View view) {
        Intent intent = new Intent(MyApplication.getInstance(),TirageAuSortActivity.class);
        startActivity(intent);

    }

    public void go_concurrents(View view) {
        Intent intent = new Intent(this, ConcurrentsActivity.class);
        startActivity(intent);
    }

    public void go_employes(View view) {
        Intent intent = new Intent(this,ChoisirEmployeActifActivity.class);
        startActivity(intent);
    }
}
