package com.example.alice.stockage.Vue;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.alice.stockage.BDD.DataBaseClass;
import com.example.alice.stockage.R;
import com.example.alice.stockage.Donnees.Sexe;

import java.io.File;
import java.io.FileWriter;

public class Ajout extends AppCompatActivity {

    String FILENAME; // méthode avec l'enregistrement dans un fichier
    SQLiteDatabase Contacts; // méthode avec SQLite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);
        Intent reception = getIntent();
        FILENAME = reception.getStringExtra("fichier");
        Contacts = DataBaseClass.getInstance();
    }

    private void ajoutNom(String tel, String nom, String prenom, String s)
    {

        // Ajout dans un fichier
        File fos;
        FileWriter fw;
        String texte = tel + "=" + nom + ";" + prenom + ";"+s+"\n";

        try
        {
            fos = new File(getFilesDir(), FILENAME);
            fw = new FileWriter(fos, true);
            fw.write(texte);
            fw.close();

            Toast.makeText(getApplicationContext(), "Ajout réussi dans le fichier!", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Toast.makeText(getApplicationContext(), "Contact ajouté : " + texte, Toast.LENGTH_LONG).show();


        // Ajout dans la BDD SQLite
        try{

            Contacts.execSQL("INSERT INTO contacts (nom, prenom, tel)"+
                             " VALUES('" + nom + "', '" + prenom + "','"+ tel +"')");
//etc)
            Toast.makeText(this, "Le contact" + nom + " "
                    + prenom + "a été inséré", Toast.LENGTH_LONG).show();
        }

        catch (Exception e){
            Toast.makeText(this, "Le contact n'a pas pu être inséré", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickStock(View v)
    {
        EditText etNom = (EditText) findViewById(R.id.txtNom);
        EditText etPrenom = (EditText) findViewById(R.id.txtRecherche);
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

        ajoutNom(tel, nom, prenom, s.toString());
    }

    public void onClickAnnule(View v)
    {
        Intent annulation = new Intent(this, Accueil.class);
        startActivity(annulation);
    }
}
