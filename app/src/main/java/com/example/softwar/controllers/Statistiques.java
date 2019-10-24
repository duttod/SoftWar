package com.example.softwar.controllers;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Employe;
import com.example.softwar.data.EmployeDansEntreprise;
import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Statistiques extends AppCompatActivity {

    ArrayList<Entreprise> liste_entreprises;
    EntreprisePerso entreprise_joueur;
    int users_total;
    LinearLayout eactif;
    DatabaseClient mdb = DatabaseClient.getInstance(MyApplication.getInstance());
    TextView tnomE,targent,tnbu,tnbe,tpartm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistiques);
        getSupportActionBar().hide();
        entreprise_joueur =((MyApplication)this.getApplication()).getEntreprise_joueur();
        liste_entreprises = new ArrayList<>();

        eactif = findViewById(R.id.listEmployeActif);
        tnomE = findViewById(R.id.snom_entreprise);
        targent = findViewById(R.id.sargent_entreprise);
        tnbu= findViewById(R.id.snb_utilisateurs);
        tnbe= findViewById(R.id.snombre_employes);
        tpartm =  findViewById(R.id.spart_marche);
        setStatistiquesE();
        afficheEmployeActif();/*
        afficheEmployeActif1();
        afficheEmployeActif2();
        afficheEmployeActif3();*/
    }

