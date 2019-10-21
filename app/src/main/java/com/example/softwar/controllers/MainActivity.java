package com.example.softwar.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Logiciel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseClient mdb;
    EntreprisePerso entreprise_joueur ;
    TextView argent,nbuser,nomE;
    Logiciel monlogiciel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences session = getSharedPreferences(ChargePartie.MyPREFERENCES, Context.MODE_PRIVATE);
        argent = (TextView) findViewById(R.id.argent);
        nbuser = (TextView) findViewById(R.id.nbUtilisateurs);
        nomE = (TextView) findViewById(R.id.nomE);

        mdb = DatabaseClient.getInstance(getApplicationContext());

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

            }

        }

        getPartie ge = new getPartie();
        ge.execute();

    }
/*
    private void getLogiciel() {

        class getPartie extends AsyncTask<Void, Void, Logiciel> {

            @Override
            protected Logiciel doInBackground(Void... voids) {

                Logiciel log = mdb.getAppDatabase().logicieldao().getByEntreprise(entreprise_joueur.getNomLogiciel());
                return log ;
            }

            @Override
            protected void onPostExecute(Logiciel l) {
                super.onPostExecute(l);

                monlogiciel = l;
                LoadDataEntreprise();

            }

        }

        getPartie ge = new getPartie();
        ge.execute();

    }
*/
    public void LoadDataEntreprise() {

        argent.setText(Long.toString(entreprise_joueur.getArgentEntreprise()));
        nomE.setText(entreprise_joueur.getNomEntreprise());
        nbuser.setText(Integer.toString(monlogiciel.getNbUtilisateurs()));

    }


}
