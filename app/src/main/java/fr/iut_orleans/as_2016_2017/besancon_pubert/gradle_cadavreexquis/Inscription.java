package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

public class Inscription extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_inscription);
    }
    
    public void validerInscription(View view){
        EditText login = (EditText)findViewById(R.id.editLogin);
        EditText password = (EditText)findViewById(R.id.editPassword);
        EditText passwordConfirm = (EditText)findViewById(R.id.editPasswordCondfirm);
        String textLogin = login.getText().toString();
        String textPassword = password.getText().toString();

        CadavreExquisBDD cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();

        if(!password.getText().toString().equals(passwordConfirm.getText().toString()))
        {
            Toast toast = Toast.makeText(this, "le mot de passe est mal confirmé", Toast.LENGTH_LONG);
            toast.show();
        }
        else if (cadavreExquisBDD.inBDDUtilisateurWithLogin(textLogin)){
            Toast toast = Toast.makeText(this, "Ce nom existe déjà", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            Utilisateur utilisateur = new Utilisateur(textLogin,textPassword);
            cadavreExquisBDD.insertUtilisateur(utilisateur);
            cadavreExquisBDD.close();
            setResult(RESULT_OK);
            super.finish ();
        }
    }

    // dans le cas ou l'on appuie sur la fleche de retour
    @Override
    public void finish ()
    {
        super.finish ();
    }

}
