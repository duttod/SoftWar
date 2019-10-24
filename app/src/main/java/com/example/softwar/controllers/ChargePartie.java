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

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;

import java.util.ArrayList;
import java.util.List;

public class ChargePartie extends AppCompatActivity {
    Typeface typeface;
    LinearLayout linear_bouttons_parties;
    private DatabaseClient mdb;

    public EntreprisePerso entreprise_joueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_charge_partie);
        mdb = DatabaseClient.getInstance(getApplicationContext());
        linear_bouttons_parties = findViewById(R.id.linear_bouttons_partie);

        if (mdb.getAppDatabase().entreprisepersodao().getAll() != null && (mdb.getAppDatabase().entreprisepersodao().getAll().size() != 0)) {
            getPartie();
        }
    }

    public void setView() {

        final Activity act = this;

        if (entreprise_joueur != null) {

            Button but = new Button(this);
            but.setTypeface(ResourcesCompat.getFont(this, R.font.nasalization));
            but.setTextColor(Color.parseColor("#FFB900"));
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

        class getPartie extends AsyncTask<Void, Void, List<EntreprisePerso>> {

            @Override
            protected List<EntreprisePerso> doInBackground(Void... voids) {

                List<EntreprisePerso> entreprise_j = mdb.getAppDatabase().entreprisepersodao().getAll();
                return entreprise_j;

            }

            @Override
            protected void onPostExecute(List<EntreprisePerso> ent) {
                super.onPostExecute(ent);

                    System.out.println("ID Charge : "+mdb.getAppDatabase().entreprisepersodao().getAll().get(0).getIdEmployeActif1());

                    entreprise_joueur = ent.get(0);
                    entreprise_joueur.setEntreprisePersoCharge(mdb);

                    MyApplication.getInstance().setEntreprise_joueur(entreprise_joueur);
                    setView();
            }

        }

        getPartie ge = new getPartie();
        ge.execute();

    }

}
