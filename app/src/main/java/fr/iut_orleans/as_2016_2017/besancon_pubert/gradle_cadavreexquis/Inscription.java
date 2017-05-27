package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Inscription extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_inscription);
        Intent intent = getIntent ();
    }

    public void validerInscription(View view){
        Intent intent = new Intent ();
        setResult(Activity.RESULT_OK , intent );
        super.finish ();
    }
}
