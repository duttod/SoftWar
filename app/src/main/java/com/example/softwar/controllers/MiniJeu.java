package com.example.softwar.controllers;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
        initPattern1_1();

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
        LinearLayout l2 = initLigne();
        initReponse("while", l2);
        initText("(i<=10){", l2);
        LinearLayout l3 = initLigne();
        initText("      System.out.println(i);", l3);
        LinearLayout l4 = initLigne();
        initText("      ", l4);
        initReponse("i++", l4);
        initText(";", l4);
        LinearLayout l5 = initLigne();
        initText("}", l5);


    }
    public void initText(String text, LinearLayout layout){
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(26);
        layout.addView(tv);
    }
    public void initReponse(String text, LinearLayout layout){
        TextView tv1 = new EditText(this);

        //LinearLayout l = new LinearLayout(this);
        //l.addView(tv1);
        tv1.setTextSize(26);
        tv1.setText("       ");
        tv1.setBackgroundColor(Color.DKGRAY);
        //et.setEnabled(false);
        tv1.setOnDragListener(new MyDragListener());
        layout.addView(tv1);

        pattern.addBonneReponse(text);
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(26);
        tv.setOnTouchListener(new MyTouchListener());
        layout_reponses.addView(tv);
    }
    public LinearLayout initLigne(){
        LinearLayout l1 = new LinearLayout(this);
        l1.setOrientation(LinearLayout.HORIZONTAL);
        this.layout_text.addView(l1);
        return l1;
    }


    // This defines your touch listener
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
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
                    v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                    view.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //update the text in the target view to reflect the data being dropped
                    dropTarget.setText(dropped.getText());
                    //make it bold to highlight the fact that an item has been dropped
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    dropTarget.setOnTouchListener(new MyTouchListener());
                    Object tag = dropTarget.getTag();
                    //if there is already an item here, set it back visible in its original place
                    if(tag!=null)
                    {
                        //the tag is the view id already dropped here
                        int existingID = (Integer)tag;
                        //set the original view visible again
                        findViewById(existingID).setVisibility(View.VISIBLE);
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackgroundDrawable(normalShape);
                default:
                    break;
            }
            return true;
        }
    }
}
