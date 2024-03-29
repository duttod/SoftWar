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

            db.execSQL("INSERT INTO Jeu (nom,dureeJeu,description) VALUES(\"Resolutiondecode\",0,\"Complète le code pour gagner des récompenses\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,0,0,\"mauvais\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(2500,1000,0,0,0,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,300,5,0,0,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(400,0,0,5,0,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,1,0,4,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,100,1,2,1,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(300,200,0,0,2,0,\"assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(500,200,1,0,1,0,\"assezbon\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(5000,2500,0,0,0,0,\"bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,10,0,0,0,\"bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,10,0,0,\"bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,10,0,\"bon\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(5000,5000,2,2,1,1,\"excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(4250,2100,4,3,0,0,\"excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(1000,2000,0,4,3,0,\"excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(3000,1000,3,3,3,3,\"excellent\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,0,0,0,0,0,\"mauvais\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(500,1000,0,0,0,1,\"attaquer-assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(500,1000,0,0,1,0,\"attaquer-assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(500,1000,0,1,0,0,\"attaquer-assezbon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(500,1000,1,0,0,0,\"attaquer-assezbon\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(500,2500,0,1,0,0,\"attaquer-bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(1000,1000,0,1,0,1,\"attaquer-bon\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(0,3000,0,2,0,1,\"attaquer-bon\");");

            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(5000,5000,0,1,0,0,\"attaquer-excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(2000,2000,1,2,2,1,\"attaquer-excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(1500,2420,2,0,2,2,\"attaquer-excellent\");");
            db.execSQL("INSERT INTO ResultatJeu (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,resultat) VALUES(1950,900,1,3,1,2,\"attaquer-excellent\");");


            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(0,2500,0,0,0,0,\"Votre campagne de promotion a attiré de riches investisseurs !\",\"bien\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(0,0,5,0,0,0,\"Votre logiciel a été adopté par une grande compagnie pharmaceutique\",\"bien\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(2000,2000,0,0,0,0,\"Un acteur renommée fait la promotion de votre logiciel !\",\"bien\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(4000,0,0,0,0,0,\"Un de vos concurrents a fait une campagne massive d'e-mailing auprès de vos utilisateurs !\",\"bien\");");

            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(0,5000,0,10,0,0,\"Un de vos employés a mis un 0 en trop sur une facture !\",\"mauvais\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(0,0,1,5,1,0,\"Un de vos concurrents a DDOS vos serveurs durant la nuit !\",\"mauvais\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(0,0,2,5,2,0,\"Un de vos concurrents a réalisé une injection SQL sur votre site !\",\"mauvais\");");
            db.execSQL("INSERT INTO Alea (nbUtilisateurs,argent,rentabilite,securite,puissance,ergonomie,contexte,type) VALUES(0,3000,3,0,0,5,\"Un hacker a redirigé la homepage de votre site sur le sien !\",\"mauvais\");");

            db.execSQL("INSERT INTO Employe(nom,prenom,age,productivite,rapidite,rarete) VALUES(\"Flex\", \"Fabieng\",44,17,19,3);");
            db.execSQL("INSERT INTO Employe(nom,prenom,age,productivite,rapidite,rarete) VALUES(\"Goudja\", \"Célestine\",44,15,15,2);");
            db.execSQL("INSERT INTO Employe(nom,prenom,age,productivite,rapidite,rarete) VALUES(\"Royale\", \"Vaness\",50,7,10,1);");
            db.execSQL("INSERT INTO Employe(nom,prenom,age,productivite,rapidite,rarete) VALUES(\"Petit\", \"Corenting\",12,3,10,1);");
        }
    };

}