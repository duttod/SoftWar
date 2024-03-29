package com.example.softwar.controllers;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.EntreprisePerso;
import com.example.softwar.data.Jeu;
import com.example.softwar.data.Logiciel;
import com.example.softwar.data.Pattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MiniJeu extends AppCompatActivity {

    TextView titre;
    LinearLayout layout_text;
    LinearLayout layout_reponses;

    Pattern pattern;

    Pattern pattern4;


    TextView chrono;
    CountDownTimer countDownTimer;
    String action_demander;
    ArrayList<TextView> reponses;
    int indice;
    int nb_bonnerep;
    int nb_mauvaiserep;

    private DatabaseClient mDb;
    private Jeu jeu_en_cours;
    private EntreprisePerso entreprise_joueur;

    private float mx, my;
    private float curX, curY;

    private ScrollView vScroll;
    private HorizontalScrollView hScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mini_jeu);
        getSupportActionBar().hide();

        entreprise_joueur = ((MyApplication)this.getApplication()).getEntreprise_joueur();

        action_demander = this.getIntent().getStringExtra(ChooseRenforcerAttaquerActivity.ACTION_KEY);
        System.out.println(action_demander);
        titre = findViewById(R.id.minijeu_titre);
        layout_text = findViewById(R.id.layout_text);
        layout_reponses = findViewById(R.id.layout_reponses);
        reponses = new ArrayList<>();

        pattern = new Pattern();

        int alea = new Random().nextInt(3);
        if(alea == 0){
            initPattern1_1();
        }else if(alea ==1){
            initPattern1_2();
        }else if( alea ==3){
            initPattern1_3();
        }else initPattern1_4();


        nb_bonnerep = 0;
        nb_mauvaiserep = 0;
        indice = 0;

        mDb = DatabaseClient.getInstance(getApplicationContext());

        vScroll = (ScrollView) findViewById(R.id.vScroll);
        hScroll = (HorizontalScrollView) findViewById(R.id.hScroll);

        InitJeu();

    }
    public void initPattern1_1(){

        LinearLayout l1 = initLigne();
        initText("1- ", l1);
        initReponse("int i",l1,pattern);
        initText("=1;", l1);
        LinearLayout l2 = initLigne();
        initText("2-  ", l2);
        initReponse("while", l2,pattern);
        initText("(i<=10){", l2);
        LinearLayout l3 = initLigne();
        initText("3-        System.out.println(i);", l3);
        LinearLayout l4 = initLigne();
        initText("4-        ", l4);
        initReponse("i++", l4,pattern);
        initText(";", l4);
        LinearLayout l5 = initLigne();
        initText("5-  }", l5);
        LinearLayout l6 = initLigne();
        initText("  ",l6);
        affiche_reponses();

    }

    public void initPattern1_2(){

        LinearLayout l1 = initLigne();
        initText("1- public abstract ", l1);
        initReponse("class",l1,pattern);
        initText(" Voiture {;", l1);
        LinearLayout l2 = initLigne();
        initText("2-    ", l2);
        initReponse("String", l2,pattern);
        initText(" libelle;", l2);
        LinearLayout l3 = initLigne();
        initText("3-    ", l3);
        initReponse("int", l3,pattern);
        initText(" prix;", l3);
        LinearLayout l4 = initLigne();
        initText("4-   int ", l4);
        initReponse("prix", l4,pattern);
        initText(";", l4);
        LinearLayout l5 = initLigne();
        initText("5- ", l5);
        LinearLayout l6 = initLigne();
        initText("6-   public String getLibelle() { ", l6);
        initReponse("return", l6,pattern);
        initText(" libelle; }", l6);
        LinearLayout l7 = initLigne();
        initText("7-    public int getPrix() { return prix; } ", l7);
        LinearLayout l8 = initLigne();
        initText("8-    public int getPoids() { return ", l8);
        initReponse("poids", l8,pattern);
        initText("; }", l8);
        LinearLayout l9 = initLigne();
        initText("9- ", l9);
        LinearLayout l10 = initLigne();
        initText("10-    protected ", l10);
        initReponse("void", l10,pattern);
        initText(" setLibelle(String libelle) { this.libelle = libelle; }", l10);
        LinearLayout l11 = initLigne();
        initText("11-    protected void setPrix(int prix) { this.prix = prix; }", l11);
        LinearLayout l12 = initLigne();
        initText("12-    protected void setPrix(int prix) { ", l12);
        initReponse("this.", l12,pattern);
        initText("prix = prix }", l12);
        LinearLayout l13 = initLigne();
        initText("13- ", l13);
        LinearLayout l14 = initLigne();
        initText("14-    public String ", l14);
        initReponse("toString() ", l14,pattern);
        initText("{ return \"Voiture : \" + getLibelle() + \", Prix : \" + getPrix() + \", Poids : \" + getPoids(); }", l14);
        LinearLayout l15 = initLigne();
        initText("15-  }", l15);
        LinearLayout l16 = initLigne();

        initText(" ", l15);


        initText(" ", l16);
        affiche_reponses();
    }
    public void initPattern1_3(){
        /*public static void main(String[] args) {

            int j = 20, i = 0;
            try {
                System.out.println(j/i);
            } catch (ArithmeticException e) {
                System.out.println("Division par zéro !");
            }
            System.out.println("coucou toi !");
        }*/
        LinearLayout l1 = initLigne();
        initText("1- public static ", l1);
        initReponse("void",l1,pattern);
        initText(" main(String[] args) {", l1);
        LinearLayout l2 = initLigne();
        initText("2-    ", l2);
        LinearLayout l3 = initLigne();
        initText("3-    ", l3);
        initReponse("int", l3,pattern);
        initText(" j = 20, i = 0;", l3);
        LinearLayout l4 = initLigne();
        initText("4-   ", l4);
        initReponse("try", l4,pattern);
        initText(" {", l4);
        LinearLayout l5 = initLigne();
        initText("5-    System.", l5);
        initReponse("out.", l5, pattern);
        initText("println(\"Division par zéro !\");",l5);
        LinearLayout l6 = initLigne();
        initText("6-   }", l6);
        initReponse("catch", l6,pattern);
        initText(" (ArithmeticException e) {", l6);
        LinearLayout l7 = initLigne();
        initText("7- }", l7);
        LinearLayout l8 = initLigne();
        initText("8-", l8);
        initReponse("System.", l8,pattern);
        initText("out.println(\"coucou toi !\");", l8);
        LinearLayout l9 = initLigne();
        initText("9- } ", l9);
        LinearLayout l10 = initLigne();

        initText(" ", l10);
        affiche_reponses();
    }

    public void initPattern1_4(){

        LinearLayout l1 = initLigne();
        initText("1- public class SommeTableaux {", l1);
        LinearLayout l2 = initLigne();
        initText("2-   public static void ", l2);
        initReponse("main", l2,pattern);
        initText("(String[] args) {;", l2);
        LinearLayout l3 = initLigne();
        initText("3- ", l3);
        LinearLayout l4 = initLigne();
        initText("4-     ", l4);
        initReponse("int", l4,pattern);
        initText(" indice_tableau, total ", l4);
        initReponse("= 0", l4, pattern);
        initText(";", l4);
        LinearLayout l5 = initLigne();
        initText("5-     ", l5);
        initReponse("int[]", l5,pattern);
        initText(" nb = {1,2,3,4,5,6,7,8,9};", l5);
        LinearLayout l6 = initLigne();
        initText("6- ", l6);
        LinearLayout l7 = initLigne();
        initText("7-     ", l7);
        initReponse("do", l7, pattern);
        initText(" {", l7);
        LinearLayout l8 = initLigne();
        initText("8-       total += nb[", l8);
        initReponse("indice_tableau", l8, pattern);
        initText("];", l8);
        LinearLayout l9 = initLigne();
        initText("9-       indice_tableau", l9);
        initReponse("++", l9, pattern);
        initText(";", l9);
        LinearLayout l10 = initLigne();
        initText("10-     }", l10);
        LinearLayout l11 = initLigne();
        initText("11-     while (indice_tableau < ", l11);
        initReponse("nb.length", l11,pattern);
        initText(");", l11);
        LinearLayout l12 = initLigne();
        initText(" ", l12);
        LinearLayout l13 = initLigne();
        initText("13-     System.out.", l13);
        initReponse("println", l13,pattern);
        initText("(total);", l13);
        LinearLayout l14 = initLigne();
        initText("14- ", l14);
        LinearLayout l15 = initLigne();
        initText("15 -   }", l15);
        LinearLayout l16 = initLigne();
        initText("16- }", l16);
        LinearLayout l17 = initLigne();

        initText(" ", l17);
        initText(" ", l17);
        affiche_reponses();
    }

    public void initText(String text, LinearLayout layout){
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFB900"));
        tv.setTypeface(ResourcesCompat.getFont(this, R.font.inconsolata));
        layout.addView(tv);
    }
    public void initReponse(String text, LinearLayout layout, Pattern pattern){
        TextView tv1 = new TextView(this);

        //LinearLayout l = new LinearLayout(this);
        //l.addView(tv1);
        tv1.setTextSize(20);

        tv1.setText("       ");
        tv1.setBackgroundColor(Color.YELLOW);

        tv1.setId(indice);
        indice++;
        //et.setEnabled(false);
        tv1.setOnDragListener(new MyDragListener());
        layout.addView(tv1);

        pattern.addBonneReponse(text);
        TextView tv = new TextView(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10,0,10,0);
        tv.setLayoutParams(params);

        tv.setText(text);
        tv.setTextSize(26);
        tv.setTextColor(Color.parseColor("#FF22CD"));
        tv.setTypeface(ResourcesCompat.getFont(this, R.font.inconsolata));
        tv.setOnTouchListener(new MyTouchListener());
        reponses.add(tv);
    }

    public LinearLayout initLigne(){
        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.HORIZONTAL);
        this.layout_text.addView(l1);
        return l1;
    }
    public void affiche_reponses(){
        Collections.shuffle(reponses);
        for (TextView tv: reponses){
            layout_reponses.addView(tv);
        }
    }

    public void valider_minijeu(View view) {
        Intent intent = new Intent(this, ResultatMiniJeu.class);
        intent.putExtra(ChooseRenforcerAttaquerActivity.ACTION_KEY,action_demander);
        intent.putExtra(ResultatMiniJeu.nbjuste, nb_bonnerep);
        intent.putExtra(ResultatMiniJeu.nbpossible, pattern.getBonnesReponses().size());

        countDownTimer.cancel();
        startActivity(intent);
    }

    public void ABANDONNER(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        countDownTimer.cancel();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ((MyApplication)this.getApplication()).FadeOut((float) 3.0);
        ((MyApplication)this.getApplication()).mediaPlayer.pause();

        ((MyApplication)this.getApplication()).mediaPlayer = MediaPlayer.create(this, R.raw.maintheme);
        ((MyApplication)this.getApplication()).FadeIn((float) 3.0);
        ((MyApplication)this.getApplication()).mediaPlayer.setLooping(true);
        ((MyApplication)this.getApplication()).mediaPlayer.start();
        startActivity(intent);
    }


    // This defines your touch listener
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                //view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged

                    // view.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //update the text in the target view to reflect the data being dropped

                    if (dropTarget instanceof TextView) {

                        String init = dropTarget.getText().toString();

                        dropTarget.setText(dropped.getText());
                        dropTarget.setOnTouchListener(new MyTouchListener());

                        ((TextView) view).setText(init);
/*
                        if (((TextView) view).getText() == "       ") {
                            view.setBackgroundColor(Color.YELLOW);
                        }
*/
                        //make it bold to highlight the fact that an item has been dropped
                        dropTarget.setTypeface(Typeface.DEFAULT_BOLD);

                        Object tag = dropTarget.getTag();
                        //if there is already an item here, set it back visible in its original place
                        if (tag != null) {
                            //the tag is the view id already dropped here
                            int existingID = (Integer) tag;
                            //set the original view visible again
                            findViewById(existingID).setVisibility(View.VISIBLE);
                        }

                        nb_bonnerep = 0;
                        nb_mauvaiserep = 0;

                                for (int i = 0; i < pattern.getBonnesReponses().size(); i++) {
                                    TextView t = (TextView) findViewById(i);
                                    if (pattern.getBonnesReponses().get(i).equals(t.getText().toString())) {
                                        nb_bonnerep++;
                                    } else {
                                        nb_mauvaiserep++;
                                    }
                                }

                                System.out.println(nb_bonnerep);
                                System.out.println(nb_mauvaiserep);

                            }

                            break;
                            case DragEvent.ACTION_DRAG_ENDED:
                                //v.setBackgroundDrawable(normalShape);
                            default:
                                break;
                        }
                        return true;
                    }
            }



    private void InitJeu() {

        // Classe asynchrone permettant de récupérer des taches et de mettre à jour le listView de l'activité
        class InitJeu extends AsyncTask<Void, Void, Jeu> {

            @Override
            protected Jeu doInBackground(Void... voids) {
                Jeu jeu = mDb.getAppDatabase()
                        .jeuDao()
                        .getAJeu("Resolutiondecode");
                return jeu;
            }

            @Override
            protected void onPostExecute(Jeu jeu) {
                super.onPostExecute(jeu);
                jeu_en_cours = jeu;

                titre.setText(jeu.getNomJeu());

                chrono = findViewById(R.id.chrono);
                // TODO patch le temps

                double timeMiniJeu = 15000 + ((entreprise_joueur.getStatEmployeActif().get(1)*0.2)*1000);



                countDownTimer = new CountDownTimer((int)timeMiniJeu, 1000) {

                    public void onTick(long millisUntilFinished) {
                        chrono.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        chrono.setText("done!");
                        Button valider = findViewById(R.id.valider);
                        valider.performClick();
                    }
                }.start();

            }
        }

        InitJeu jeu = new InitJeu();
        jeu.execute();
    }



}
