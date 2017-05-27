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
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.UtilisateurBDD;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class HistoireBDDTest {

    private Context appContext;
    private HistoireBDD histoireBDD;
    private Date date;
    private Date nouvelledate;


    @Before
    public void setUp() throws Exception{
        appContext = InstrumentationRegistry.getTargetContext();
        date = new Date(123456);
        nouvelledate = new Date(123457);
        histoireBDD = new HistoireBDD(appContext);
        histoireBDD.open();
    }

    @Test
    public void insertHistoireTest() throws Exception {
        // Context of the app under test.

        //Insertions
        histoireBDD.insertHistoire(new Histoire(date));
    }




    @Test
    public void getHistoireWithIDTest() throws Exception {

        //Insertions
        histoireBDD.insertHistoire(new Histoire(date));
        //Recuperation par Login
        Histoire histoire = histoireBDD.getHistoireWithID(1);

        assertEquals("Récuperation d'une histoire avec son ID", new Histoire(1, date), histoire);

    }

    @Test
    public void updateHistoireTest() throws Exception {

        //Insertions
        histoireBDD.insertHistoire(new Histoire(date));
        histoireBDD.updateHistoire(1, new Histoire(nouvelledate));
        //Recuperation par Login
        Histoire histoire = histoireBDD.getHistoireWithID(1);

        assertEquals("Modification d'une histoire", new Histoire(1, nouvelledate), histoire);
    }

    @Test
    public void removeUtilisateurTest() throws Exception {

        //Insertions
        histoireBDD.insertHistoire(new Histoire(date));
        histoireBDD.removeHistoireWithID(1);

        assertEquals("Suppression d'un utilisateur ", null, histoireBDD.getHistoireWithID(1));

    }

    @After
    public void tearDown(){
        histoireBDD.getBDD().execSQL("DROP TABLE "+ CadavreExquisBDD.TABLE_HISTOIRE);
        histoireBDD.getBDD().execSQL(CadavreExquisBDD.CREATE_TABLE_HISTOIRE);
        histoireBDD.close();
    }

}
