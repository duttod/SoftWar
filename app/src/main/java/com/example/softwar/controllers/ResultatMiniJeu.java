package com.example.softwar.controllers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
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

    private DatabaseClient mDb;

    private TextView resultats;

    private TextView argent, nbusers, puissance, rentabilite, securite, ergonomie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_mini_jeu);
        getSupportActionBar().hide();
        mDb = DatabaseClient.getInstance(getApplicationContext());

        nbjuste_i = getIntent().getIntExtra(nbjuste,0);
        nbpossible_i = getIntent().getIntExtra(nbpossible,0);
        resultats = findViewById(R.id.resultats_mini_jeu);

        argent = findViewById(R.id.argent);
        nbusers = findViewById(R.id.nbusers);
        puissance = findViewById(R.id.puissance);
        rentabilite = findViewById(R.id.rentabilite);
        securite = findViewById(R.id.securite);
        ergonomie = findViewById(R.id.ergonomie);

        determineniveau();
        affiche_resultats();

        getResJeu();

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

        ((MyApplication)this.getApplication()).getEntreprise_joueur().setArgentEntreprise(((MyApplication)this.getApplication()).getEntreprise_joueur().getArgentEntreprise()+recompense.getArgent());
        ((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().setNbUtilisateurs(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs()+recompense.getNbUtilisateurs());
        ((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauErgonomie(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie()+recompense.getErgonomie());
        ((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauPuissance(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance()+recompense.getPuissance());
        ((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauRentabilite(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite()+recompense.getRentabilite());
        ((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauSecurite(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite()+recompense.getSecurite());

        argent.setText("+ "+recompense.getArgent()+"€");
        nbusers.setText("+ "+recompense.getNbUtilisateurs()+" utilisateurs");
        puissance.setText("+ "+recompense.getPuissance()+" puissance du logiciel");
        ergonomie.setText("+ "+recompense.getErgonomie()+" ergonomie du logiciel");
        securite.setText("+ "+recompense.getSecurite()+" sécurité du logiciel");
        rentabilite.setText("+ "+recompense.getRentabilite()+" rentabilite du logiciel");

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
                giveRecompense();
            }
        }

        getResJeu gr = new getResJeu();
        gr.execute();
    }
}
