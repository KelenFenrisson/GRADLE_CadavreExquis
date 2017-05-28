package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

public class LoginScreen extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_loginscreen);
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onCreate(Bundle savedInstanceState) - recuperation du bundle :\n"+savedInstanceState);


    }


    public void onLogInClick(View view){
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - appui sur le bouton :\n");

        String login = ((EditText)findViewById(R.id.txtinput_login)).getText().toString();
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - texte dans le champ Login :\n"+login);

        String password = ((EditText)findViewById(R.id.txtinput_password)).getText().toString();
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - texte dans le champ Password :\n"+password);

        if(checkCredentials(login, password)){
            Intent intent = new Intent (this,MainMenu.class);
            EditText nameuser = (EditText)findViewById(R.id.txtinput_login);
            String message = nameuser.getText().toString();
            intent.putExtra("nomUser", message);
            setResult(Activity.RESULT_OK , intent );
            startActivityForResult(intent,2);
        }
        else{
//            Log.i("test", "LoginScreen.onLogInClick(View view) - la verif retourne faux :\n");
            Toast toast = Toast.makeText(this, "Votre login ou votre mot de passe n'est pas correct", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean checkCredentials(String login, String password){
        //TODO : un vrai contrôle de mot de passe et tout et tout
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.checkCredentials(String login, String password) - verif :\n");

        CadavreExquisBDD cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();
        Utilisateur utilisateur = cadavreExquisBDD.getUtilisateurWithLogin(login);
        cadavreExquisBDD.close();

        return utilisateur != null && utilisateur.getMotDePasse().equals(password);
    }

    public void inscription(View view){
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.inscription(View view) - appui sur le bouton :\n");
        Intent intent = new Intent(this,Inscription.class);
        startActivityForResult(intent,2);
    }

    @Override
    public void onActivityResult (int requestcode, int resultcode, Intent intent ) {
        if(requestcode==2) {
            super.onActivityResult(requestcode, resultcode, intent);
            String message = intent.getStringExtra("deco");
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
            toast.show();
            ((EditText) findViewById(R.id.txtinput_login)).setText("");
            ((EditText) findViewById(R.id.txtinput_password)).setText("");
        }
    }

}
