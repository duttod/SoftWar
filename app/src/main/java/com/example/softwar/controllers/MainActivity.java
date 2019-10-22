package com.example.softwar.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Logiciel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseClient mdb;
    EntreprisePerso entreprise_joueur ;
    TextView argent,nbuser,nomE;

    ArrayList<Entreprise> concurrents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        argent = (TextView) findViewById(R.id.argent);
        nbuser = (TextView) findViewById(R.id.nbUtilisateurs);
        nomE = (TextView) findViewById(R.id.nomE);

        mdb = DatabaseClient.getInstance(getApplicationContext());
        concurrents = new ArrayList<>();

        getPartie();
    }


    private void getPartie() {

        class getPartie extends AsyncTask<Void, Void, EntreprisePerso> {

            @Override
            protected EntreprisePerso doInBackground(Void... voids) {

                ArrayList<EntreprisePerso> entreprise_j = new ArrayList<>();
                entreprise_j.addAll(mdb.getAppDatabase().entreprisepersodao().getAll());
                if (entreprise_j.get(0) != null) {
                    return entreprise_j.get(0);
                } else {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(EntreprisePerso ent) {
                super.onPostExecute(ent);

                entreprise_joueur = new EntreprisePerso (mdb, ent.getNomEntreprise(), ent.getNomLogiciel(), ent.getArgentEntreprise(), ent.getNbContrats(), ent.getProductivite());

                LoadDataEntreprise();
                CreerRandomConcurrents();
                setImageLogiciel();

            }

        }

        getPartie ge = new getPartie();
        ge.execute();

    }

    public void LoadDataEntreprise() {

        argent.setText("Argent:"+Long.toString(entreprise_joueur.getArgentEntreprise()));
        nomE.setText("Entreprise:"+entreprise_joueur.getNomEntreprise());
        nbuser.setText("Utilisateurs:"+Integer.toString(entreprise_joueur.getLogiciel().getNbUtilisateurs()));

    }

    public void CreerRandomConcurrents() {

        for (int i = 0; i < 5; i++) {
            Entreprise ets_conc = new Entreprise("Concurrent"+i,"Soft"+i);

            int argent_depart = (int) (Math.random() * (500 - 2000));
            int nb_utilisateurs_depart = (int) (Math.random() * (1500 - 50000));

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
}
