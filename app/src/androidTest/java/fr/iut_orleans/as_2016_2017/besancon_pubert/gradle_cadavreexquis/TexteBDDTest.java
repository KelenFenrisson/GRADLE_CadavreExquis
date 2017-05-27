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
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.HistoireBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.TexteBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.UtilisateurBDD;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TexteBDDTest {

    private Context appContext;
    private TexteBDD texteBDD;
    private HistoireBDD histoireBDD;
    private UtilisateurBDD utilisateurBDD;
    private Date date;
    private Date nouveauDate;
    private String contenu;
    private String nouveauContenu;
    private Utilisateur utilisateur;
    private Utilisateur nouveauUtilisateur;
    private Histoire histoire ;
    private Histoire nouveauHistoire ;

    @Before
    public void setUp(){
        appContext = InstrumentationRegistry.getTargetContext();
        date = new Date(123456);
        nouveauDate = new Date(123457);
        contenu = "Il etait un foie";
        nouveauContenu = "Il etait une fois";
        utilisateur = new Utilisateur(1,"Utilisateur", "MotDePasse");
        nouveauUtilisateur = new Utilisateur(2,"AutreUtilisateur", "AutreMotDePasse");
        histoire = new Histoire(1, date);
        nouveauHistoire = new Histoire(2, nouveauDate);
        texteBDD = new TexteBDD(this.appContext);
        histoireBDD = new HistoireBDD(this.appContext);
        utilisateurBDD = new UtilisateurBDD(this.appContext);
        texteBDD.open();
        histoireBDD.open();
        utilisateurBDD.open();


    }

    @Test
    public void insertTexteTest() throws Exception {

        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));
    }

    @Test
    public void getTexteWithIDTest() throws Exception {

        //Insertions
        utilisateurBDD.insertUtilisateur(utilisateur);
        assertEquals("L'utilisateur est le même qu'a l'entree", utilisateurBDD.getUtilisateurWithID(utilisateur.getId()), utilisateur);
        histoireBDD.insertHistoire(histoire);
        assertEquals("L'histoire est la même qu'a l'entree", histoireBDD.getHistoireWithID(histoire.getId()), histoire);
        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));

        //Recuperation par Login
        Texte texte = texteBDD.getTexteWithID(1);

        assertEquals("Récuperation d'un texte avec son ID", new Texte(1, date, contenu, utilisateur, histoire),texte);

    }


    @Test
    public void updateTexteTest() throws Exception {


        //Insertions
        utilisateurBDD.insertUtilisateur(utilisateur);
        utilisateurBDD.insertUtilisateur(nouveauUtilisateur);
        assertEquals("L'utilisateur est le même qu'a l'entree", utilisateurBDD.getUtilisateurWithID(utilisateur.getId()), utilisateur);
        assertEquals("Le nouveaUtilisateur est le même qu'a l'entree", utilisateurBDD.getUtilisateurWithID(nouveauUtilisateur.getId()), nouveauUtilisateur);
        histoireBDD.insertHistoire(histoire);
        histoireBDD.insertHistoire(nouveauHistoire);
        assertEquals("L'histoire est la même qu'a l'entree", histoireBDD.getHistoireWithID(histoire.getId()), histoire);
        assertEquals("La nouveauHistoire est la même qu'a l'entree", histoireBDD.getHistoireWithID(nouveauHistoire.getId()), nouveauHistoire);
        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));
        texteBDD.updateTexte(1, new Texte(nouveauDate, nouveauContenu, nouveauUtilisateur, nouveauHistoire));

        //Recuperation par Login
        Texte texte = texteBDD.getTexteWithID(1);

        assertEquals("Modification d'un texte", new Texte(1, nouveauDate, nouveauContenu, nouveauUtilisateur, nouveauHistoire),texte);

    }

    @Test
    public void removeTexteTest() throws Exception {

        //Insertions
        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));
        texteBDD.removeTexteWithID(1);

        assertEquals("Suppression d'un Texte ", null, texteBDD.getTexteWithID(1));

    }

    @After
    public void tearDown(){
        texteBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_TEXTE);
        texteBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_HISTOIRE);
        texteBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_UTILISATEUR);
        texteBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_UTILISATEUR);
        texteBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_HISTOIRE);
        texteBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_TEXTE);

        texteBDD.close();
        histoireBDD.close();
        utilisateurBDD.close();
    }

}
