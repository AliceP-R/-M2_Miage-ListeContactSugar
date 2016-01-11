package com.example.alice.stockage.Vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.alice.stockage.R;
import com.example.alice.stockage.Donnees.Sexe;

import java.io.File;
import java.io.FileWriter;

public class Ajout extends AppCompatActivity {

    String FILENAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        Intent reception = getIntent();
        FILENAME = reception.getStringExtra("fichier");
    }

    private void ajoutNom(String texte)
    {
        File fos;
        FileWriter fw;

        try
        {
            fos = new File(getFilesDir(), FILENAME);
            fw = new FileWriter(fos, true);
            fw.write(texte);
            fw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "Contact ajouté : " + texte, Toast.LENGTH_LONG).show();
    }

    public void onClickStock(View v)
    {
        EditText etNom = (EditText) findViewById(R.id.txtNom);
        EditText etPrenom = (EditText) findViewById(R.id.txtPrenom);
        EditText etTel = (EditText) findViewById(R.id.txtTel);
        RadioButton rbH = (RadioButton) findViewById(R.id.rbHomme);
        RadioButton rbF = (RadioButton) findViewById(R.id.rbFemme);

        String nom = etNom.getText().toString();
        String prenom = etPrenom.getText().toString();
        String tel = etTel.getText().toString();
        Sexe s=null;

        if(nom.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Vous avez oublié de rentrer votre nom !", Toast.LENGTH_LONG).show();
            return;
        }
        if(prenom.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Vous avez oublié de rentrer votre prénom !", Toast.LENGTH_LONG).show();
            return;
        }
        if(tel.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Vous avez oublié de rentrer votre numéro !", Toast.LENGTH_LONG).show();
            return;
        }
        if((!rbH.isChecked()) && !(rbF.isChecked()))
        {
            Toast.makeText(getApplicationContext(), "Vous avez oublié de choisir votre sexe !", Toast.LENGTH_LONG).show();
            return;
        }
        if(rbH.isChecked())
            s=Sexe.HOMME;
        else
            s=Sexe.FEMME;

        ajoutNom(tel + "=" + nom + ";" + prenom + ";"+s.toString()+"\n");
    }

    public void onClickAnnule(View v)
    {
        Intent annulation = new Intent(this, Accueil.class);
        startActivity(annulation);
    }
}
