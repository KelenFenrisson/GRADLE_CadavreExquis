package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences perso;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_loginscreen);
        this.cadavreExquisBDD = new CadavreExquisBDD(this);
        this.cadavreExquisBDD.open();

        if(this.cadavreExquisBDD.getUtilisateurWithID(1) == null){
            cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.INSERT_UTILISATEURS);
            cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.INSERT_HISTOIRES);
            cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.INSERT_TEXTES);
            cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.INSERT_EVALUER);
        }

//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onCreate(Bundle savedInstanceState) - recuperation du bundle :\n"+savedInstanceState);

        perso = getPreferences(MODE_PRIVATE);


        if(perso.getString("login",null)!=null){
            Utilisateur user = cadavreExquisBDD.getUtilisateurWithLogin(perso.getString("login",null));

            ((EditText)findViewById(R.id.txtinput_login)).setText(user.getLogin());
            ((EditText)findViewById(R.id.txtinput_password)).setText(user.getMotDePasse());

        }


    }

    public void onLogInClick(View view){
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - appui sur le bouton :\n");

        String login = ((EditText)findViewById(R.id.txtinput_login)).getText().toString();
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.onLogInClick(View view) - texte dans le champ Login :\n"+login);

        String password = ((EditText)findViewById(R.id.txtinput_password)).getText().toString();
//        Log.i("test", "LoginScreen test connexion");

        if(checkCredentials(login, password)){
            perso.edit().putString("login",login).commit();

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

        Utilisateur utilisateur = cadavreExquisBDD.getUtilisateurWithLogin(login);
        if (utilisateur!=null)
            idUser = utilisateur.getId();


        return utilisateur != null && utilisateur.getMotDePasse().equals(password);
    }

    public void inscription(View view){
//        Log.i(getResources().getString(R.string.app_name), "LoginScreen.inscription(View view) - appui sur le bouton :\n");
        Intent intent = new Intent(this,Inscription.class);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult (int requestcode, int resultcode, Intent intent ) {

        super.onActivityResult(requestcode, resultcode, intent);

        switch(requestcode){


            case 1 : // RETOUR DE L'INSCRITPION
                switch(resultcode){
                    case RESULT_OK :
                        Toast.makeText(this, "Votre compte a bien été créé.", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_CANCELED :
                        Toast.makeText(this, "Inscription annulée", Toast.LENGTH_SHORT).show();
                        break;
                    default :
                        Toast.makeText(this, "Une anomalie est survenue. Veuillez refaire votre inscription.", Toast.LENGTH_SHORT).show();
                        break;
                }
                break;



            case 2 : // RETOUR DE LA DECONNEXION
                switch(resultcode){
                    case RESULT_OK :
                        String message = intent.getStringExtra("deco");
                        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
                        toast.show();
                        break;
                    case RESULT_CANCELED :
                        Toast.makeText(this, "Une anomalie est survenue. Vous n'êtes pas deconnecté", Toast.LENGTH_SHORT).show();
                        break;
                    default: break;
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        this.cadavreExquisBDD.close();
        super.onDestroy();
    }
}
