package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        Intent intentId = getIntent ();
        String histo = intentId.getStringExtra("historique");
        idUser = Integer.parseInt(intentId.getStringExtra("idUser"));

        intent = new Intent(this,EvaluationScreen.class);
        intent.putExtra("idUser",Integer.toString(idUser));

        listeElem = new ArrayList<String>();

        cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();

        //vient du main avec clique sur le bouton TOUTES LES HISTOIRES
        if(histo.equals("all")){
            listeHistoire = cadavreExquisBDD.getAllHistoire();
        }

        //vient du main avec clique sur le bouton MES HISTOIRES
        if(histo.equals("onlyUser")){
            listeHistoire = cadavreExquisBDD.getAllHistoireFromUtilisateurID(idUser);
        }

        cadavreExquisBDD.close();
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
                intent.putExtra("idHistoire",Integer.toString(histoire.getId()));
                intent.putExtra("idUser",Integer.toString(idUser));
                startActivityForResult(intent,1);
            }
        });

    }





}
