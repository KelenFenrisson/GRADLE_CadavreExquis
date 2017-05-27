package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

public class MainActivity extends AppCompatActivity {

    Utilisateur utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Si un utilisateur est connecté :
        if(savedInstanceState.get("Utilisateur")!=null)
        {

        }
        // Sinon on envoie Anonyme se connecter
        else{
            utilisateur = this.getUtilisateur();
        }
    }

    public Utilisateur getUtilisateur(){

        Utilisateur utilisateur = null;
        Intent intent = new Intent("Connexion Utilisateur", Uri.parse("Utilisateur : " + utilisateur), this , LoginScreen.class);

        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

        return utilisateur;
    }
}
