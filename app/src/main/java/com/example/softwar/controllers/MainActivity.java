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

        //Récupérer la variable globale Application
        //!!!!!!!!!!

        concurrents = new ArrayList<>();

        setImageLogiciel();
        LoadDataEntreprise();
    }

    public void LoadConcurrents() {
        getConcurrents();
        ((MyApplication)this.getApplication()).setConcurrents(concurrents);

    }

    private void getConcurrents() {

        class getConcurrents extends AsyncTask<Void, Void, List<Entreprise>> {

            @Override
            protected List<Entreprise> doInBackground(Void... voids) {

                List<Entreprise> concu = mdb.getAppDatabase().entreprisedao().getAll();

                return concu;
            }

            @Override
            protected void onPostExecute(List<Entreprise> concu) {
                super.onPostExecute(concu);

                concurrents.clear();
                concurrents.addAll(concu);

            }
        }

        getConcurrents gc = new getConcurrents();
        gc.execute();
    }

    public void LoadDataEntreprise() {

        argent.setText(Long.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getArgentEntreprise()));
        nomE.setText(((MyApplication)this.getApplication()).getEntreprise_joueur().getNomEntreprise());
        nbuser.setText(Integer.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs()));
        argent.setText("Argent:"+Long.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getArgentEntreprise()));
        nomE.setText("Entreprise:"+((MyApplication)this.getApplication()).getEntreprise_joueur().getNomEntreprise());
        nbuser.setText("Utilisateurs:"+Integer.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs()));

        LoadConcurrents();
    }

    public void setImageLogiciel() {

        switch(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauLogiciel()) {
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
