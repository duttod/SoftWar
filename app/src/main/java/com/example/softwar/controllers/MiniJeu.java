package com.example.softwar.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.softwar.R;
import com.example.softwar.data.Pattern;

public class MiniJeu extends AppCompatActivity {
    TextView titre;
    LinearLayout layout_text;
    LinearLayout layout_reponses;
    Pattern pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_jeu);
        titre = findViewById(R.id.minijeu_titre);
        layout_text = findViewById(R.id.layout_text);
        layout_reponses = findViewById(R.id.layout_reponses);
        pattern = new Pattern();

    }
    public void initPattern1_1(){
        /*  int i=1;
            while(i<=10){
                System.out.println(i);
                i++;
            }
        */
        LinearLayout l1 = initLigne();
        initText("int ", l1);
        initReponse("i",l1);
        initText("=1;", l1);

    }
    public void initText(String text, LinearLayout layout){
        TextView tv = new TextView(this);
        tv.setText(text);
        layout.addView(tv);
    }
    public void initReponse(String text, LinearLayout layout){
        EditText et = new EditText(this);
        layout.addView(et);
        pattern.addBonneReponse(text);
        TextView tv = new TextView(this);
        tv.setText(text);
        layout_reponses.addView(tv);
    }
    public LinearLayout initLigne(){
        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.HORIZONTAL);
        this.layout_text.addView(l1);
        return l1;
    }
}
