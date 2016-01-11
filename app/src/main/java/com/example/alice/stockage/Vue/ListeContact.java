package com.example.alice.stockage.Vue;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alice.stockage.Personnalisation.ContactAdapter;
import com.example.alice.stockage.Donnees.Personne;
import com.example.alice.stockage.Donnees.Sexe;
import com.example.alice.stockage.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

public class ListeContact extends ListActivity {

    private String FILENAME;
    private ArrayList<Personne> lPersonne;
    private ContactAdapter adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contact);
        Intent reception = getIntent();
        FILENAME = reception.getStringExtra("fichier");
        lPersonne = new ArrayList<>();
        lireFichier();
        adapterContact = new ContactAdapter(this, lPersonne);
        setListAdapter(adapterContact);
    }

    private void lireFichier()
    {
        Properties prop = new Properties();

        FileInputStream in = null;
        try {
            in = new FileInputStream(getFilesDir()+"/"+FILENAME);
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Vous n'avez aucun contact.", Toast.LENGTH_LONG).show();
        }
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert in != null;
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ketSet() renvoie la liste des clefs
        Iterator it =  prop.keySet().iterator();
        //StringBuilder sb = new StringBuilder();
        while(it.hasNext())
        {
            String tel = (String) it.next();
            String[] contenu = prop.getProperty(tel).split(";");
            String nom = contenu[0];
            String prenom = contenu[1];
            String sexe = contenu[2];
            Sexe s;
            if(sexe.equals("Homme"))
                s = Sexe.HOMME;
            else
                s = Sexe.FEMME;
            lPersonne.add(new Personne(s, nom, tel, prenom));
            //sb.append(sexe).append(" ").append(nom).append(" ").append(prenom).append(" : ").append(clef).append("\n");
        }
        /*
        new AlertDialog.Builder(this)
                .setTitle("Le fichier contient : ")
                .setMessage(sb.toString())
                .setPositiveButton(android.R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
        */
    }

    public void onClickRetour(View v)
    {
        Intent annulation = new Intent(this, Accueil.class);
        startActivity(annulation);
    }


}
