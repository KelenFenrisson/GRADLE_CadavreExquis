package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.Date;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.ProjectSQLiteOpenHelper;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;

public class NouvellePhrase extends Activity
{
    Histoire histoire;
    Texte texte;
    CadavreExquisBDD cadavreExquisBDD;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_nouvellephrase);
        cadavreExquisBDD.open();






    }


    public void onBtnAddSentenceClick(View view){
        EditText phraseUtilisateur = (EditText) (findViewById(R.id.txtinp_usersentence));
        //regarder si il y a une histoire comportant moins de 5 textes
        // histoire = cadavreExquisBDD.getFirstHistoireToComplete();
        histoire = null;

        if(histoire != null) {
            // Si il y en a une, recuperer son id pour la future insertion de texte

        }
        else{

        }
        // Sinon, on en commence une.
        histoire = new Histoire(new Date(), getTitleFromAlertBox());
        //

    }

    public String getTitleFromAlertBox(){

        String titre = "";

        final EditText edittext = new EditText(this);
        edittext.setText("Write the new title here.");
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("This new story needs a name !");
        alert.setTitle("Story Title");

        alert.setView(edittext);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                //titre = edittext.getText().toString();
            }
        });

        alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();

        return titre;
    }


    @Override
    protected void onDestroy() {
        cadavreExquisBDD.close();
        super.onDestroy();
    }
}
