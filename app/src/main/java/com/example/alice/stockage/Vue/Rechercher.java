package com.example.alice.stockage.Vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alice.stockage.Donnees.Personne;
import com.example.alice.stockage.Personnalisation.ContactAdapter;
import com.example.alice.stockage.R;

import java.util.ArrayList;

public class Rechercher extends AppCompatActivity {

    private ArrayList lPersonne;
    private ArrayList lRes;
    private ContactAdapter adapterContact;
    private ListView lv;

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            TextView tvID = (TextView) view.findViewById(R.id.tvId);
            String text = tvID.getText().toString();
            TextView tvRech = (TextView) findViewById(R.id.txtRecherche);
            String recherche = tvRech.getText().toString();
            Intent intent = new Intent(parent.getContext(), Modification.class);
            intent.putExtra("select-id", text);
            intent.putExtra("text_recherche", recherche);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rechercher);
        Intent retour = getIntent();
        String recherche = retour.getStringExtra("text_recherche");

        if(recherche != null) // on affiche l'ancienne recherche
        {
            lPersonne = (ArrayList) Personne.find(Personne.class, "nom LIKE '%" + recherche + "' OR prenom LIKE '%" + recherche +"'" );
            EditText etRech = (EditText) findViewById(R.id.txtRecherche);
            etRech.setText(recherche);
        }
        else // on affiche rien
        {
            lPersonne = new ArrayList<>();
        }

        lRes = new ArrayList();
        adapterContact = new ContactAdapter(this, lPersonne);
        lv = (ListView) findViewById(R.id.lview);
        lv.setAdapter(adapterContact);
        lv.setOnItemClickListener(new ListClickHandler());


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
