package com.example.alice.stockage.Vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.alice.stockage.BDD.DataBaseClass;
import com.example.alice.stockage.R;

public class Accueil extends AppCompatActivity {

    String FILENAME = "stockage3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        DataBaseClass.initInstance(getApplicationContext()); // connexion à la base SQLite
    }

    public void onClickAjout(View v)
    {
        Intent changement = new Intent(this, Ajout.class);
        changement.putExtra("fichier", FILENAME);
        startActivity(changement);
    }

    public void onClickVisu(View v)
    {
        Intent direction = new Intent(this, ListeContact.class);
        direction.putExtra("fichier", FILENAME);
        startActivity(direction);
    }

    public void onClickRecherche(View v)
    {
        Intent changement = new Intent(this, Rechercher.class);
        startActivity(changement);
    }
}
