package com.example.softwar.controllers;

import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tirage_au_sort);
        mDb = DatabaseClient.getInstance(getApplicationContext());
        getSupportActionBar().hide();
    }

    public void onepull(View view) {
        //Tire l'employé
        t = new Tirage();
        t.Tirages(1);
        //Ajoute au model et a la BD
        entreprise_joueur =((MyApplication)this.getApplication()).getEntreprise_joueur();
        entreprise_joueur.addEmploye(t.getEmployeTire().get(0));
        dialog = new Dialog(MyApplication.getContext());
        dialog.setContentView(R.layout.popup_tirage);
        TextView nomE = (TextView)dialog.findViewById(R.id.nom_employe);
        TextView pnomE = (TextView)dialog.findViewById(R.id.prenom_employe);
        TextView rarE = (TextView)dialog.findViewById(R.id.rarete_employe);
        nomE.setText(t.getEmployeTire().get(0).getNomEmploye());
        pnomE.setText(t.getEmployeTire().get(0).getPrenomEmploye());
        rarE.setText(t.getEmployeTire().get(0).getRarete());
        dialog.show();


    }

    public void tenpull(View view) {
        //Tire l'employé
        for (int i=0 ; i<10 ;i++){
            onepull(view);
        }

    }

    private void sauvegarderEmploye(final String nomL) {

        class sauvegarderEmploye extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Logiciel l = new Logiciel(nomL);
                mDb.getAppDatabase().logicieldao().insert(l);

                return null;
            }

        }

        sauvegarderEmploye gt = new sauvegarderEmploye();
        gt.execute();
    }

    public void fermerbox(View view) {
        dialog.cancel();
    }
}
