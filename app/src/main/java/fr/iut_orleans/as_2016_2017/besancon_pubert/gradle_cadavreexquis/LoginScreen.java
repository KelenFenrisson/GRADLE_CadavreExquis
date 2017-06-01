package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.ProjectSQLiteOpenHelper;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

public class LoginScreen extends Activity
{

    int idUser;
    CadavreExquisBDD cadavreExquisBDD;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_loginscreen);
        this.cadavreExquisBDD = new CadavreExquisBDD(this);
        this.cadavreExquisBDD.open();
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.INSERT_UTILISATEURS);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.INSERT_HISTOIRES);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.INSERT_TEXTES);
        this.cadavreExquisBDD.close();
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onCreate(Bundle savedInstanceState) - recuperation du bundle :\n"+savedInstanceState);

    }

    public void onLogInClick(View view){
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - appui sur le bouton :\n");

        String login = ((EditText)findViewById(R.id.txtinput_login)).getText().toString();
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - texte dans le champ Login :\n"+login);

        String password = ((EditText)findViewById(R.id.txtinput_password)).getText().toString();
//        Log.i("test", "LoginScreen test connexion");

        if(checkCredentials(login, password)){
            Intent intent = new Intent (this,MainMenu.class);
            intent.putExtra("idUser", Integer.toString(idUser));
            setResult(Activity.RESULT_OK , intent );
            startActivityForResult(intent,2);
        }
        else{
            Toast toast = Toast.makeText(this, "Votre login ou votre mot de passe n'est pas correct", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean checkCredentials(String login, String password){

        cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();
        Utilisateur utilisateur = cadavreExquisBDD.getUtilisateurWithLogin(login);
        if (utilisateur!=null)
            idUser = utilisateur.getId();
        cadavreExquisBDD.close();

        return utilisateur != null && utilisateur.getMotDePasse().equals(password);
    }

    public void inscription(View view){
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.inscription(View view) - appui sur le bouton :\n");
        Intent intent = new Intent(this,Inscription.class);
        startActivityForResult(intent,1);
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
