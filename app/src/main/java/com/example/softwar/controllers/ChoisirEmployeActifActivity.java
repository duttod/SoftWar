package com.example.softwar.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Employe;

import java.util.ArrayList;
import java.util.List;

public class ChoisirEmployeActifActivity extends AppCompatActivity {

    //

    // DATA
    private DatabaseClient mDb;
    private ListeEmployeAdapter adapter;


    // VIEW
    private ListView listEmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_employe_actif);

        // Récupération du DatabaseClient
        mDb = DatabaseClient.getInstance(getApplicationContext());

        // Récupérer les vues


        // Lier l'adapter au listView
        adapter = new ListeEmployeAdapter(this,new ArrayList<Employe>());
        listEmp.setAdapter(adapter);


        // Ajout du drag and drop sur la listView
        /*
        * listEmp.set [...]
        *
        * */

        getEmp();


    }

    private void getEmp(){
        class GetEmp extends AsyncTask<Void, Void, List<Employe>>{

            @Override
            protected List<Employe> doInBackground(Void... voids) {
                List<Employe> employeList = mDb.getAppDatabase()
                        .employeDao() // ne pas récupếrer dans la bdd mais dans entreprise ! à faire lorsque l'on a les var global !!!!!!
                        .getAll();

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
}
