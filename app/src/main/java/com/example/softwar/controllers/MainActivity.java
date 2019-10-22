package com.example.softwar.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Logiciel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EntreprisePerso entreprise_joueur ;
    TextView argent,nbuser,nomE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        argent = (TextView) findViewById(R.id.argent);
        nbuser = (TextView) findViewById(R.id.nbUtilisateurs);
        nomE = (TextView) findViewById(R.id.nomE);

        //Récupérer la variable globale Application
        //!!!!!!!!!!!
        entreprise_joueur = ((MyApplication) this.getApplication()).getEntreprise_joueur();


       LoadDataEntreprise();
    }




    public void LoadDataEntreprise() {

        argent.setText(Long.toString(entreprise_joueur.getArgentEntreprise()));
        nomE.setText(entreprise_joueur.getNomEntreprise());
        nbuser.setText(Integer.toString(entreprise_joueur.getLogiciel().getNbUtilisateurs()));

    }


}
