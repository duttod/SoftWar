package com.example.softwar.controllers;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    private Dialog dialog,dialogcompteur,dialogftour,dial_erreur_ftour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        dialog = new Dialog(MainActivity.this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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
                saveContext();

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
        argent.setText("Argent : "+Long.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getArgentEntreprise()));
        nomE.setText("Entreprise : "+((MyApplication)this.getApplication()).getEntreprise_joueur().getNomEntreprise());
        nbuser.setText("Utilisateurs : "+Integer.toString(((MyApplication)this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs()));
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
        if(MyApplication.getInstance().getCompteur_action()<=0){

            dialogcompteur = new Dialog(this);
            dialogcompteur.setContentView(R.layout.popup_compteur_actions);
            dialogcompteur.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialogcompteur.show();
        }else{

        Intent intent = new Intent(this,ChooseRenforcerAttaquerActivity.class);
        startActivity(intent);
        }
    }

    public void GoToStats(View view) {
        Intent intent = new Intent(this,Statistiques.class);
        startActivity(intent);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        LoadConcurrents();
    }

    //PARTIE A PASSER EN ASYNCHRONE SI POSSIBLE

    public void FinTour(View view) {
        if(MyApplication.getInstance().getCompteur_action()==0){
            BilanTour();
        }else{
            dial_erreur_ftour = new Dialog(this);
            dial_erreur_ftour.setContentView(R.layout.popup_erreur_ftour);
            dial_erreur_ftour.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dial_erreur_ftour.show();
        }


    }

    public void RandomEvenementDebutTour(View view) {

        MyApplication.getInstance().setCompteur_action(2);
        int chanceevenement = (int) (Math.random() * (100 - 0));
        TextView txt = dialog.findViewById(R.id.description_alea);
        txt.setText("");
        txt.setTextColor(Color.parseColor("#FFB900"));
        txt.setTypeface(ResourcesCompat.getFont(this, R.font.nasalization));

        if (chanceevenement <= 50) {

            List<Alea> aleas = mdb.getAppDatabase().aleadao().getAll();
            int indice = (int) (Math.random() * ((aleas.size()) - 0));

            Alea aleachoisi = aleas.get(indice);
            txt.setText("Evènement : "+aleachoisi.getContexte());

            if (aleachoisi.getType().equals("bien")) {

                if (aleachoisi.getArgent() != 0) {
                    ((MyApplication) this.getApplication()).getEntreprise_joueur().setArgentEntreprise(((MyApplication) this.getApplication()).getEntreprise_joueur().getArgentEntreprise() + aleachoisi.getArgent());
                    txt.setText(txt.getText() + " | +" + aleachoisi.getArgent() + "€");
                }
                if (aleachoisi.getNbUtilisateurs() != 0) {
                    ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNbUtilisateurs(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs() + aleachoisi.getNbUtilisateurs());
                    txt.setText(txt.getText() + " | +" + aleachoisi.getNbUtilisateurs() + " utilisateurs ");
                }
                if (aleachoisi.getSecurite() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite()+aleachoisi.getSecurite() < 100) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauSecurite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite() + aleachoisi.getSecurite());
                    } else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauSecurite(100);
                    }
                    txt.setText(txt.getText() + " | +" + aleachoisi.getSecurite() + " sécurité ");
                }
                if (aleachoisi.getErgonomie() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie()+aleachoisi.getErgonomie() < 100) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauErgonomie(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie() + aleachoisi.getErgonomie());
                    } else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauErgonomie(100);
                    }
                    txt.setText(txt.getText() + " | +" + aleachoisi.getErgonomie() + " ergonomie ");
                }
                if (aleachoisi.getPuissance() != 0) {
                    if ( ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance()+aleachoisi.getPuissance() < 100) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauPuissance(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance() + aleachoisi.getPuissance());
                    } else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauPuissance(100);
                    }
                    txt.setText(txt.getText() + "| +" + aleachoisi.getPuissance() + " puissance ");
                }
                if (aleachoisi.getRentabilite() != 0 ) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite()+aleachoisi.getRentabilite() < 100) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauRentabilite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite() + aleachoisi.getRentabilite());
                    } else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauRentabilite(100);
                    }
                    txt.setText(txt.getText() + " | +" + aleachoisi.getRentabilite() + " rentabilité ");
                }

            } else {

                System.out.println("Passe");

                if (aleachoisi.getArgent() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getArgentEntreprise() >= aleachoisi.getArgent()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().setArgentEntreprise(((MyApplication) this.getApplication()).getEntreprise_joueur().getArgentEntreprise() - aleachoisi.getArgent());
                    } else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().setArgentEntreprise(0);
                    }
                    txt.setText(txt.getText() + " | -" + aleachoisi.getArgent() + "€ ");
                }
                if (aleachoisi.getNbUtilisateurs() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs() >= aleachoisi.getNbUtilisateurs()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNbUtilisateurs(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNbUtilisateurs() - aleachoisi.getNbUtilisateurs());
                    } else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNbUtilisateurs(0);
                    }
                    txt.setText(txt.getText() + " | -" + aleachoisi.getNbUtilisateurs() + " utilisateurs ");
                }
                if (aleachoisi.getSecurite() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite() >= aleachoisi.getSecurite()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauSecurite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauSecurite() - aleachoisi.getSecurite());
                    } else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauSecurite(0);
                    }
                    txt.setText(txt.getText() + " | -" + aleachoisi.getSecurite() + " sécurité ");
                }
                if (aleachoisi.getErgonomie() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie() >= aleachoisi.getErgonomie()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauErgonomie(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauErgonomie() - aleachoisi.getErgonomie());
                    }else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauErgonomie(0);
                    }
                    txt.setText(txt.getText() + " | -" + aleachoisi.getErgonomie() + " ergonomie ");
                }
                if (aleachoisi.getPuissance() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance() >= aleachoisi.getPuissance()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauPuissance(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauPuissance() - aleachoisi.getPuissance());
                    }else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauPuissance(0);
                    }
                    txt.setText(txt.getText() + " | -" + aleachoisi.getPuissance() + " puissance ");
                }
                if (aleachoisi.getRentabilite() != 0) {
                    if (((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite() >= aleachoisi.getRentabilite()) {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauRentabilite(((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().getNiveauRentabilite() - aleachoisi.getRentabilite());
                    }else {
                        ((MyApplication) this.getApplication()).getEntreprise_joueur().getLogiciel().setNiveauRentabilite(0);
                    }
                    txt.setText(txt.getText() + " | -" + aleachoisi.getRentabilite() + " rentabilité ");

                }
            }

            dialog.show();
            //Appel au popup
        } else {
            txt.setText("Pas d'évènement aujourd'hui !");
            dialog.show();
        }

        ((MyApplication) this.getApplication()).addTour();
        UpgradeConcurents();
        saveContext();
        LoadDataEntreprise();
    }

    private void UpgradeConcurents() {
        getConcurrents();
        for(Entreprise c : concurrents){
            Logiciel l = c.getLogiciel();
            int somme = (l.getNiveauErgonomie()*20)+(l.getNiveauLogiciel()*20)+(l.getNiveauPuissance()*20)+(l.getNiveauRentabilite()*120+6000);
            int nbuserg =l.getNiveauErgonomie()*530+2000+(l.getNbUtilisateurs()/100);
            c.setArgentEntreprise(c.getArgentEntreprise()+somme);
            c.getLogiciel().setNbUtilisateurs(l.getNbUtilisateurs()+nbuserg);
            l.setNiveauErgonomie(l.getNiveauErgonomie()+1);
            l.setNiveauPuissance(l.getNiveauPuissance()+1);
            l.setNiveauSecurite(l.getNiveauSecurite()+1);
            l.setNiveauRentabilite(l.getNiveauRentabilite()+2);
        }
    }

    public void BilanTour() {

        int numerotour =((MyApplication) this.getApplication()).getTour();

        //Traitements : distribuer argent + utilisateurs en fonction des stats du logiciel
        Logiciel l = entreprise_joueur.getLogiciel();
        int somme = (l.getNiveauErgonomie()*20)+(l.getNiveauLogiciel()*20)+(l.getNiveauPuissance()*20)+(l.getNiveauRentabilite()*100+4000);
        int nbuserg =l.getNiveauErgonomie()*500+2000+(l.getNbUtilisateurs()/100);
        entreprise_joueur.setArgentEntreprise(entreprise_joueur.getArgentEntreprise()+somme);
        entreprise_joueur.setNbContrats(entreprise_joueur.getNbContrats()+1);
        entreprise_joueur.getLogiciel().setNbUtilisateurs(l.getNbUtilisateurs()+nbuserg);



        //Affichage des res dans un popup
        dialogftour = new Dialog(this);
        dialogftour.setContentView(R.layout.layout_fin_tour);
        TextView targent = dialogftour.findViewById(R.id.argentfintour);
        targent.setText("Vous recevez "+Integer.toString(somme)+"€");
        TextView tuser = dialogftour.findViewById(R.id.utilisateursfintour);
        tuser.setText("Vous recevez "+Integer.toString(nbuserg)+" utilisateurs");
        TextView tcompteur = dialogftour.findViewById(R.id.compteurtour);
        tcompteur.setText("Vous avez finis le tour:"+Integer.toString(numerotour));
        dialogftour.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogftour.show();
        ((MyApplication) this.getApplication()).addTour();
        
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

    public void fermerbilanbox(View view) {
        dialogftour.cancel();
        RandomEvenementDebutTour(view);
    }

    public void fermercompteurbox(View view) {
        dialogcompteur.cancel();
    }

    public void fermerdialerreur(View view) {
        dial_erreur_ftour.cancel();
    }
}
