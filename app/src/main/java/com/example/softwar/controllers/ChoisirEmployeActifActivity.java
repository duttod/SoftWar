package com.example.softwar.controllers;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
        listEmp = findViewById(R.id.listEmploye);

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

    private void getEmp(){
        class GetEmp extends AsyncTask<Void, Void, List<Employe>>{

            @Override
            protected List<Employe> doInBackground(Void... voids) {
                List<Employe> employeList = mDb.getAppDatabase()
                        .employeDao() // ne pas récupếrer dans la bdd mais dans entreprise !
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
