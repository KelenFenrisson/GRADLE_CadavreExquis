package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.CadavreExquisBDD;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.UtilisateurBDD;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UtilisateurBDDTest{

    private Context appContext;
    private UtilisateurBDD utilisateurBDD;
    private String nomUtilisateur;
    private String nouveauNomUtilisateur;
    private String mdpUtilisateur;
    private String nouveauMdpUtilisateur;




    @Before
    public void setUp() throws Exception{
        appContext = InstrumentationRegistry.getTargetContext();
        nomUtilisateur = "Utilisateur";
        nouveauNomUtilisateur= "Admin";
        mdpUtilisateur = "MotDePasse";
        nouveauMdpUtilisateur = "Password";
        utilisateurBDD = new UtilisateurBDD(appContext);
        utilisateurBDD.open();
    }

    @Test
    public void insertUtilisateurTest() throws Exception {
        // Context of the app under test.

        //Insertions
        utilisateurBDD.insertUtilisateur(new Utilisateur(nomUtilisateur, mdpUtilisateur));
    }




    @Test
    public void getUtilisateurWithIDTest() throws Exception {

        //Insertions
        utilisateurBDD.insertUtilisateur(new Utilisateur(nomUtilisateur, mdpUtilisateur));
        //Recuperation par Login
        Utilisateur utilisateur = utilisateurBDD.getUtilisateurWithID(1);

        assertEquals("Récuperation d'un utilisateur avec son ID", new Utilisateur(1, nomUtilisateur, mdpUtilisateur), utilisateur);

    }

    @Test
    public void getUtilisateurWithLoginTest() throws Exception {

        //Insertions
        utilisateurBDD.insertUtilisateur(new Utilisateur(nomUtilisateur, mdpUtilisateur));
        //Recuperation par Login
        Utilisateur utilisateur = utilisateurBDD.getUtilisateurWithLogin(nomUtilisateur);

        assertEquals("Récuperation d'un utilisateur avec son Login", new Utilisateur(1, nomUtilisateur, mdpUtilisateur), utilisateur);

    }

    @Test
    public void updateUtilisateurTest() throws Exception {

        //Insertions
        utilisateurBDD.insertUtilisateur(new Utilisateur(nomUtilisateur, mdpUtilisateur));

        utilisateurBDD.updateUtilisateur(1, new Utilisateur(nouveauNomUtilisateur, nouveauMdpUtilisateur));

        assertEquals("Modification d'un utilisateur ", new Utilisateur(1, nouveauNomUtilisateur, nouveauMdpUtilisateur), utilisateurBDD.getUtilisateurWithID(1));

    }

    @Test
    public void removeUtilisateurTest() throws Exception {

        //Insertions
        utilisateurBDD.insertUtilisateur(new Utilisateur(nomUtilisateur, mdpUtilisateur));
        utilisateurBDD.removeUtilisateurWithID(1);

        assertEquals("Suppression d'un utilisateur ", null, utilisateurBDD.getUtilisateurWithID(1));

    }

    @After
    public void tearDown(){
        utilisateurBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_UTILISATEUR);
        utilisateurBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_UTILISATEUR);
        utilisateurBDD.close();
    }

}
