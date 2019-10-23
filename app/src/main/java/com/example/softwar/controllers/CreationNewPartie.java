package com.example.softwar.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softwar.MyApplication;
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
    ArrayList<Logiciel> liste_logiciels = new ArrayList();
    ArrayList<EntreprisePerso> liste_entreprises = new ArrayList();
    EntreprisePerso eperso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_creation_new_partie);
        getSupportActionBar().hide();
        mDb = DatabaseClient.getInstance(getApplicationContext());

    }

    public void demarrerPartie(View view) {

        tnomL = (EditText) findViewById(R.id.edit_nom_logiciel);
        tnomE = (EditText) findViewById(R.id.edit_nom_entreprise);

        tnomE.getBackground().mutate().setColorFilter(Color.parseColor("#FFB900"), PorterDuff.Mode.SRC_ATOP);
        tnomL.getBackground().mutate().setColorFilter(Color.parseColor("#FFB900"), PorterDuff.Mode.SRC_ATOP);

        getLogiciels();
        getEntreprises();

        if(mDb.getAppDatabase().entreprisepersodao().getAll().size() == 0){
            if(nomEValide(tnomE.getText().toString()) && nomLValide(tnomE.getText().toString()) ) {
                CreerRandomConcurrents();
                creerEntreprisePerso(tnomE.getText().toString(), tnomL.getText().toString());
            } else {
                Toast.makeText(this,"Veuillez remplir les champs !", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Une partie a déjà a été crée ! veuillez la charger.", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean nomEValide(String nomE) {
        int i = 0;
        if(!nomE.isEmpty()){
            while(i<liste_entreprises.size() && liste_entreprises.get(i).getNomEntreprise() != nomE ){
                i++;
            }
            return i==liste_entreprises.size();
        }else{
            return false;
        }

    }

    private boolean nomLValide(String nomL) {
        int i = 0;
        if(!nomL.isEmpty()){
            while(i<liste_logiciels.size() && liste_logiciels.get(i).getNomLogiciel() != nomL ){
                i++;
            }
            return i==liste_logiciels.size();
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
                liste_logiciels.clear();
                liste_logiciels.addAll(mylist);

            }
        }

        getLogiciels gt = new getLogiciels();
        gt.execute();
    }

    public void CreerRandomConcurrents() {

        for (int i = 0; i < 5; i++) {

            Entreprise ets_conc = new Entreprise("Concurrent"+i,"Soft"+i);

            int argent_depart = (int) (Math.random() * (2000 - 500));
            int nb_utilisateurs_depart = (int) (Math.random() * (50000 - 1500));

            ets_conc.setArgentEntreprise(argent_depart);
            ets_conc.getLogiciel().setNbUtilisateurs(nb_utilisateurs_depart);
            ets_conc.setNbusers(nb_utilisateurs_depart);

            setConcurrent(ets_conc);
        }

        //((MyApplication)this.getApplication()).setConcurrents(concurrents);
    }

    private void setConcurrent(final Entreprise concu) {

        class setConcurrent extends AsyncTask<Void, Void, Entreprise> {

            @Override
            protected Entreprise doInBackground(Void... voids) {

                mDb.getAppDatabase().entreprisedao().insert(concu);

                return concu;
            }

            @Override
            protected void onPostExecute(Entreprise concu) {
                super.onPostExecute(concu);

            }
        }

        setConcurrent sc = new setConcurrent();
        sc.execute();
    }

    private void getEntreprises() {

        class getEntreprises extends AsyncTask<Void, Void, List<EntreprisePerso>> {

            @Override
            protected List<EntreprisePerso> doInBackground(Void... voids) {

                List<EntreprisePerso> EntrepriseList = mDb.getAppDatabase().entreprisepersodao().getAll();

                return EntrepriseList;
            }

            @Override
            protected void onPostExecute(List<EntreprisePerso> mylist) {
                super.onPostExecute(mylist);

                // Mettre à jour l'adapter avec la liste de taches
                liste_entreprises.clear();
                liste_entreprises.addAll(mylist);

            }
        }

        getEntreprises gt = new getEntreprises();
        gt.execute();
    }

    private void creerEntreprisePerso(final String nomE, final String nomL) {

        class creerEntreprisePerso extends AsyncTask<Void, Void, EntreprisePerso> {

            @Override
            protected EntreprisePerso doInBackground(Void... voids) {
                EntreprisePerso e = new EntreprisePerso(nomE, nomL, 20000, 1, 0);
                mDb.getAppDatabase().entreprisepersodao().insert(e);

                return e;
            }

            @Override
            protected void onPostExecute(EntreprisePerso e) {
                super.onPostExecute(e);
                eperso = e;

                eperso.getLogiciel().setNbUtilisateurs(1000);

                creerLogiciel();
                MyApplication.getInstance().setEntreprise_joueur(eperso);

                Intent intent = new Intent(MyApplication.getInstance(), MainActivity.class);
                startActivity(intent);
            }
        }
        creerEntreprisePerso gt = new creerEntreprisePerso();
        gt.execute();
    }

    private void creerLogiciel() {

        class creerLogiciel extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                System.out.println(eperso.getNomLogiciel());

                mDb.getAppDatabase().logicieldao().insert(eperso.getLogiciel());
                return null;
            }
        }

        creerLogiciel gt = new creerLogiciel();
        gt.execute();
    }
}
