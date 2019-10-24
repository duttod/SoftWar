package com.example.softwar.controllers;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Employe;
import com.example.softwar.data.EntreprisePerso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChoisirEmployeActifActivity extends AppCompatActivity {

    //

    // DATA
    private DatabaseClient mDb;
    private ListeEmployeAdapter adapter;
    private EntreprisePerso entreprise_joueur;

    // VIEW
    private ListView listEmp;
    private ArrayList<LinearLayout> empActif = new ArrayList<>();
    private TextView text;

    @Override
    public void onPause(){
        super.onPause();
        ((MyApplication)this.getApplication()).mediaPlayer.pause();
    }
    @Override
    public void onResume(){
        super.onResume();
        ((MyApplication)this.getApplication()).mediaPlayer.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_employe_actif);
        getSupportActionBar().hide();
        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());
        entreprise_joueur=((MyApplication)this.getApplication()).getEntreprise_joueur();

        // Récupérer les vues
        listEmp = findViewById(R.id.listEmploye);
        empActif.add((LinearLayout) findViewById(R.id.employe1));
        empActif.add((LinearLayout) findViewById(R.id.employe2));
        empActif.add((LinearLayout) findViewById(R.id.employe3));

        // Set les vues si des Employé sont déjà actif
        setEmployeDejaActif();


        // Lier l'adapter au listView
        adapter = new ListeEmployeAdapter(this,new ArrayList<Employe>());
        listEmp.setAdapter(adapter);


        // Ajout click sur la listView
        listEmp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employe emp = adapter.getItem(position);
                //

                //
                int i = 0;
                int occurrence =0;
                for (int c =0; c<3;c++){
                    if(empActif.get(c).getChildCount()>0){
                        System.out.println(empActif.get(c).getChildCount());
                        if (Integer.parseInt(((TextView)empActif.get(c).getChildAt(2)).getText().toString()) == emp.getId()){
                            occurrence++;
                        }

                    }
                }
                if (occurrence<mDb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(entreprise_joueur.getNomEntreprise(),emp.getId()).getQuantite()){
                    while (i < 3 && empActif.get(i).getChildCount()>0){
                        System.out.println(empActif.get(i).getChildCount());
                        System.out.println(empActif.get(i).getChildAt(0));
                        i++;
                    }
                    System.out.println(i);
                    if (i == 3 && empActif.get(i-1).getChildCount() >0){

                    }
                    else {
                        setEmployeActif(i,emp);


                    }
                }
                else {
                    Toast.makeText(ChoisirEmployeActifActivity.this,"Employé déjà utilisé",Toast.LENGTH_SHORT).show();
                }

            }
        });




        getEmp();
    }

    private void getEmp(){
        class GetEmp extends AsyncTask<Void, Void, List<Employe>>{

            @Override
            protected List<Employe> doInBackground(Void... voids) {
/*                List<Employe> employeList = mDb.getAppDatabase()
                        .employeDao() // ne pas récupếrer dans la bdd mais dans entreprise ! à faire lorsque l'on a les var global !!!!!!
                        .getAll();*/
                ArrayList<Employe> employeList = new ArrayList<>();
                for (int i =0; i<entreprise_joueur.getEmployes().size();i++){
                   employeList.add(mDb.getAppDatabase().employeDao().getAnEmploye(entreprise_joueur.getEmployes().get(i).getIdEmploye()));
                }
//                 Employe e = new Employe("Thierry","Henry",4,1,1,1);
//                 employeList.add(e);

                System.out.println(employeList.get(0).getNomEmploye());
                return employeList;
            }

            @Override
            protected void onPostExecute(List<Employe> employeList) {
                super.onPostExecute(employeList);

                // Mettre à jour l'adapter avec la liste des employés
                adapter.clear();
                adapter.addAll(employeList);

                // Notifier l'adapter du changement
                adapter.notifyDataSetChanged();
            }
        }

        GetEmp ge = new GetEmp();
        ge.execute();

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }*/
    public void setEmployeDejaActif(){
        for (int i = 0; i < 3 ;i++) {
            if (entreprise_joueur.getEmployeActif().get(i) != -1){
                setEmployeActif(i,mDb.getAppDatabase().employeDao().getAnEmploye(entreprise_joueur.getEmployeActif().get(i)));
            }

        }

    }
    public void setEmployeActif(int i, Employe emp){
        // TODO  : Prendre en compte l'insertion et la suppression dans la bdd
        final int j = i;
        // Création du TextView
        text = new TextView(ChoisirEmployeActifActivity.this);
        text.setText(emp.getNomEmploye());
        setDesignEmpActif(text);
        empActif.get(i).addView(text);
        text = new TextView(ChoisirEmployeActifActivity.this);
        text.setText(emp.getPrenomEmploye());
        setDesignEmpActif(text);
        empActif.get(i).addView(text);
        text = new TextView(ChoisirEmployeActifActivity.this);
        text.setText(Integer.toString(emp.getId()));
        text.setVisibility(View.INVISIBLE);
        empActif.get(i).addView(text);

        // Suppression de la list des employes Actif
        empActif.get(i).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empActif.get(j).removeAllViews();

                // Mise à jour bdd
                // TODO enlever les employeActif
                // -1 id quand il n'y a pas d'employé
                entreprise_joueur.setEmployeActif(j,-1);
                mDb.getAppDatabase().entreprisepersodao().update(entreprise_joueur);

            }
        });
        /*
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         *
         * Zone Code : mettre à jour la bdd de entreprise + set employé dans MyApplication
         *
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * */

        entreprise_joueur.setEmployeActif(j,emp.getId());
        mDb.getAppDatabase().entreprisepersodao().update(entreprise_joueur);

    }

    public void setDesignEmpActif(TextView text){
        text.setTypeface(ResourcesCompat.getFont(this, R.font.nasalization));
        text.setTextColor(Color.parseColor("#FFB900"));
        text.setTextSize(25);
    }
}
