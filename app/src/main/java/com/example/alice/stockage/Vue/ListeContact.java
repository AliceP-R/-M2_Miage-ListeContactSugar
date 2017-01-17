package com.example.alice.stockage.Vue;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.alice.stockage.Donnees.Personne;
import com.example.alice.stockage.Personnalisation.ContactAdapter;
import com.example.alice.stockage.R;

import java.util.ArrayList;

public class ListeContact extends ListActivity {

    private ArrayList lPersonne;
    private ContactAdapter adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contact);
        lPersonne = new ArrayList<>();
        lPersonne = (ArrayList) Personne.listAll(Personne.class);
        adapterContact = new ContactAdapter(this, lPersonne);
        setListAdapter(adapterContact);
    }

    public void onClickRetour(View v)
    {
        Intent annulation = new Intent(this, Accueil.class);
        startActivity(annulation);
    }


}
