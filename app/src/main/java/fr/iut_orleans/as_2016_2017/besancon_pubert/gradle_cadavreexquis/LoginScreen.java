package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.UtilisateurBDD;

public class LoginScreen extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_loginscreen);
        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onCreate(Bundle savedInstanceState) - recuperation du bundle :\n"+savedInstanceState);
        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onCreate(Bundle savedInstanceState) - recuperation de l'intent :\n"+ getIntent());
    }


    public void onLogInClick(View view){
        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - appui sur le bouton :\n");

        String login = ((EditText)findViewById(R.id.txtinput_login)).getText().toString();
        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - texte dans le champ Login :\n"+login);

        String password = ((EditText)findViewById(R.id.txtinput_password)).getText().toString();
        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - texte dans le champ Password :\n"+password);


        if(checkCredentials(login, password)){
            this.setResult(RESULT_OK);
            //TODO
        }
        else{
            Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - la verif retourne faux :\n");
            //TODO
        }
    }

    public boolean checkCredentials(String login, String password){
        //TODO : un vrai contrôle de mot de passe et tout et tout
        Log.i(getResources().getString(R.string.app_name), "LoginScreen.checkCredentials(String login, String password) - verif :\n");

        UtilisateurBDD utilisateurBDD = new UtilisateurBDD(this);
        utilisateurBDD.open();
        Utilisateur utilisateur = utilisateurBDD.getUtilisateurWithLogin(login);
        utilisateurBDD.close();

        return utilisateur != null && utilisateur.getMotDePasse().equals(password);
    }

    public void onCancelClick(View view){
        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onCancelClick(View view) - appui sur le bouton :\n");
        //TODO
    }

}
