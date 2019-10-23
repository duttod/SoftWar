package com.example.softwar.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.softwar.R;

public class ResultatMiniJeu extends AppCompatActivity {

    public static final String nbpossible = "nbpossible";
    public static final String nbjuste = "nbjuste";

    private int nbjuste_i;
    private int nbpossible_i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_mini_jeu);

        nbjuste_i = Integer.parseInt(getIntent().getStringExtra(nbjuste));
        nbpossible_i = Integer.parseInt(getIntent().getStringExtra(nbpossible));
    }
}
