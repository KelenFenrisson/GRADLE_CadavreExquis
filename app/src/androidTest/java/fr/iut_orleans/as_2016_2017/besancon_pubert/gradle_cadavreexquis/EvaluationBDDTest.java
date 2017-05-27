package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Evaluation;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.EvaluationBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.HistoireBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.TexteBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.UtilisateurBDD;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EvaluationBDDTest {

    private Context appContext;
    private EvaluationBDD evaluationBDD;
    private TexteBDD texteBDD;
    private UtilisateurBDD utilisateurBDD;
    private HistoireBDD histoireBDD;
    private Date date;
    private Date nouveauDate;
    private String commentaire;
    private String nouveauCommentaire;
    private int note;
    private int nouveauNote;
    private Texte texte ;
    private Texte nouveautexte ;
    private Utilisateur utilisateur;
    private Utilisateur nouveauUtilisateur;
    private Histoire histoire;
    private Histoire nouveauHistoire;


    @Before
    public void setUp(){
        appContext = InstrumentationRegistry.getTargetContext();
        date = new Date(123456);
        nouveauDate = new Date(123457);
        commentaire = "Commentaire";
        nouveauCommentaire = "Nouveau Commentaire";
        note = 10;
        nouveauNote = 5;
        utilisateur = new Utilisateur(1, "Utilisateur", "MotDePasse");
        nouveauUtilisateur = new Utilisateur(2, "AutreUtilisateur", "AutreMotDePasse");
        histoire = new Histoire(1, date);
        nouveauHistoire = new Histoire(2, nouveauDate);
        texte = new Texte(1, date,"Il etait un foie",utilisateur,histoire);
        nouveautexte = new Texte(2, nouveauDate, "Il etait une fois", nouveauUtilisateur, nouveauHistoire);
        evaluationBDD = new EvaluationBDD(this.appContext);
        histoireBDD = new HistoireBDD(this.appContext);
        texteBDD = new TexteBDD(this.appContext);
        utilisateurBDD = new UtilisateurBDD(this.appContext);
        evaluationBDD.open();
        histoireBDD.open();
        texteBDD.open();
        utilisateurBDD.open();


    }

    @Test
    public void insertTexteTest() throws Exception {

        utilisateurBDD.insertUtilisateur(utilisateur);
        texteBDD.insertTexte(texte);
        evaluationBDD.insertEvaluation(new Evaluation(utilisateur, texte, date, note, commentaire));
    }

    @Test
    public void getTexteWithIDTest() throws Exception {

        histoireBDD.insertHistoire(histoire);

        utilisateurBDD.insertUtilisateur(utilisateur);
        assertEquals("On verifie l'utilisateur",utilisateur ,utilisateurBDD.getUtilisateurWithID(utilisateur.getId()));

        texteBDD.insertTexte(texte);
        assertEquals("On verifie le texte",texte,texteBDD.getTexteWithID(texte.getId()));

        evaluationBDD.insertEvaluation(new Evaluation(utilisateur, texte, date, note, commentaire));

        //Recuperation par Login
        Evaluation evaluation = evaluationBDD.getEvaluationWithID(utilisateur.getId(), texte.getId());

        assertEquals("Récuperation d'une evaluation avec son ID", new Evaluation(utilisateur, texte, date, note, commentaire),evaluation);

    }


    @Test
    public void updateTexteTest() throws Exception {

        histoireBDD.insertHistoire(histoire);
        histoireBDD.insertHistoire(nouveauHistoire);

        utilisateurBDD.insertUtilisateur(utilisateur);
        utilisateurBDD.insertUtilisateur(nouveauUtilisateur);

        assertEquals("On verifie l'utilisateur",utilisateur ,utilisateurBDD.getUtilisateurWithID(utilisateur.getId()));
        assertEquals("On verifie l'autre utilisateur",nouveauUtilisateur ,utilisateurBDD.getUtilisateurWithID(nouveauUtilisateur.getId()));

        texteBDD.insertTexte(texte);
        texteBDD.insertTexte(nouveautexte);
        assertEquals("On verifie le texte",texte,texteBDD.getTexteWithID(texte.getId()));
        assertEquals("On verifie le nouveau texte",nouveautexte,texteBDD.getTexteWithID(nouveautexte.getId()));

        Evaluation evaluation = new Evaluation(utilisateur, texte, date, note, commentaire);
        evaluationBDD.insertEvaluation(evaluation);
        evaluationBDD.updateEvaluation(evaluation.getUtilisateur().getId(), evaluation.getTexte().getId(), new Evaluation(nouveauUtilisateur, nouveautexte, nouveauDate, nouveauNote, nouveauCommentaire));

        //Recuperation par Login
        evaluation = evaluationBDD.getEvaluationWithID(nouveauUtilisateur.getId(), nouveautexte.getId());

        assertEquals("Modification d'une evaluation", new Evaluation(nouveauUtilisateur, nouveautexte, nouveauDate, nouveauNote, nouveauCommentaire),evaluation);

    }

    @Test
    public void removeTexteTest() throws Exception {

        //Insertions
        utilisateurBDD.insertUtilisateur(utilisateur);
        texteBDD.insertTexte(texte);
        evaluationBDD.insertEvaluation(new Evaluation(utilisateur, texte, date, note, commentaire));

        evaluationBDD.removeEvaluationWithID(utilisateur.getId(), texte.getId());

        assertEquals("Suppression d'un Texte ", null, evaluationBDD.getEvaluationWithID(utilisateur.getId(), texte.getId()));

    }

    @After
    public void tearDown(){
        evaluationBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_EVALUER);
        evaluationBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_TEXTE);
        evaluationBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_HISTOIRE);
        evaluationBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_UTILISATEUR);

        evaluationBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_UTILISATEUR);
        evaluationBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_HISTOIRE);
        evaluationBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_TEXTE);
        evaluationBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_EVALUER);

        evaluationBDD.close();
        texteBDD.close();
        histoireBDD.close();
        utilisateurBDD.close();
    }

}
