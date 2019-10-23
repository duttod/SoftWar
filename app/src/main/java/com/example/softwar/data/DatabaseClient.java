package com.example.softwar.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.Room.databaseBuilder;

public class DatabaseClient {

    // Instance unique permettant de faire le lien avec la base de données
    private static DatabaseClient instance;

    // Objet représentant la base de données de votre application
    private AppDatabase appDatabase;

    // Constructeur
    private DatabaseClient(final Context context) {

        // Créer l'objet représentant la base de données de votre application
        // à l'aide du "Room database builder"
        // MyToDos est le nom de la base de données
        //appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyToDos").build();

        // Ajout de la méthode addCallback permettant de populate (remplir) la base de données à sa création
        appDatabase = databaseBuilder(context, AppDatabase.class, "Softwar_db").addCallback(roomDatabaseCallback).allowMainThreadQueries().build();
    }

    // Méthode statique
    // Retourne l'instance de l'objet DatabaseClient
    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    // Retourne l'objet représentant la base de données de votre application
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    // Objet permettant de populate (remplir) la base de données à sa création
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {

        // Called when the database is created for the first time.
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            db.execSQL("INSERT INTO Jeu (nom,dureeJeu,description) VALUES(\"Resolution de code\",0,\"Complète le code pour gagner des récompenses\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,0,0,\"mauvais\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(2500,1000,0,0,0,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,1,0,0,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,1,0,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,1,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,0,1,\"assezbon\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(5000,2500,0,0,0,0,\"bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,1,0,1,0,\"bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,1,0,1,0,0,\"bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,1,1,\"bon\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(10000,5000,0,0,0,0,\"excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,2,0,1,0,\"excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,1,0,2,0,0,\"excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,2,1,\"excellent\");");

            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(15000,0,0,0,0,0,\"Votre campagne de promotion a attiré de riches investisseurs !\",\"bien\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(0,0,0,-1,0,0,\"Un de vos concurrents a DDOS vos serveurs durant la nuit !\",\"mauvais\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(-3000,1,0,2,0,0,\"Un de vos concurrents a fait une campagne massive d'e-mailing auprès de vos utilisateurs !\",\"bien\");");

            db.execSQL("INSERT INTO Employe(nom,prenom,age,productivite,rapidite,rarete) VALUES(\"Gérot\", \"Cédric\",44,17,19,3);");
            db.execSQL("INSERT INTO Employe(nom,prenom,age,productivite,rapidite,rarete) VALUES(\"Ceret\", \"Eric\",44,15,15,2);");
            db.execSQL("INSERT INTO Employe(nom,prenom,age,productivite,rapidite,rarete) VALUES(\"Blancool\", \"Gaelle\",50,7,10,1);");
        }
    };

}