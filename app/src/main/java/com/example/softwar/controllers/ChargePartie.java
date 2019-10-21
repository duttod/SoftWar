package com.example.softwar.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;

import java.util.List;

public class ChargePartie extends AppCompatActivity {

    LinearLayout linear_bouttons_parties;
    private DatabaseClient mdb;
    public static final String MyPREFERENCES = "MyPrefs" ;

    public EntreprisePerso entreprise_joueur;
    SharedPreferences session ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charge_partie);

        mdb = DatabaseClient.getInstance(getApplicationContext());
        linear_bouttons_parties = findViewById(R.id.linear_bouttons_partie);
        session = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        getPartie();
    }

    public void setView() {

        final Activity act = this;

        if (entreprise_joueur != null) {

            Button but = new Button(this);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Start la partie
                    SharedPreferences.Editor editor = session.edit();

                    editor.putString("NomEntreprise", entreprise_joueur.getNomEntreprise());
                    editor.commit();
                    Intent intent = new Intent(ChargePartie.super.getApplication(),MainActivity.class);
                    startActivity(intent);
                }
            });

            but.setText(entreprise_joueur.getNomEntreprise());
            linear_bouttons_parties.addView(but);

        } else {

            TextView txt = new TextView(this);
            txt.setText("Aucune partie disponible");
            linear_bouttons_parties.addView(txt);

        }

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
                setView();

            }

        }

        getPartie ge = new getPartie();
        ge.execute();

    }

}