/*    public void afficheEmployeActif1() {
            int id1 = entreprise_joueur.getIdEmployeActif1();
            if(entreprise_joueur.getEmployeById(id1)!=null) {
                Employe e = entreprise_joueur.getEmployeById(id1);
                LinearLayout mavue = new LinearLayout(this);
                mavue.setOrientation(LinearLayout.HORIZONTAL);

                TextView nomE = new TextView(this);
                nomE.setText("Nom:"+e.getNomEmploye()+" ");
                nomE.setTextColor(Color.RED);
                nomE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                TextView pE = new TextView(this);
                pE.setText("Prénom:"+e.getPrenomEmploye()+" ");
                pE.setTextColor(Color.RED);
                pE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                TextView prodE = new TextView(this);
                prodE.setText("Productivité:"+Integer.toString(e.getProductivite())+" ");
                prodE.setTextColor(Color.RED);
                prodE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                TextView rapE = new TextView(this);
                rapE.setText("Rapidité:"+Integer.toString(e.getRapidite())+" ");
                rapE.setTextColor(Color.RED);
                rapE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                mavue.addView(nomE);
                mavue.addView(pE);
                mavue.addView(prodE);
                mavue.addView(rapE);
                eactif.addView(mavue);

            }
    }
    public void afficheEmployeActif2() {
        int id1 = entreprise_joueur.getIdEmployeActif2();
        if(entreprise_joueur.getEmployeById(id1)!=null) {
            Employe e = entreprise_joueur.getEmployeById(id1);
            LinearLayout mavue = new LinearLayout(this);
            mavue.setOrientation(LinearLayout.HORIZONTAL);

            TextView nomE = new TextView(this);
            nomE.setText("Nom:"+e.getNomEmploye()+" ");
            nomE.setTextColor(Color.RED);
            nomE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            TextView pE = new TextView(this);
            pE.setText("Prénom:"+e.getPrenomEmploye()+" ");
            pE.setTextColor(Color.RED);
            pE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            TextView prodE = new TextView(this);
            prodE.setText("Productivité:"+Integer.toString(e.getProductivite())+" ");
            prodE.setTextColor(Color.RED);
            prodE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            TextView rapE = new TextView(this);
            rapE.setText("Rapidité:"+Integer.toString(e.getRapidite())+" ");
            rapE.setTextColor(Color.RED);
            rapE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            mavue.addView(nomE);
            mavue.addView(pE);
            mavue.addView(prodE);
            mavue.addView(rapE);
            eactif.addView(mavue);

        }
    }
    public void afficheEmployeActif3() {
        int id1 = entreprise_joueur.getIdEmployeActif3();
        if(entreprise_joueur.getEmployeById(id1)!=null) {
            Employe e = entreprise_joueur.getEmployeById(id1);
            LinearLayout mavue = new LinearLayout(this);
            mavue.setOrientation(LinearLayout.HORIZONTAL);

            TextView nomE = new TextView(this);
            nomE.setText("Nom:"+e.getNomEmploye()+" ");
            nomE.setTextColor(Color.RED);
            nomE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            TextView pE = new TextView(this);
            pE.setText("Prénom:"+e.getPrenomEmploye()+" ");
            pE.setTextColor(Color.RED);
            pE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            TextView prodE = new TextView(this);
            prodE.setText("Productivité:"+Integer.toString(e.getProductivite())+" ");
            prodE.setTextColor(Color.RED);
            prodE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            TextView rapE = new TextView(this);
            rapE.setText("Rapidité:"+Integer.toString(e.getRapidite())+" ");
            rapE.setTextColor(Color.RED);
            rapE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

            mavue.addView(nomE);
            mavue.addView(pE);
            mavue.addView(prodE);
            mavue.addView(rapE);
            eactif.addView(mavue);

        }
    }*/

    public void afficheEmployeActif(){
        for (int i = 0; i<3; i++){
            if (entreprise_joueur.getEmployeActif().get(i) != -1){

                Employe emp = mdb.getAppDatabase().employeDao().getAnEmploye(entreprise_joueur.getEmployeActif().get(i));

                LinearLayout mavue = new LinearLayout(this);
                mavue.setOrientation(LinearLayout.HORIZONTAL);

                TextView nomE = new TextView(this);
                nomE.setText("Nom:"+emp.getNomEmploye()+" ");
                nomE.setTextColor(Color.RED);
                nomE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                TextView pE = new TextView(this);
                pE.setText("Prénom:"+emp.getPrenomEmploye()+" ");
                pE.setTextColor(Color.RED);
                pE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                TextView prodE = new TextView(this);
                prodE.setText("Productivité:"+Integer.toString(emp.getProductivite())+" ");
                prodE.setTextColor(Color.RED);
                prodE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                TextView rapE = new TextView(this);
                rapE.setText("Rapidité:"+Integer.toString(emp.getRapidite())+" ");
                rapE.setTextColor(Color.RED);
                rapE.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);

                mavue.addView(nomE);
                mavue.addView(pE);
                mavue.addView(prodE);
                mavue.addView(rapE);
                eactif.addView(mavue);


            }

        }
        // Affichage des stats global des employés
        TextView textStatGlobal = (TextView)findViewById(R.id.textStatGlobal);
        textStatGlobal.setText("Prod : " + entreprise_joueur.getStatEmployeActif().get(0) + " Rap : " + entreprise_joueur.getStatEmployeActif().get(1));


    }


    public void setStatistiquesE() {
        tnomE.setText("Nom:"+entreprise_joueur.getNomEntreprise());
        targent.setText("Capital:"+Long.toString(entreprise_joueur.getArgentEntreprise()));
        tnbu.setText("Utilisateurs:"+Integer.toString(entreprise_joueur.getNbusers()));
        tnbe.setText("Employes:"+Integer.toString(entreprise_joueur.getEmployes().size()));

        //part de marché
        for (int i = 0; i < ((MyApplication)this.getApplication()).getConcurrents().size(); i++) {
            liste_entreprises.add(((MyApplication)this.getApplication()).getConcurrents().get(i));
        }

        liste_entreprises.add(((MyApplication)this.getApplication()).getEntreprise_joueur());
        Collections.sort(liste_entreprises);
        for (int i = 0; i < liste_entreprises.size(); i++) {
            users_total += liste_entreprises.get(i).getLogiciel().getNbUtilisateurs();
        }
        tpartm.setText("Parts de marché : "+((entreprise_joueur.getNbusers()/users_total)*100)+"%");
    }

}
