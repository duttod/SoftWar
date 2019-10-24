package com.example.softwar.controllers;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.Alea;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Entreprise;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Jeu;
import com.example.softwar.data.Logiciel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView argent, nbuser, nomE;
    private DatabaseClient mdb;
    EntreprisePerso entreprise_joueur ;
    ArrayList<Entreprise> concurrents;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.popup_aleadebuttour);

        argent = (TextView) findViewById(R.id.argent);
        nbuser = (TextView) findViewById(R.id.nbUtilisateurs);
        nomE = (TextView) findViewById(R.id.nomE);

        mdb = DatabaseClient.getInstance(getApplicationContext());

        //Récupérer la variable globale Application
        //!!!!!!!!!!
        entreprise_joueur =((MyApplication)this.getApplication()).getEntreprise_joueur();
        concurrents = new ArrayList<>();

        //setImageLogiciel();
        LoadConcurrents();
    }

    //Pas toucher
    public void LoadConcurrents() {

        getConcurrents();

    }

    private void getConcurrents() {

        class getConcurrents extends AsyncTask<Void, Void, List<Entreprise>> {

            @Override
            protected List<Entreprise> doInBackground(Void... voids) {

                List<Entreprise> concu = mdb.getAppDatabase().entreprisedao().getAll();

                return concu;
            }

            @Override
            protected void onPostExecute(List<Entreprise> concu) {
                super.onPostExecute(concu);

                concurrents.clear();
                concurrents.addAll(concu);

                for (int i = 0; i < concurrents.size(); i++) {

                    concurrents.get(i).setLogiciel(concurrents.get(i).getNomLogiciel());
                    concurrents.get(i).getLogiciel().setNbUtilisateurs(concurrents.get(i).getNbusers());
                    System.out.println(concurrents.get(i).getNomLogiciel());
                    concurrents.get(i).getLogiciel().setNomLogiciel(concurrents.get(i).getNomLogiciel());
                }

                setMyAppConcu();
                LoadDataEntreprise();

            }
        }

        getConcurrents gc = new getConcurrents();
        gc.execute();
    }

    public void setMyAppConcu() {
        ((MyApplication)this.getApplication()).setConcurrents(concurrents);
    }

    public void LoadDataEntreprise() {

        argent.setText(Long.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getArgentEntreprise()));
        nomE.setText(((MyApplication)this.getApplication()).getEntreprise_joueur().getNomEntreprise());
        nbuser.setText(Integer.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs()));
        argent.setText("Argent:"+Long.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getArgentEntreprise()));
        nomE.setText("Entreprise:"+((MyApplication)this.getApplication()).getEntreprise_joueur().getNomEntreprise());
        nbuser.setText("Utilisateurs:"+Integer.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs()));
    }

    public void setImageLogiciel() {

        switch(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauLogiciel()) {
            case 1 : ////
                break;
            case 2 : ////
                break;
            case 3 : ////
                break;
            case 4 : ////
                break;
            case 5 : ////
                break;
        }

    }

    public void GoToInvocation(View view) {
        Intent intent = new Intent(MyApplication.getInstance(),TirageAuSortActivity.class);
        startActivity(intent);

    }

    public void go_concurrents(View view) {
        Intent intent = new Intent(this, ConcurrentsActivity.class);
        startActivity(intent);
    }

    public void go_employes(View view) {
        Intent intent = new Intent(this,ChoisirEmployeActifActivity.class);
        startActivity(intent);
    }


    public void GoToAmeliorer(View view) {
        Intent intent = new Intent(this,AmeliorationsActivity.class);
        startActivity(intent);
    }

    public void GoToChoixAttackDef(View view) {
        Intent intent = new Intent(this,ChooseRenforcerAttaquerActivity.class);
        startActivity(intent);
    }

    public void GoToStats(View view) {
        Intent intent = new Intent(this,Statistiques.class);
        startActivity(intent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LoadDataEntreprise();
    }

    //PARTIE A PASSER EN ASYNCHRONE SI POSSIBLE

    public void RandomEvenementDebutTour(View view) {

        int chanceevenement = (int) (Math.random() * (100 - 0));
        TextView txt = dialog.findViewById(R.id.description_alea);
        txt.setText("");

        if (chanceevenement <= 50) {

            List<Alea> aleas = mdb.getAppDatabase().aleadao().getAll();
            int indice = (int) (Math.random() * ((aleas.size() - 1) - 0));

            Alea aleachoisi = aleas.get(indice);
            txt.setText(aleachoisi.getContexte()+ " | ");

            if (aleachoisi.getType().equals("bien")) {

                if (aleachoisi.getArgent() != 0) {
                    ((MyApplication) this.getApplication()).getEntreprise_joueur().setArgentEntreprise(((MyApplication) this.getApplication()).getEntreprise_joueur().getArgentEntreprise() + aleachoisi.getArgent());
                    txt.setText(txt.getText() + "+" + aleachoisi.getArgent() + "€ | ");
                }
                if (aleachoisi.getNbUtilisateurs() != 0) {
                    ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNbUtilisateurs(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs() + aleachoisi.getNbUtilisateurs());
                    txt.setText(txt.getText().toString() + "+" + aleachoisi.getNbUtilisateurs() + " utilisateurs | ");
                }
                if (aleachoisi.getSecurite() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite() < 20) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauSecurite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite() + aleachoisi.getSecurite());
                    }
                    txt.setText(txt.getText().toString() + "+" + aleachoisi.getSecurite() + " sécurité | ");
                }
                if (aleachoisi.getErgonomie() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie() < 20) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauErgonomie(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie() + aleachoisi.getErgonomie());
                    }
                    txt.setText(txt.getText().toString() + "+" + aleachoisi.getErgonomie() + " ergonomie | ");
                }
                if (aleachoisi.getPuissance() != 0) {
                    if ( ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance() < 20) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauPuissance(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance() + aleachoisi.getPuissance());
                    }
                    txt.setText(txt.getText().toString() + "+" + aleachoisi.getPuissance() + " puissance | ");
                }
                if (aleachoisi.getRentabilite() != 0 ) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite() < 20) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauRentabilite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite() + aleachoisi.getRentabilite());
                    }
                    txt.setText(txt.getText().toString() + "+" + aleachoisi.getRentabilite() + " rentabilité | ");
                }

            } else if (aleachoisi.getType().equals("mauvais")) {

                if (aleachoisi.getArgent() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getArgentEntreprise() >= aleachoisi.getArgent()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().setArgentEntreprise(((MyApplication) this.getApplication()).getEntreprise_joueur().getArgentEntreprise() - aleachoisi.getArgent());
                    }
                    txt.setText(txt.getText().toString() + "-" + aleachoisi.getArgent() + "€ | ");
                }
                if (aleachoisi.getNbUtilisateurs() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs() >= aleachoisi.getNbUtilisateurs()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNbUtilisateurs(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs() - aleachoisi.getNbUtilisateurs());
                    }
                    txt.setText(txt.getText().toString() + "-" + aleachoisi.getNbUtilisateurs() + " utilisateurs | ");
                }
                if (aleachoisi.getSecurite() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite() >= aleachoisi.getSecurite()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauSecurite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite() - aleachoisi.getSecurite());
                    }
                    txt.setText(txt.getText().toString() + "-" + aleachoisi.getSecurite() + " sécurité | ");
                }
                if (aleachoisi.getErgonomie() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie() >= aleachoisi.getErgonomie()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauErgonomie(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie() - aleachoisi.getErgonomie());
                    }
                    txt.setText(txt.getText().toString() + "-" + aleachoisi.getErgonomie() + " ergonomie | ");
                }
                if (aleachoisi.getPuissance() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance() >= aleachoisi.getPuissance()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauPuissance(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance() - aleachoisi.getPuissance());
                    }
                    txt.setText(txt.getText().toString() + "-" + aleachoisi.getPuissance() + " puissance | ");
                }
                if (aleachoisi.getRentabilite() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite() >= aleachoisi.getRentabilite()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauRentabilite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite() - aleachoisi.getRentabilite());
                    }
                    txt.setText(txt.getText().toString() + "-" + aleachoisi.getRentabilite() + " rentabilité | ");
                }
            }

            dialog.show();
            //Appel au popup
        }

        saveContext();
    }

    public void saveContext() {
        mdb.getAppDatabase().entreprisepersodao().update(((MyApplication)this.getApplication()).getEntreprise_joueur());

        for (int i = 0; i < ((MyApplication)this.getApplication()).getConcurrents().size(); i++) {
            ((MyApplication)this.getApplication()).getConcurrents().get(i).setNbusers(((MyApplication)this.getApplication()).getConcurrents().get(i).getLogiciel().getNbUtilisateurs());
            mdb.getAppDatabase().entreprisedao().update(((MyApplication)this.getApplication()).getConcurrents().get(i));
        }

        mdb.getAppDatabase().logicieldao().update(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel());

        LoadDataEntreprise();
    }

    public void fermerbox(View view) {
        dialog.cancel();
    }
}
