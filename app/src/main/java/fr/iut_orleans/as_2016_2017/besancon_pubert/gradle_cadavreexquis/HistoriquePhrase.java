package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by besancon on 30/05/17.
 */

public class HistoriquePhrase extends Activity {

    ArrayList<String> listeElem;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.historique);
        listeElem = new ArrayList<String>();
        listeElem.add("Pommes");
        listeElem.add("Poires");
        listeElem.add("Cerises");
        adapter = new ArrayAdapter<String>(this, R.layout.historique, listeElem);
        ListView listeV1 = (ListView) findViewById(R.id.listeViewHistorique);
        listeV1.setAdapter(adapter);

//        listeV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.i("test",""+i);
//            }
//        });

    }





}
