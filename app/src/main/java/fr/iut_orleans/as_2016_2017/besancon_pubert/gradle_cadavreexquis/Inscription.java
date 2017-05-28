package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        EditText password = (EditText)findViewById(R.id.editPassword);
        EditText passwordConfirm = (EditText)findViewById(R.id.editPasswordCondfirm);
        if(!password.getText().toString().equals(passwordConfirm.getText().toString()))
        {
            Toast toast = Toast.makeText(this, "Pas le mot de passe identique", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            //inscription a faire dans la base de données
            super.finish ();
        }
    }
}
