package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.TexteBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TexteBDDTest {

    private Context appContext = InstrumentationRegistry.getTargetContext();
    private Date date = new Date(123456);
    private Date nouveauDate = new Date(123457);
    private String contenu = "Il etait un foie";
    private String nouveauContenu = "Il etait une fois";
    private Utilisateur utilisateur = new Utilisateur(1,"Utilisateur", "MotDePasse");
    private Utilisateur nouveauUtilisateur = new Utilisateur(2,"AutreUtilisateur", "AutreMotDePasse");
    private Histoire histoire = new Histoire(1, 123456);
    private Histoire nouveauHistoire = new Histoire(2, 123457);

    @Test
    public void openAndCloseTest() throws Exception {
        // Context of the app under test.
        TexteBDD texteBDD = new TexteBDD(appContext);
        texteBDD.open();
        texteBDD.close();
    }

    @Test
    public void insertTexteTest() throws Exception {
        // Context of the app under test.
        TexteBDD texteBDD = new TexteBDD(appContext);
        texteBDD.open();

        //Insertions
        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));
        texteBDD.close();
    }

    @Test
    public void getTexteWithIDTest() throws Exception {
        // Context of the app under test.
        TexteBDD texteBDD = new TexteBDD(appContext);
        texteBDD.open();

        //Insertions
        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));

        //Recuperation par Login
        Texte texte = texteBDD.getTexteWithID(1);

        assertEquals("Récuperation d'un texte avec son ID", new Texte(1, date, contenu, utilisateur, histoire),texte);

        texteBDD.close();
    }


    @Test
    public void updateTexteTest() throws Exception {
        // Context of the app under test.
        TexteBDD texteBDD = new TexteBDD(appContext);
        texteBDD.open();

        //Insertions
        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));
        texteBDD.updateTexte(1, new Texte());

        //Recuperation par Login
        Texte texte = texteBDD.getTexteWithID(1);

        assertEquals("Modification d'un texte", new Texte(1, nouveauDate, nouveauContenu, nouveauUtilisateur, nouveauHistoire),texte);

        texteBDD.close();
    }

    @Test
    public void removeUtilisateurTest() throws Exception {
        // Context of the app under test.
        TexteBDD texteBDD = new TexteBDD(appContext);
        texteBDD.open();

        //Insertions
        texteBDD.insertTexte(new Texte(date, contenu, utilisateur, histoire));
        texteBDD.removeTexteWithID(1);

        assertEquals("Suppression d'un Texte ", null, texteBDD.getTexteWithID(1));

        texteBDD.close();
    }

}
