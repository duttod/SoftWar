package com.example.softwar.controllers;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softwar.R;

public class HomePageActivity extends AppCompatActivity {

    public final static int CREDIT_REQU = 0;
    public final static int CHARGER_PARTIE_REQ = 1;
    public final static int NOUVELLE_PARTIE_REQ = 2;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        getSupportActionBar().hide();
        TextView titre = findViewById(R.id.home_titre);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.digitall);
        titre.setTypeface(typeface);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.maintheme);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
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
                Toast.makeText(this,"Retour", Toast.LENGTH_SHORT).show();
        }
    }
/*
    public void setFont(TextView textView, String fontName) {
        if(fontName != null){
            try {
                //Typeface typeface = Typeface.createFromAsset(getAssets(), fontName);
                textView.setTypeface(Typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }*/

}
