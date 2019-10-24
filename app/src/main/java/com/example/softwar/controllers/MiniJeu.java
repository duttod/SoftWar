package com.example.softwar.controllers;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.List;

public class MiniJeu extends AppCompatActivity {

    TextView titre;
    LinearLayout layout_text;
    LinearLayout layout_reponses;
    Pattern pattern;
    TextView chrono;
    CountDownTimer countDownTimer;
    String action_demander;
    int indice;
    int nb_bonnerep;
    int nb_mauvaiserep;

    private DatabaseClient mDb;
    private Jeu jeu_en_cours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_jeu);
        getSupportActionBar().hide();

        action_demander = this.getIntent().getStringExtra(ChooseRenforcerAttaquerActivity.ACTION_KEY);
        System.out.println(action_demander);
        titre = findViewById(R.id.minijeu_titre);
        layout_text = findViewById(R.id.layout_text);
        layout_reponses = findViewById(R.id.layout_reponses);
        pattern = new Pattern();
        initPattern1_1();

        nb_bonnerep = 0;
        nb_mauvaiserep = 0;
        indice = 0;

        mDb = DatabaseClient.getInstance(getApplicationContext());

        InitJeu();

    }
    public void initPattern1_1(){
        /*  int i=1;
            while(i<=10){
                System.out.println(i);
                i++;
            }
        */
        LinearLayout l1 = initLigne();
        initText("1-  int ", l1);
        initReponse("i",l1);
        initText("=1;", l1);
        LinearLayout l2 = initLigne();
        initText("2-  ", l2);
        initReponse("while", l2);
        initText("(i<=10){", l2);
        LinearLayout l3 = initLigne();
        initText("3-        System.out.println(i);", l3);
        LinearLayout l4 = initLigne();
        initText("4-        ", l4);
        initReponse("i++", l4);
        initText(";", l4);
        LinearLayout l5 = initLigne();
        initText("5-  }", l5);
        LinearLayout l6 = initLigne();
        initText("  ",l6);

    }
    public void initText(String text, LinearLayout layout){
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(26);
        tv.setTextColor(Color.parseColor("#FFB900"));
        tv.setTypeface(ResourcesCompat.getFont(this, R.font.nasalization));
        layout.addView(tv);
    }
    public void initReponse(String text, LinearLayout layout){
        TextView tv1 = new TextView(this);

        //LinearLayout l = new LinearLayout(this);
        //l.addView(tv1);
        tv1.setTextSize(26);

        tv1.setText("       ");
        tv1.setBackgroundColor(Color.YELLOW);

        tv1.setId(indice);
        indice++;
        //et.setEnabled(false);
        tv1.setOnDragListener(new MyDragListener());
        layout.addView(tv1);

        pattern.addBonneReponse(text);
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(26);
        tv.setTextColor(Color.parseColor("#FF22CD"));
        tv.setTypeface(ResourcesCompat.getFont(this, R.font.nasalization));
        tv.setOnTouchListener(new MyTouchListener());
        layout_reponses.addView(tv);
    }

    public LinearLayout initLigne(){
        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.HORIZONTAL);
        this.layout_text.addView(l1);
        return l1;
    }

    public void valider_minijeu(View view) {
        Intent intent = new Intent(this, ResultatMiniJeu.class);
        intent.putExtra(ResultatMiniJeu.nbjuste, nb_bonnerep);
        intent.putExtra(ResultatMiniJeu.nbpossible, pattern.getBonnesReponses().size());
        Toast.makeText(this, "blblbl", Toast.LENGTH_LONG).show();
        countDownTimer.cancel();
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
                        if(tag!=null)
                        {
                            //the tag is the view id already dropped here
                            int existingID = (Integer)tag;
                            //set the original view visible again
                            findViewById(existingID).setVisibility(View.VISIBLE);
                        }

                        nb_bonnerep = 0;
                        nb_mauvaiserep = 0;

                       for (int i = 0; i < pattern.getBonnesReponses().size(); i++) {
                               TextView t = (TextView) findViewById(i);
                               if(pattern.getBonnesReponses().get(i).equals(t.getText().toString())){
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // Fais ton traitement
        }
        return true;
    }

    private void InitJeu() {
        ///////////////////////
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
                countDownTimer = new CountDownTimer(15000, 1000) {

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

    /*
    	public void getRecompenses(EntreprisePerso p, DatabaseClient mdb) {

		int indicemin = 0;
		int indicemax = mdb.getAppDatabase().resultatjeudao().getAll().size();

		int indice = (int) (Math.random() * ( indicemax - indicemin ));

		ResultatJeu recompense = mdb.getAppDatabase().resultatjeudao().getAll().get(indice);

	}
     */
