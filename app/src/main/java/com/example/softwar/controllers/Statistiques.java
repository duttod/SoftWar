package com.example.softwar.controllers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;

import java.util.List;

public class Statistiques extends AppCompatActivity {

    private EntreprisePerso entreprise_joueur;
    private DatabaseClient mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);
        getSupportActionBar().hide();
        mdb = DatabaseClient.getInstance(getApplicationContext());

        getPartie();
    }

    private void getPartie() {

        class getPartie extends AsyncTask<Void, Void, EntreprisePerso> {

            @Override
            protected EntreprisePerso doInBackground(Void... voids) {

                List<EntreprisePerso> entreprise_j = mdb.getAppDatabase().entreprisepersodao().getAll();
                if (entreprise_j.get(0) != null) {
                    return entreprise_j.get(0);
                } else {
                    return null;
                }
            }

            @Override
            protected void onPostExecute(EntreprisePerso ent) {
                super.onPostExecute(ent);

                entreprise_joueur = ent;
                setStatistiques();
            }

        }

        getPartie ge = new getPartie();
        ge.execute();

    }

    public void setStatistiques() {

    }

}
