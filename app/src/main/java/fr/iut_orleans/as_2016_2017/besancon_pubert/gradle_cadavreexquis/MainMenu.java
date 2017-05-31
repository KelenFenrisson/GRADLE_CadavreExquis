package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends Activity
{
    /** Called when the activity is first created. */
    String nameUser;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_mainmenu);
        Intent intent = getIntent ();
        nameUser = intent.getStringExtra("nomUser");
        String message = "Bonjour, "+nameUser;
        TextView texte = (TextView)findViewById(R.id.textView);
        texte.setText(message);
    }

    public void jouer(View view){
        Intent intent = new Intent(this,NouvellePhrase.class);
        //ajouter envoie du nom user en +
        startActivityForResult(intent,3);
    }

    public void historique(View view){
        Intent intent = new Intent(this,HistoriquePhrase.class);
        //ajouter envoie du nom user en +
        startActivityForResult(intent,4);
    }

    public void seDeconnecter(View view){
        Intent intent = new Intent ();
        intent.putExtra("deco", "Déconnexion");
        setResult(Activity.RESULT_OK , intent );
        super.finish ();
    }
}
