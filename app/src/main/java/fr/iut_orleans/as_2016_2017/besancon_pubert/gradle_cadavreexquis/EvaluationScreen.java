package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Evaluation;


/**
 * Created by besancon on 30/05/17.
 */

public class EvaluationScreen extends Activity {

    int idUser;
    int idHistoire;
    Utilisateur user;
    Histoire histoire;
    float noteMoyenne;
    int noteUser;
    CadavreExquisBDD cadavreExquisBDD;
    Intent intentAuto;
    Evaluation evaluat;
    EditText comm;
    TextView commTitre;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_evaluation);
        intentAuto = new Intent(this,HistoriquePhrase.class);
        Intent intent = getIntent();

        idHistoire = Integer.parseInt(intent.getStringExtra("idHistoire"));
        noteUser=-1;


        idUser = Integer.parseInt(intent.getStringExtra("idUser"));
        TextView texteUser = (TextView) findViewById(R.id.textUser);
        TextView texteTitre = (TextView) findViewById(R.id.textTitreHistoire);
        TextView texteHistoire = (TextView) findViewById(R.id.textHistoireComplette);
        TextView texteNote = (TextView) findViewById(R.id.textNote);
        RadioGroup groupeRadioBoutton = (RadioGroup) findViewById(R.id.groupRadioButtonAll);
        commTitre = (TextView) findViewById(R.id.commentaireAll);

        TextView add_comm = (TextView) findViewById(R.id.commentaireUser);
        comm = (EditText)findViewById(R.id.Add_commentaire);
        Button validerEval=(Button)findViewById(R.id.ajouterEval);

        evaluat = new Evaluation();

        cadavreExquisBDD = new CadavreExquisBDD(this);
        cadavreExquisBDD.open();
        user = cadavreExquisBDD.getUtilisateurWithID(idUser);
        texteUser.setText("Session de " + user.getLogin());

        histoire = cadavreExquisBDD.getHistoireWithID(idHistoire);
        texteTitre.setText(histoire.getTitre());
        String histoireComplette = new String();
        for (Texte texte:histoire.getTextes())
            histoireComplette += texte.getContenu();
        texteHistoire.setText(histoireComplette);


        if(cadavreExquisBDD.getEvaluationWithID(idUser,idHistoire)!=null){

            groupeRadioBoutton.setVisibility(View.GONE);
            comm.setVisibility(View.GONE);
            validerEval.setVisibility(View.GONE);

            noteMoyenne = cadavreExquisBDD.getEvaluationAverageNoteForHistoire(idHistoire);
            texteNote.setText("La Note que vous avez mis sur cette histoire est : "+ Float.toString(cadavreExquisBDD.getEvaluationWithID(idUser,idHistoire).getNote()) + ". \n La moyenne des notes est de : "+noteMoyenne+" !");

            String comm ="\n\n";

            for(Evaluation eval : cadavreExquisBDD.getAllEvaluationByhistoire(idHistoire)){
                comm += cadavreExquisBDD.getUtilisateurWithID(eval.getUtilisateur_id()).getLogin() +" : " +eval.getCommentaire()+"\n\n";
            }

            add_comm.setText(comm);
        }
        else
        {
            texteNote.setText("Notez l'histoire");
            groupeRadioBoutton.setVisibility(View.VISIBLE);
            comm.setVisibility(View.VISIBLE);
            commTitre.setVisibility(View.GONE);

        }
        cadavreExquisBDD.close();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.noteButton0:
                if (checked)
                    noteUser = 0;
                break;
            case R.id.noteButton1:
                if (checked)
                    noteUser = 1;
                break;
            case R.id.noteButton2:
                if (checked)
                    noteUser = 2;
                break;
            case R.id.noteButton3:
                if (checked)
                    noteUser = 3;
                break;
            case R.id.noteButton4:
                if (checked)
                    noteUser = 4;
                break;
            case R.id.noteButton5:
                if (checked)
                    noteUser = 5;
                break;
        }

        evaluat.setNote(noteUser);

    }

    public void ajouterEval(View view){

        if(noteUser==-1)
            Toast.makeText(this, "Vous n'avez pas noté l'histoire", Toast.LENGTH_LONG).show();
        else if (comm.getText().toString().equals(""))
            Toast.makeText(this, "Vous n'avez pas ajouté de commentaire", Toast.LENGTH_LONG).show();
        else {
            Date date = new Date();

            evaluat.setDateEvaluation(date);
            evaluat.setCommentaire(comm.getText().toString());
            evaluat.setHistoire_id(idHistoire);
            evaluat.setUtilisateur_id(idUser);


            cadavreExquisBDD.open();
            cadavreExquisBDD.insertEvaluation(evaluat);
            cadavreExquisBDD.close();
            super.finish();

        }
    }



}
