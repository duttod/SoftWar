package com.example.softwar.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.softwar.MyApplication;
import com.example.softwar.R;
import com.example.softwar.data.DatabaseClient;
import com.example.softwar.data.Employe;
import com.example.softwar.data.EntreprisePerso;

import java.util.List;

public class ListeEmployeAdapter extends ArrayAdapter<Employe> {
    public ListeEmployeAdapter(@NonNull Context mCtx, List<Employe> employeList) {
        super(mCtx, R.layout.template_list_employe, employeList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Récupération
        final  Employe emp = getItem(position);

        // Charge le template XML
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.template_list_employe,parent,false);


        // Récupération des objets graphique dans le template
        TextView textViewNom = (TextView) rowView.findViewById(R.id.nom_employe);
        TextView textViewPrenom = (TextView) rowView.findViewById(R.id.prenom_employe);
        TextView textViewRarete = (TextView) rowView.findViewById(R.id.rarete_employe);
        TextView textViewAge = (TextView) rowView.findViewById(R.id.age_employe);
        TextView textViewProductivite = (TextView) rowView.findViewById(R.id.productivite_employe) ;
        TextView textViewRapidite = (TextView) rowView.findViewById(R.id.rapidite_employe);
        TextView textViewQuantite = (TextView) rowView.findViewById(R.id.quantite_employe);

        // Remplir les objet graphique avec les bonnes valeurs
        textViewNom.setText(emp.getNomEmploye());
        textViewPrenom.setText(emp.getPrenomEmploye());
        textViewRarete.setText(Integer.toString(emp.getRarete())); // Erreur possible : cast en String !
        textViewAge.setText(Integer.toString(emp.getAgeEmploye()));
        textViewProductivite.setText(Integer.toString(emp.getProductivite()));
        textViewRapidite.setText(Integer.toString(emp.getRapidite()));
//        textViewQuantite.setText();
       /*Récupérer les valeurs de la table asso pour ça !*/


        // A TESTER ! Vraiment pas beau !
        EntreprisePerso entreprisePerso = ((MyApplication)this.getContext()).getEntreprise_joueur();
        DatabaseClient mdb = DatabaseClient.getInstance(getContext());
        textViewQuantite.setText(Integer.toString(mdb.getAppDatabase().employeDansEntrepriseDao().getUnEmployeDuneEntreprise(entreprisePerso.getNomEntreprise(),emp.getId()).getQuantite()));

        return rowView;
    }
}
