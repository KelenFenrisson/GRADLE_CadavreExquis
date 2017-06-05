package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

/**
 * Created by besancon on 30/05/17.
 */

public class HistoriquePhrase extends Activity {

    ArrayList<String> listeElem;
    ArrayAdapter<String> adapter;
    ArrayList<Histoire> listeHistoire;
    CadavreExquisBDD cadavreExquisBDD;
    int idUser;
    Utilisateur user;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.historique);

        // Ouverture de la BDD
        cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();

        Intent intentId = getIntent ();
        String histo = intentId.getStringExtra("historique");
        idUser = Integer.parseInt(intentId.getStringExtra("idUser"));
        TextView texteTitre = (TextView) findViewById(R.id.textTitreHistoriquePhrase);


        intent = new Intent(this,EvaluationScreen.class);
        intent.putExtra("idUser",Integer.toString(idUser));

        listeElem = new ArrayList<String>();



        //vient du main avec clique sur le bouton TOUTES LES HISTOIRES
        if(histo.equals("all")){
            listeHistoire = cadavreExquisBDD.getCompleteHistoireList();
            texteTitre.setText("Toutes les histoires");

        }

        //vient du main avec clique sur le bouton MES HISTOIRES
        if(histo.equals("onlyUser")){
            listeHistoire = cadavreExquisBDD.getAllHistoireFromUtilisateurID(idUser);
            texteTitre.setText("Mes histoires");
        }

        for(Histoire titre : listeHistoire)
            listeElem.add(titre.getTitre());

        //association adapteur avec la listeview
        adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, listeElem);
        ListView listeV1 = (ListView) findViewById(R.id.listeViewHistorique);
        listeV1.setAdapter(adapter);

        listeV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Histoire histoire = listeHistoire.get(i);
                if(cadavreExquisBDD.getTextesArrayListWithHistoireID(histoire.getId()).size()>=5){
                    intent.putExtra("idHistoire",Integer.toString(histoire.getId()));
                    intent.putExtra("idUser",Integer.toString(idUser));
                    startActivityForResult(intent,1);
                }
                else{
                    Toast.makeText(HistoriquePhrase.this, "Cette histoire n'est pas terminée ! Patience !", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public void finish(){
        cadavreExquisBDD.close();
        super.finish();
    }

}
