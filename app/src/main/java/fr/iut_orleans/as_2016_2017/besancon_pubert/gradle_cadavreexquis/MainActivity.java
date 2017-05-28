package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.UtilisateurBDD;

public class MainActivity extends AppCompatActivity {

    public static final int LOGIN_REQUEST = 100;

    private String utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(getResources().getString(R.string.app_name), "MainActivity.onCreate(Bundle savedInstanceState) - recuperation du bundle :\n"+savedInstanceState);

        // S'il n'y a pas de sauvegarde, il n'y a pas d'utilisateur.
        if(savedInstanceState == null)
        {
            Intent connexionIntent = new Intent(this, LoginScreen.class);
            Log.i(getResources().getString(R.string.app_name), "MainActivity.onCreate(Bundle savedInstanceState) - intent :\n"+connexionIntent);

            startActivityForResult(new Intent(this, LoginScreen.class), LOGIN_REQUEST);
        }
    }

    @Override
    public void onActivityResult (int requestcode, int resultcode, Intent intent ){
        super.onActivityResult(requestcode, resultcode, intent);

        Log.i(getResources().getString(R.string.app_name), "MainActivity.onActivityResult(int requestcode, int resultcode, Intent intent ) - requestcode :\n"+requestcode);
        Log.i(getResources().getString(R.string.app_name), "MainActivity.onActivityResult(int requestcode, int resultcode, Intent intent ) - resultcode :\n"+resultcode);
        Log.i(getResources().getString(R.string.app_name), "MainActivity.onActivityResult(int requestcode, int resultcode, Intent intent ) - intent :\n"+ intent);

        switch(requestcode){
            case LOGIN_REQUEST :
                switch(resultcode){
                    case (RESULT_OK) : this.utilisateur = intent.getStringExtra("utilisateur");
//                    case (RESULT_CANCELED) : ;
//                    case (RESULT_FIRST_USER): ;
                }
        }
    }
}
