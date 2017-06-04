package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Evaluation;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.ProjectSQLiteOpenHelper;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

public class NouvellePhrase extends Activity
{
    Utilisateur utilisateur;
    Histoire histoire;
    CadavreExquisBDD cadavreExquisBDD;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_nouvellephrase);
        this.cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();
        utilisateur = cadavreExquisBDD.getUtilisateurWithID(Integer.parseInt(getIntent().getStringExtra("idUser")));

        // Affichage des infos de l'histoire
        histoire = cadavreExquisBDD.getHistoireToCompleteForUser(utilisateur.getId());

        // Si l'utilisateur continue une histoire existante, on lui affiche la derniere phrase
        if(histoire != null){
            ((TextView) findViewById(R.id.txtinput_newtitle)).setText(histoire.getTitre());
            ((TextView) findViewById(R.id.txtinput_newtitle)).setEnabled(false);
            ((TextView) findViewById(R.id.lbl_story)).setText(cadavreExquisBDD.getLastTexteFrom(histoire.getId()).getContenu());
        } // Sinon, on ne fait rien, le texte par defaut est dejà là.
    }


    public void onBtnAddSentenceClick(View view) {
        String titreUtilisateur = ((EditText) (findViewById(R.id.txtinput_newtitle))).getText().toString();
        String phraseUtilisateur = ((EditText) (findViewById(R.id.txtinp_usersentence))).getText().toString();

        // Si c'est une nouvelle histoire
        if (histoire == null) {
            histoire = cadavreExquisBDD.getHistoireWithID((int) (cadavreExquisBDD.insertHistoire(new Histoire(new Date(System.currentTimeMillis()), titreUtilisateur))));
        }


        //Si l'utilisateur a ecrit quelque chose pour son histoire
        if (!phraseUtilisateur.equals(getString(R.string.app_writeyoursentence)) && !phraseUtilisateur.equals("") && !titreUtilisateur.equals("") && !titreUtilisateur.equals(getString(R.string.app_inputnewtitle))) {
            cadavreExquisBDD.insertTexte(new Texte(new Date(System.currentTimeMillis()), phraseUtilisateur, utilisateur.getId(), histoire.getId()));
            this.finish();
        } else {
            Toast.makeText(this, "Il vous manque quelque-chose ... Reverifiez ! ", Toast.LENGTH_SHORT);
        }
    }

    public void onBtnCancelClick(View view){
        this.finish();
    }


    public String getTitleFromAlertBox(){

        final String[] titre = {""};

        final EditText edittext = new EditText(this);
        edittext.setText("Write the new title here.");
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("This new story needs a name !");
        alert.setTitle("Story Title");

        alert.setView(edittext);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                titre[0] = edittext.getText().toString();
            }
        });

        alert.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });

        alert.show();

        return titre[0];
    }

    @Override
    public void finish() {
        Intent intent = new Intent ();
        setResult(Activity.RESULT_OK , intent );
        super.finish();
    }

    @Override
    protected void onDestroy() {
        cadavreExquisBDD.close();
        super.onDestroy();
    }
}
