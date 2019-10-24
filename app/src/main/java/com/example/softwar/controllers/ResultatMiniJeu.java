package com.example.softwar.controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Logiciel;
import com.example.softwar.data.ResultatJeu;

import java.util.List;

public class ResultatMiniJeu extends AppCompatActivity {

    public static final String nbpossible = "nbpossible";
    public static final String nbjuste = "nbjuste";

    List<ResultatJeu> listEvents;

    private int nbjuste_i;
    private int nbpossible_i;
    private String niveau;
    EntreprisePerso entreprisePerso;
    private DatabaseClient mDb;
    String action_demander;
    private TextView resultats;

    private TextView argent, nbusers, puissance, rentabilite, securite, ergonomie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_mini_jeu);
        getSupportActionBar().hide();
        mDb = DatabaseClient.getInstance(getApplicationContext());
        action_demander = this.getIntent().getStringExtra(ChooseRenforcerAttaquerActivity.ACTION_KEY);

        nbjuste_i = getIntent().getIntExtra(nbjuste,0);
        nbpossible_i = getIntent().getIntExtra(nbpossible,0);
        resultats = findViewById(R.id.resultats_mini_jeu);

        argent = findViewById(R.id.argent);
        nbusers = findViewById(R.id.nbusers);
        puissance = findViewById(R.id.puissance);
        rentabilite = findViewById(R.id.rentabilite);
        securite = findViewById(R.id.securite);
        ergonomie = findViewById(R.id.ergonomie);

        entreprisePerso= ((MyApplication)this.getApplication()).getEntreprise_joueur();

        determineniveau();
        affiche_resultats();

        getResJeu();
        entreprisePerso.setNbMiniJeux(entreprisePerso.getNbMiniJeux()+1);
        ((MyApplication)this.getApplication()).decrementCompteur();

    }

    public void determineniveau() {
        if ((nbjuste_i/nbpossible_i) < 0.25) {
            niveau = "mauvais";
        } else if ((nbjuste_i/nbpossible_i) >= 0.25 && (nbjuste_i/nbpossible_i) < 0.5) {
            niveau = "assezbon";
        } else if ((nbjuste_i/nbpossible_i) >= 0.5 && (nbjuste_i/nbpossible_i) < 0.75) {
            niveau = "bon";
        } else {
            niveau = "excellent";
            entreprisePerso.setNbMiniJeuxGagner(entreprisePerso.getNbMiniJeuxGagner()+1);
        }
    }

    public void affiche_resultats() {
        if (nbjuste_i == nbpossible_i) {
            resultats.setText("Bravo c'est gagné ! Score : "+nbjuste_i +"/"+nbpossible_i);
        } else {
            resultats.setText("Quelques petites erreurs ont été trouvées ! Score : "+nbjuste_i +"/"+nbpossible_i);
        }
    }

    public void giveRecompense() {

        int indice = (int) (Math.random() * ((listEvents.size()-1) - 0));
        ResultatJeu recompense = listEvents.get(indice);

        entreprisePerso.setArgentEntreprise(entreprisePerso.getArgentEntreprise()+recompense.getArgent());
        entreprisePerso.getLogiciel().setNbUtilisateurs(entreprisePerso.getLogiciel().getNbUtilisateurs()+recompense.getNbUtilisateurs());
        entreprisePerso.getLogiciel().setNiveauErgonomie(entreprisePerso.getLogiciel().getNiveauErgonomie()+recompense.getErgonomie());
        entreprisePerso.getLogiciel().setNiveauPuissance(entreprisePerso.getLogiciel().getNiveauPuissance()+recompense.getPuissance());
        entreprisePerso.getLogiciel().setNiveauRentabilite(entreprisePerso.getLogiciel().getNiveauRentabilite()+recompense.getRentabilite());
        entreprisePerso.getLogiciel().setNiveauSecurite(entreprisePerso.getLogiciel().getNiveauSecurite()+recompense.getSecurite());

        argent.setText("+ "+recompense.getArgent()+"€");
        nbusers.setText("+ "+recompense.getNbUtilisateurs()+" utilisateurs");
        puissance.setText("+ "+recompense.getPuissance()+" puissance du logiciel");
        ergonomie.setText("+ "+recompense.getErgonomie()+" ergonomie du logiciel");
        securite.setText("+ "+recompense.getSecurite()+" sécurité du logiciel");
        rentabilite.setText("+ "+recompense.getRentabilite()+" rentabilite du logiciel");

    }
    public void DammageEntreprise(){
        int indice = (int) (Math.random() * ((listEvents.size()-1) - 0));
        ResultatJeu degats = listEvents.get(indice);

        Entreprise eA = MyApplication.getInstance().getEntreprise_attaquer();
        eA.setNbusers(eA.getNbusers()-degats.getNbUtilisateurs());
        eA.setArgentEntreprise(eA.getArgentEntreprise()-degats.getArgent());

        argent.setText("- "+degats.getArgent()+"€");
        nbusers.setText("- "+degats.getNbUtilisateurs()+" utilisateurs");
        puissance.setText("- "+degats.getPuissance()+" puissance du logiciel");
        ergonomie.setText("- "+degats.getErgonomie()+" ergonomie du logiciel");
        securite.setText("- "+degats.getSecurite()+" sécurité du logiciel");
        rentabilite.setText("- "+degats.getRentabilite()+" rentabilite du logiciel");

        entreprisePerso.setArgentEntreprise(entreprisePerso.getArgentEntreprise()+degats.getArgent());
        ((MyApplication)this.getApplication()).setEntreprise_attaquer(null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Fais ton traitement
        }
        return true;
    }

    private void getResJeu() {
        ///////////////////////
        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class getResJeu extends AsyncTask<Void, Void, List<ResultatJeu>> {

            @Override
            protected List<ResultatJeu> doInBackground(Void... voids) {
                List<ResultatJeu> listEvents = mDb.getAppDatabase().resultatjeudao().getResultatSelonScore(niveau);
                return listEvents;
            }

            @Override
            protected void onPostExecute(List<ResultatJeu> mylist) {
                super.onPostExecute(mylist);

                listEvents = mylist;
                if(action_demander.equals("renforcer")){
                    giveRecompense();
                }else{
                    DammageEntreprise();
                }

            }
        }


        getResJeu gr = new getResJeu();
        gr.execute();
    }

    public void go_menu(View view) {
        Intent intent = new Intent(MyApplication.getInstance(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
