package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginScreen extends Activity
{
    /** Called when the activity is first created. */
    EditText login;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_loginscreen);
    }

    public void seConnecter(View view){
        Intent intent = new Intent(this,MainMenu.class);
        //login = (EditText)findViewById(R.id.editLogin);
        intent.putExtra("text", login.getText().toString());
        startActivityForResult(intent,1);
    }

    public void sincrire(View view){
        Intent intent = new Intent(this,Inscription.class);
        startActivityForResult(intent,2);
    }
}
