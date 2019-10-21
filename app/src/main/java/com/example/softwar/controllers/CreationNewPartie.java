package com.example.softwar.controllers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Logiciel;

import java.util.ArrayList;
import java.util.List;

public class CreationNewPartie extends AppCompatActivity {
    EditText tnomL,tnomE;
    private DatabaseClient mDb;
    ArrayList<Logiciel> arraylog = new ArrayList();
    ArrayList<Entreprise> arrayEnt = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_new_partie);
        mDb = DatabaseClient.getInstance(getApplicationContext());
    }

    public void demarrerPartie(View view) {
        tnomE = (EditText) findViewById(R.id.edit_nom_entreprise);
        tnomL = (EditText) findViewById(R.id.edit_nom_logiciel);
        getLogiciels();

        if(nomEValide(tnomE.getText().toString()) && nomLValide(tnomE.getText().toString()) ){
            creerLogiciel(tnomL.getText().toString());
            creerEntreprisePerso(tnomE.getText().toString(),tnomL.getText().toString());
        }
    }

    private boolean nomEValide(String nomE) {
        int i = 0;
        if(!nomE.isEmpty()){
            while(i<arrayEnt.size() && arrayEnt.get(i).getNomEntreprise() != nomE ){
                i++;
            }
            return i==arrayEnt.size()-1;
        }else{
            return false;
        }

    }

    private boolean nomLValide(String nomL) {
        int i = 0;
        if(!nomL.isEmpty()){
            while(i<arrayEnt.size() && arrayEnt.get(i).getNomEntreprise() != nomL ){
                i++;
            }
            return i==arrayEnt.size()-1;
        }else{
            return false;
        }
    }

    private void getLogiciels() {
        ///////////////////////
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class getLogiciels extends AsyncTask<Void, Void, List<Logiciel>> {

            @Override
            protected List<Logiciel> doInBackground(Void... voids) {
                List<Logiciel> LogicielList = mDb.getAppDatabase()
                        .logicieldao()
                        .getAll();
                return LogicielList;
            }

            @Override
            protected void onPostExecute(List<Logiciel> mylist) {
                super.onPostExecute(mylist);

                // Mettre à jour l'adapter avec la liste de taches
                arraylog.clear();
                arraylog.addAll(mylist);

            }
        }

        getLogiciels gt = new getLogiciels();
        gt.execute();
    }
    private void getEntreprises() {

        class getEntreprises extends AsyncTask<Void, Void, List<Entreprise>> {

            @Override
            protected List<Entreprise> doInBackground(Void... voids) {
                List<Entreprise> EntrepriseList = mDb.getAppDatabase()
                        .entreprisedao().getAll();
                return EntrepriseList;
            }

            @Override
            protected void onPostExecute(List<Entreprise> mylist) {
                super.onPostExecute(mylist);

                // Mettre à jour l'adapter avec la liste de taches
                arrayEnt.clear();
                arrayEnt.addAll(mylist);

            }
        }

        getEntreprises gt = new getEntreprises();
        gt.execute();
    }

    private void creerEntreprisePerso(final String nomE, final String nomL) {

        class creerEntreprisePerso extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                EntreprisePerso e = new EntreprisePerso(nomE,nomL,1000 , 1 , 0);
                mDb.getAppDatabase().entreprisedao().insert(e);

                return null;
            }

        }

        creerEntreprisePerso gt = new creerEntreprisePerso();
        gt.execute();
    }

    private void creerLogiciel(final String nomL) {

        class creerLogiciel extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Logiciel l = new Logiciel(nomL);
                mDb.getAppDatabase().logicieldao().insert(l);

                return null;
            }

        }

        creerLogiciel gt = new creerLogiciel();
        gt.execute();
    }



}
