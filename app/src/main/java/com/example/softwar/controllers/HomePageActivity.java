package com.example.softwar.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.softwar.R;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void ChargerPartie(View view) {
    }

    public void afficherCredit(View view) {
        Intent intent = new Intent(this,CreditActivity.class);
        startActivity(intent);

    }

    public void Nouvelle_Partie(View view) {
        Intent intent = new Intent(this,CreationNewPartie.class);
        startActivity(intent);

    }
}
