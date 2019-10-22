package com.example.softwar.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;

import java.util.ArrayList;
import java.util.List;

public class ChargePartie extends AppCompatActivity {
    Typeface typeface;
    LinearLayout linear_bouttons_parties;
    private DatabaseClient mdb;
    TextView titre;
    TextView soustitre;

    public EntreprisePerso entreprise_joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_charge_partie);
        typeface = ResourcesCompat.getFont(this, R.font.nasalization);
        mdb = DatabaseClient.getInstance(getApplicationContext());
        linear_bouttons_parties = findViewById(R.id.linear_bouttons_partie);
        titre = findViewById(R.id.titre_CreationNewPartie);
        soustitre = findViewById(R.id.chargerpartie_soustitre);
        soustitre.setTypeface(typeface);
        titre.setTypeface(typeface);
        getPartie();
    }

    public void setView() {

        final Activity act = this;

        if (entreprise_joueur != null) {

            Button but = new Button(this);
            but.setTypeface(typeface);
            but.setTextColor(Color.WHITE);
            but.setTextSize(25);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setShape(GradientDrawable.RECTANGLE);
            drawable.setStroke(5, Color.parseColor("#00BBDE"));
            drawable.setColor(Color.parseColor("#21004B"));

            but.setBackgroundDrawable(drawable);
            //but.setBackground(Drawable.createFromPath("@drawable/bgbutton.png"));
            //but.setPadding(0,0,0,0);
            //but.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Start la partie

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

                ArrayList<EntreprisePerso> entreprise_j = new ArrayList<EntreprisePerso>();
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
                setView();

            }

        }

        getPartie ge = new getPartie();
        ge.execute();

    }

}
