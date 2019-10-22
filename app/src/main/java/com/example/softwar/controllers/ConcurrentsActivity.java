package com.example.softwar.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;

public class ConcurrentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concurrents);


    }

    public void setClassement() {

        ScrollView linclassement = findViewById(R.id.scroll_classement);
        for (int i = 0; i < ((MyApplication)this.getApplication()).getConcurrents().size(); i++) {
            TextView tx = new TextView(this);

            String nomets = ((MyApplication)this.getApplication()).getConcurrents().get(i).getNomEntreprise();
            Long argentreprise = ((MyApplication)this.getApplication()).getConcurrents().get(i).getArgentEntreprise();
            int nbusers = ((MyApplication)this.getApplication()).getConcurrents().get(i).getLogiciel().getNbUtilisateurs();

            tx.setText("Entreprise : "+nomets+" - Argent : "+argentreprise+" - Nombre d'utilisateurs : "+nbusers);

            //linclassement.addView();
        }

    }

}
