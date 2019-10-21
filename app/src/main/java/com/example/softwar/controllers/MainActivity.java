package com.example.softwar.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;

public class MainActivity extends AppCompatActivity {

    private DatabaseClient mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdb = DatabaseClient.getInstance(getApplicationContext());
    }


}
