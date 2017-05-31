package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

/**
 * Created by besancon on 30/05/17.
 */

public class Evaluation extends Activity {

    int idUser;
    Utilisateur user;
    Histoire histoire;
    Float evaluation;
    CadavreExquisBDD cadavreExquisBDD;
    Intent intentAuto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_evaluation);
        intentAuto = new Intent(this,HistoriquePhrase.class);
        Intent intent = getIntent();
//        int idHistoire = Integer.parseInt(intent.getStringExtra("idHistoire"));
        int idUser = Integer.parseInt(intent.getStringExtra("idUser"));

        TextView texteUser = (TextView) findViewById(R.id.textUser);
//        TextView texteTitre = (TextView) findViewById(R.id.textTitreHistoire);
//        TextView texteHistoire = (TextView) findViewById(R.id.textHistoireComplette);
//        TextView texteNote = (TextView) findViewById(R.id.textNote);
        RadioGroup groupeRadioBoutton = (RadioGroup) findViewById(R.id.groupRadioButtonAll);


        cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();
        user = cadavreExquisBDD.getUtilisateurWithID(idUser);
        texteUser.setText("Session " + user.getLogin());

//        histoire = cadavreExquisBDD.getHistoireWithID(idHistoire);
//        texteTitre.setText(histoire.getTitre());
//        String histoireComplette = new String();
//        for (Texte texte:histoire.getTextes())
//            histoireComplette += texte;
//        texteHistoire.setText(histoireComplette);

        //TODO une méthode de cadavreExquisBDD.userDoEvaluation(idUser,idHistoire)   return bool
        //TODO une méthode de cadavreExquisBDD.getMoyenneEvaluation(idhistoire)      return float
//        if(cadavreExquisBDD.userDoEvaluation(idUser,idHistoire)){
//            groupeRadioBoutton.setVisibility(View.GONE);
//            evaluation = cadavreExquisBDD.getMoyenneEvaluation(idHistoire);
//            texteNote.setText("La moyenne des notes pour cette histoire est : "+evaluation);
//          }
//        else
//        {
//            texteNote.setText("Notez l'histoire");
//            radioBoutton.setVisibility(View.Visible);
//        }
        cadavreExquisBDD.close();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        int note=0;
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.noteButton0:
                if (checked)
                    note = 0;
                break;
            case R.id.noteButton1:
                if (checked)
                    note = 1;
                break;
            case R.id.noteButton2:
                if (checked)
                    note = 2;
                break;
            case R.id.noteButton3:
                if (checked)
                    note = 3;
                break;
            case R.id.noteButton4:
                if (checked)
                    note = 4;
                break;
            case R.id.noteButton5:
                if (checked)
                    note = 5;
                break;
        }

        TextView test = (TextView)findViewById(R.id.test);
        test.setText(Integer.toString(note));
        super.finish();
    }
}
