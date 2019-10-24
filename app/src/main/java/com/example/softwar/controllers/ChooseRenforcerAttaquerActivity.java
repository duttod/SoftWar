package com.example.softwar.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.softwar.R;

import com.example.softwar.R;

public class ChooseRenforcerAttaquerActivity extends AppCompatActivity {
    public final static String ACTION_KEY = "action_demander";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_renforcer_attaquer);
        getSupportActionBar().hide();
    }

    public void MiniJeuRenforcer(View view) {
        Intent intent = new Intent(this,MiniJeu.class);
        intent.putExtra(ACTION_KEY,"renforcer");
        startActivity(intent);
    }

    public void MiniJeuAttaquer(View view) {
        Intent intent = new Intent(this,ChooseEntrepriseToAttack.class);
        intent.putExtra(ACTION_KEY,"attaquer");
        startActivity(intent);
    }
}
