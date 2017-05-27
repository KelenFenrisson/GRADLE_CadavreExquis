package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainMenu extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_mainmenu);
        Intent intent = getIntent ();
        String message = "Bonjour, "+intent.getStringExtra("text");
        //TextView texte = (TextView)findViewById(R.id.textView);
        //texte.setText(message);
    }

    public void jouer(View view){
        Intent intent = new Intent(this,NouvellePhrase.class);
        startActivityForResult(intent,3);
    }

    public void seDeconnecter(View view){
        Intent intent = new Intent ();
        setResult(Activity.RESULT_OK , intent );
        super.finish ();
    }
}
