package com.example.softwar.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.softwar.R;
import com.example.softwar.data.Employe;

import java.util.List;

public class ListeEmployeAdapter extends ArrayAdapter<Employe> {
    public ListeEmployeAdapter(@NonNull Context mCtx, List<Employe> employeList) {
        super(mCtx, R.layout.template_list_employe, employeList);
    }
}
