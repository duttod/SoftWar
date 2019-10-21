package com.example.softwar.controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.softwar.R;

public class HomePageActivity extends AppCompatActivity {

    public final static int CREDIT_REQU = 0;
    public final static int CHARGER_PARTIE_REQ = 1;
    public final static int NOUVELLE_PARTIE_REQ = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void ChargerPartie(View view) {
        Intent intent = new Intent(this,ChargePartie.class);
        startActivityForResult(intent,CHARGER_PARTIE_REQ);
    }

    public void afficherCredit(View view) {
        Intent intent = new Intent(this,CreditActivity.class);
        startActivityForResult(intent,CREDIT_REQU);
    }

    public void Nouvelle_Partie(View view) {
        Intent intent = new Intent(this,CreationNewPartie.class);
        startActivityForResult(intent,NOUVELLE_PARTIE_REQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == CREDIT_REQU || requestCode == CHARGER_PARTIE_REQ || requestCode == NOUVELLE_PARTIE_REQ) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,"Retour OK", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this,"Problème retour", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
