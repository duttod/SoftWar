package com.example.softwar.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.Entreprise;

import java.util.ArrayList;
import java.util.Collections;

public class ChooseEntrepriseToAttack extends AppCompatActivity {
    TextView nom_entreprise, nb_users_entreprise, argent_entreprise, partdemarche_entreprise, nom_logiciel, argent_t, nb_users_t;
    int users_total;
    int argent_total;
    Entreprise econcurente;
    ArrayList<Entreprise> liste_entreprises;
    LinearLayout linclassement;
    Button attack ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_entreprise_to_attack);
        getSupportActionBar().hide();

        nom_entreprise = findViewById(R.id.nom_entreprise);
        nb_users_entreprise = findViewById(R.id.nb_users_entreprise);
        argent_entreprise = findViewById(R.id.argent_entreprise);
        partdemarche_entreprise = findViewById(R.id.partdemarche_entreprise);
        nom_logiciel = findViewById(R.id.nom_logiciel);

        argent_t = findViewById(R.id.argent_total);
        nb_users_t = findViewById(R.id.nb_users_total);
        attack = findViewById(R.id.attackbutton);


        liste_entreprises = new ArrayList<>();

        linclassement = findViewById(R.id.classement);

        //Liste de toutes les entreprises
        for (int i = 0; i < ((MyApplication)this.getApplication()).getConcurrents().size(); i++) {
            liste_entreprises.add(((MyApplication)this.getApplication()).getConcurrents().get(i));
        }

        liste_entreprises.add(((MyApplication)this.getApplication()).getEntreprise_joueur());
        Collections.sort(liste_entreprises);

        for (int i = 0; i < liste_entreprises.size(); i++) {
            argent_total += liste_entreprises.get(i).getArgentEntreprise();
            users_total += liste_entreprises.get(i).getLogiciel().getNbUtilisateurs();
        }

        setClassement();
    }

    public void setClassement() {

        for (int i = 1; i < liste_entreprises.size(); i++) {

            TextView tx = new TextView(this);

            final String nomets = liste_entreprises.get(i).getNomEntreprise();
            final Long argentreprise = liste_entreprises.get(i).getArgentEntreprise();
            final int nbusers = liste_entreprises.get(i).getLogiciel().getNbUtilisateurs();
            final String nomlogiciel = liste_entreprises.get(i).getLogiciel().getNomLogiciel();
            econcurente = liste_entreprises.get(i);

            tx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    nom_entreprise.setText("Nom de l'entreprise : "+nomets);
                    nom_logiciel.setText("Nom du logiciel : "+nomlogiciel);
                    argent_entreprise.setText("Capital : "+argentreprise);
                    nb_users_entreprise.setText("Nombre d'utilisateurs du logiciel : "+nbusers);
                    partdemarche_entreprise.setText("Parts de marché : "+((nbusers/users_total)*100)+"%");
                    attack.setEnabled(true);

                }
            });

            tx.setText(i+". "+nomets);
            tx.setTextSize(16);
            tx.setTypeface(ResourcesCompat.getFont(this, R.font.nasalization));
            tx.setTextColor(Color.parseColor("#FFB900"));

            linclassement.addView(tx);
        }


    }

    public void LaunchAttack(View view) {
        ((MyApplication)this.getApplication()).setEntreprise_attaquer(econcurente);

        Intent intent = new Intent(this,MiniJeu.class);
        intent.putExtra(ChooseRenforcerAttaquerActivity.ACTION_KEY,"attaquer");
        startActivity(intent);

    }
}


