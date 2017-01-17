package com.example.alice.stockage.Vue;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alice.stockage.Donnees.Personne;
import com.example.alice.stockage.Personnalisation.ContactAdapter;
import com.example.alice.stockage.R;

import java.util.ArrayList;
import java.util.Iterator;

public class Rechercher extends AppCompatActivity {

    private ArrayList lPersonne;
    private ArrayList lRes;
    private ContactAdapter adapterContact;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher);
        lPersonne = new ArrayList<>();
        lRes = new ArrayList();
        adapterContact = new ContactAdapter(this, lPersonne);
        lv = (ListView) findViewById(R.id.lview);
        lv.setAdapter(adapterContact);
    }

    public void onClickRecherche(View v)
    {
        try
        {

            lPersonne.clear();
            EditText recherche=(EditText) findViewById(R.id.txtRecherche); // Recherche
            lRes = (ArrayList) Personne.find(Personne.class, "nom LIKE '%"
                    + recherche.getText() + "' OR prenom LIKE '%" + recherche.getText()+"'" );

            for (Personne lRe : (Iterable<Personne>) lRes) {
                lPersonne.add(lRe);
            }

            System.out.println(lPersonne.toString());

            adapterContact.notifyDataSetChanged();
        }
        catch (Exception e){
            Log.e("Main : ", e.toString());
        }
    }

    public void onClickRetour(View v)
    {
        Intent annulation = new Intent(this, Accueil.class);
        startActivity(annulation);
    }
}
