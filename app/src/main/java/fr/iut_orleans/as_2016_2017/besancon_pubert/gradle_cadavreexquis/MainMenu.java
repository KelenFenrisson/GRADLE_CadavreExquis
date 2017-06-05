package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

public class MainMenu extends Activity
{
    /** Called when the activity is first created. */
    int idUser;
    Utilisateur user;
    CadavreExquisBDD cadavreExquisBDD;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_mainmenu);
        Intent intent = getIntent ();
        idUser = Integer.parseInt(intent.getStringExtra("idUser"));
        cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();
        user = cadavreExquisBDD.getUtilisateurWithID(idUser);
        String message = "Session de "+user.getLogin();
        TextView texte = (TextView)findViewById(R.id.textView);
        texte.setText(message);
    }

    public void jouer(View view){
        Intent intent = new Intent(this,NouvellePhrase.class);
        intent.putExtra("idUser",Integer.toString(idUser));
        startActivity(intent);
    }

    public void historiqueAll(View view){
        Intent intent = new Intent(this,HistoriquePhrase.class);
        intent.putExtra("idUser",Integer.toString(idUser));
        intent.putExtra("historique","all");
        startActivity(intent);
    }

    public void historiqueUserOnly(View view){
        Intent intent = new Intent(this,HistoriquePhrase.class);
        intent.putExtra("idUser",Integer.toString(idUser));
        intent.putExtra("historique","onlyUser");
        startActivity(intent);
    }

    public void seDeconnecter(View view){
        Intent intent = new Intent ();
        intent.putExtra("deco", "Déconnexion");
        setResult(Activity.RESULT_OK , intent );
        this.finish();
    }

    @Override
    public void finish ()
    {
        Intent intent = new Intent ();
        intent.putExtra("deco", "Déconnexion");
        super.finish ();
    }
}
