package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Evaluation;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.ProjectSQLiteOpenHelper;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CadavreExquisBDDTest {

    private Context appContext;
    private CadavreExquisBDD cadavreExquisBDD;

    private String login;
    private String password;
    private Date date;
    private String contenu;
    private Integer note;
    private String commentaire;

    private String autrelogin;
    private String autrepassword;
    private Date autredate;
    private String autrecontenu;
    private Integer autrenote;
    private String autrecommentaire;

    private Utilisateur utilisateur;
    private Utilisateur autreutilisateur;
    private Histoire histoire;
    private Histoire autrehistoire;
    private Texte texte;
    private Texte autretexte;
    private Evaluation evaluation;
    private Evaluation autreevaluation;




    @Before
    public void setUp(){
        appContext = InstrumentationRegistry.getTargetContext();
        login = "Utilisateur";
        autrelogin = "AutreUtilisateur";
        password = "MotDePasse";
        autrepassword = "AutreMotDePasse";
        date = new Date(100000);
        autredate = new Date(200000);
        contenu = "Il était une fois";
        autrecontenu = "Par un beau jour de printemps";
        note = 10;
        autrenote = 5;
        commentaire = "Bien tourné, j'adore !";
        autrecommentaire = "Ca tombe un peu a plat ...";

        utilisateur = new Utilisateur(1, login, password);
        autreutilisateur = new Utilisateur(2, autrelogin, autrepassword);
        histoire = new Histoire(1, date);
        autrehistoire = new Histoire(2, autredate);
        texte = new Texte(1, date, contenu, utilisateur, histoire);
        autretexte = new Texte(2, autredate, autrecontenu, autreutilisateur, autrehistoire);
        evaluation = new Evaluation(utilisateur, texte, date, note, commentaire);
        autreevaluation = new Evaluation(autreutilisateur, autretexte, autredate, autrenote, autrecommentaire);

        cadavreExquisBDD = new CadavreExquisBDD(this.appContext);
        cadavreExquisBDD.open();
    }

    @Test
    public void UtilisateurTest() throws Exception {

        cadavreExquisBDD.insertUtilisateur(utilisateur);
        cadavreExquisBDD.insertUtilisateur(autreutilisateur);

        assertEquals("TEST cadavreExquisBDD.getUtilisateurWithID 1", utilisateur, cadavreExquisBDD.getUtilisateurWithID(utilisateur.getId()));
        assertEquals("TEST cadavreExquisBDD.getUtilisateurWithID 2", autreutilisateur, cadavreExquisBDD.getUtilisateurWithID(autreutilisateur.getId()));

        Utilisateur mod1 = cadavreExquisBDD.getUtilisateurWithID(utilisateur.getId());
        Utilisateur mod2 = cadavreExquisBDD.getUtilisateurWithID(autreutilisateur.getId());
        mod1.setMotDePasse(autrepassword);
        mod2.setMotDePasse(password);
        cadavreExquisBDD.updateUtilisateur(1, mod1);
        cadavreExquisBDD.updateUtilisateur(2, mod2);

        assertEquals("TEST cadavreExquisBDD.updateUtilisateur 1", mod1, cadavreExquisBDD.getUtilisateurWithID(utilisateur.getId()));
        assertEquals("TEST cadavreExquisBDD.updateUtilisateur 2", mod2, cadavreExquisBDD.getUtilisateurWithID(autreutilisateur.getId()));

        cadavreExquisBDD.removeUtilisateurWithID(1);
        cadavreExquisBDD.removeUtilisateurWithID(2);

        assertEquals("TEST cadavreExquisBDD.removeUtilisateur 1", null, cadavreExquisBDD.getUtilisateurWithID(utilisateur.getId()));
        assertEquals("TEST cadavreExquisBDD.removeUtilisateur 2", null, cadavreExquisBDD.getUtilisateurWithID(autreutilisateur.getId()));
    }


    @Test
    public void HistoireTest() throws Exception {

        cadavreExquisBDD.insertHistoire(histoire);
        cadavreExquisBDD.insertHistoire(autrehistoire);
        assertEquals("TEST cadavreExquisBDD.getHistoireWithID 1", histoire, cadavreExquisBDD.getHistoireWithID(histoire.getId()));
        assertEquals("TEST cadavreExquisBDD.getHistoireWithID 2", autrehistoire, cadavreExquisBDD.getHistoireWithID(autrehistoire.getId()));

        Histoire mod1 = cadavreExquisBDD.getHistoireWithID(histoire.getId());
        Histoire mod2 = cadavreExquisBDD.getHistoireWithID(autrehistoire.getId());
        mod1.setDateCreation(autredate);
        mod2.setDateCreation(date);
        cadavreExquisBDD.updateHistoire(1, mod1);
        cadavreExquisBDD.updateHistoire(2, mod2);
        assertEquals("TEST cadavreExquisBDD.updateHistoire 1", mod1, cadavreExquisBDD.getHistoireWithID(histoire.getId()));
        assertEquals("TEST cadavreExquisBDD.updateHistoire 2", mod2, cadavreExquisBDD.getHistoireWithID(autrehistoire.getId()));

        cadavreExquisBDD.removeHistoireWithID(1);
        cadavreExquisBDD.removeHistoireWithID(2);

        assertEquals("TEST cadavreExquisBDD.removeHistoire 1", null, cadavreExquisBDD.getHistoireWithID(histoire.getId()));
        assertEquals("TEST cadavreExquisBDD.removeHistoire 2", null, cadavreExquisBDD.getHistoireWithID(autrehistoire.getId()));
    }


    @Test
    public void TexteTest() throws Exception {
        cadavreExquisBDD.insertUtilisateur(utilisateur);
        cadavreExquisBDD.insertUtilisateur(autreutilisateur);
        cadavreExquisBDD.insertHistoire(histoire);
        cadavreExquisBDD.insertHistoire(autrehistoire);
        cadavreExquisBDD.insertTexte(texte);
        cadavreExquisBDD.insertTexte(autretexte);
        assertEquals("TEST cadavreExquisBDD.getTexteWithID 1", texte, cadavreExquisBDD.getTexteWithID(texte.getId()));
        assertEquals("TEST cadavreExquisBDD.getTexteWithID 2", autretexte, cadavreExquisBDD.getTexteWithID(autretexte.getId()));

        Texte mod1 = cadavreExquisBDD.getTexteWithID(texte.getId());
        Texte mod2 = cadavreExquisBDD.getTexteWithID(autretexte.getId());
        mod1.setContenu(autrecontenu);
        mod1.setContenu(contenu);
        cadavreExquisBDD.updateTexte(1, mod1);
        cadavreExquisBDD.updateTexte(2, mod2);
        assertEquals("TEST cadavreExquisBDD.updateTexte 1", mod1, cadavreExquisBDD.getTexteWithID(texte.getId()));
        assertEquals("TEST cadavreExquisBDD.updateTexte 2", mod2, cadavreExquisBDD.getTexteWithID(autretexte.getId()));

        cadavreExquisBDD.removeTexteWithID(1);
        cadavreExquisBDD.removeTexteWithID(2);

        assertEquals("TEST cadavreExquisBDD.removeTexte 1", null, cadavreExquisBDD.getTexteWithID(texte.getId()));
        assertEquals("TEST cadavreExquisBDD.removeTexte 2", null, cadavreExquisBDD.getTexteWithID(autretexte.getId()));
    }

    @Test
    public void EvaluationTest() throws Exception {
        cadavreExquisBDD.insertUtilisateur(utilisateur);
        cadavreExquisBDD.insertUtilisateur(autreutilisateur);
        cadavreExquisBDD.insertHistoire(histoire);
        cadavreExquisBDD.insertHistoire(autrehistoire);
        cadavreExquisBDD.insertTexte(texte);
        cadavreExquisBDD.insertTexte(autretexte);
        cadavreExquisBDD.insertEvaluation(evaluation);
        cadavreExquisBDD.insertEvaluation(autreevaluation);
        assertEquals("TEST cadavreExquisBDD.getEvaluationWithID 1", evaluation, cadavreExquisBDD.getEvaluationWithID(evaluation.getUtilisateur().getId(), evaluation.getTexte().getId()));
        assertEquals("TEST cadavreExquisBDD.getEvaluationWithID 2", autreevaluation, cadavreExquisBDD.getEvaluationWithID(autreevaluation.getUtilisateur().getId(), autreevaluation.getTexte().getId()));

        Evaluation mod1 = cadavreExquisBDD.getEvaluationWithID(evaluation.getUtilisateur().getId(), evaluation.getTexte().getId());
        Evaluation mod2 = cadavreExquisBDD.getEvaluationWithID(autreevaluation.getUtilisateur().getId(), autreevaluation.getTexte().getId());
        mod1.setNote(autrenote);
        mod1.setNote(note);
        cadavreExquisBDD.updateEvaluation(1,1, mod1);
        cadavreExquisBDD.updateEvaluation(2,2, mod2);
        assertEquals("TEST cadavreExquisBDD.updateEvaluation 1", mod1, cadavreExquisBDD.getEvaluationWithID(evaluation.getUtilisateur().getId(), evaluation.getTexte().getId()));
        assertEquals("TEST cadavreExquisBDD.updateEvaluation 2", mod2, cadavreExquisBDD.getEvaluationWithID(autreevaluation.getUtilisateur().getId(), autreevaluation.getTexte().getId()));

        cadavreExquisBDD.removeEvaluationWithID(1,1);
        cadavreExquisBDD.removeEvaluationWithID(2,2);

        assertEquals("TEST cadavreExquisBDD.removeEvaluation 1", null, cadavreExquisBDD.getEvaluationWithID(evaluation.getUtilisateur().getId(), evaluation.getTexte().getId()));
        assertEquals("TEST cadavreExquisBDD.removeEvaluation 2", null, cadavreExquisBDD.getEvaluationWithID(autreevaluation.getUtilisateur().getId(), autreevaluation.getTexte().getId()));
    }

    @After
    public void tearDown(){

        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.DROP_TABLE_EVALUER);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.DROP_TABLE_TEXTE);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.DROP_TABLE_HISTOIRE);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.DROP_TABLE_UTILISATEUR);

        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.CREATE_TABLE_UTILISATEUR);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.CREATE_TABLE_HISTOIRE);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.CREATE_TABLE_TEXTE);
        cadavreExquisBDD.getBDD().execSQL(ProjectSQLiteOpenHelper.CREATE_TABLE_EVALUER);

        cadavreExquisBDD.close();
    }

}
