package com.example.softwar.controllers;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Logiciel;
import com.example.softwar.data.Tirage;

public class TirageAuSortActivity extends AppCompatActivity {
    Tirage t;
    EntreprisePerso entreprise_joueur ;
    private DatabaseClient mDb;
    Dialog dialog;
    Dialog dialog2;
    TextView nbc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirage_au_sort);
        mDb = DatabaseClient.getInstance(getApplicationContext());
        entreprise_joueur =((MyApplication)this.getApplication()).getEntreprise_joueur();
        getSupportActionBar().hide();
        nbc = findViewById(R.id.nbcontrats);
        nbc.setText("Nombre de contrats possédés:"+Integer.toString(entreprise_joueur.getNbContrats()));


        dialog2 = new Dialog(TirageAuSortActivity.this);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog2.setContentView(R.layout.popup_amelioration);
    }

    public void onepull(View view) {
        //Tire l'employé
        if(entreprise_joueur.getNbContrats()>=1){


        t = new Tirage();
        t.Tirages(1);
        nbc.setText("Nombre de contrats possédés:"+Integer.toString(entreprise_joueur.getNbContrats()));
        //Ajoute au model et a la BD
        entreprise_joueur.addEmploye(t.getEmployeTire().get(0));
        dialog = new Dialog(TirageAuSortActivity.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.popup_tirage);
        TextView nomE = (TextView)dialog.findViewById(R.id.nom_employe);
        TextView pnomE = (TextView)dialog.findViewById(R.id.prenom_employe);
        TextView rarE = (TextView)dialog.findViewById(R.id.rarete_employe);
        nomE.setText(t.getEmployeTire().get(0).getNomEmploye());
        pnomE.setText(t.getEmployeTire().get(0).getPrenomEmploye());
        rarE.setText(Integer.toString(t.getEmployeTire().get(0).getRarete()));
        dialog.show();
        LinearLayout layoutpop = findViewById(R.id.layout_tirage);
        dialog.getWindow().setLayout(layoutpop.getWidth()+130, (layoutpop.getHeight()/2)+40);
        }else{
            //TODO Pas asser de contrat ouvrir boite de dialogue
            dialog2.show();
        }


    }

    public void tenpull(View view) {
        //Tire l'employé
        if(entreprise_joueur.getNbContrats()>=10){
            for (int i=0 ; i<10 ;i++){
                onepull(view);
            }
        }else{
            //TODO Pas asser de contrat ouvrir boite de dialogue
            dialog2.show();
        }



    }

    public void fermerbox(View view) {
        dialog.cancel();
    }
}
