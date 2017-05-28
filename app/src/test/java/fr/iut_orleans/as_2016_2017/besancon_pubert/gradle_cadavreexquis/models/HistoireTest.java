package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mathieu on 28/05/17.
 */
public class HistoireTest {

    Histoire histoire1;
    Histoire histoire2;
    Histoire histoire3;

    @Before
    public void setUp()
    {
        histoire1 = new Histoire(1, new Date(123456), "Premiere histoire");
        histoire2 = new Histoire(2, new Date(123457), "Seconde histoire");
        histoire3 = new Histoire(3, new Date(123458), "Troisieme histoire");

    }

    @Test
    public void getId() throws Exception {

        assertEquals("Histoire.getId() 1 : ", 1, histoire1.getId().intValue());
        assertNotEquals("Histoire.getId() 2 : ", histoire1.getId(), histoire2.getId());
        assertNotEquals("Histoire.getId() 3 : ", 2, histoire3.getId().intValue());
    }

    @Test
    public void getDateCreation() throws Exception {

        assertEquals("Histoire.getDateCreation() 1 : ", new Date(123456), histoire1.getDateCreation());
        assertNotEquals("Histoire.getDateCreation() 2 : ", histoire1.getDateCreation(), histoire2.getDateCreation());
        assertNotEquals("Histoire.getDateCreation() 3 : ", new Date(123456), histoire3.getDateCreation());
    }

    @Test
    public void getTitre() throws Exception {
        assertEquals("Histoire.getTitre() 1 : ", "Premiere histoire", histoire1.getTitre());
        assertNotEquals("Histoire.getTitre() 2 : ", histoire1.getTitre(), histoire2.getTitre());
        assertNotEquals("Histoire.getTitre() 3 : ", "Premiere histoire", histoire3.getTitre());
    }

    @Test
    public void getTextes() throws Exception {

        ArrayList<Texte> th1 = new ArrayList<>();
        th1.add(new Texte(1, new Date(), "Texte1", null, null));
        th1.add(new Texte(2, new Date(), "Texte2", null, null));
        th1.add(new Texte(3, new Date(), "Texte3", null, null));
        histoire1.setTextes(th1);

        ArrayList<Texte> th2 = new ArrayList<>();
        th1.add(new Texte(4, new Date(), "Texte1", null, null));
        th1.add(new Texte(5, new Date(), "Texte2", null, null));
        th1.add(new Texte(6, new Date(), "Texte3", null, null));
        histoire2.setTextes(th2);

        assertEquals("Histoire.getTextes() 1 : ", th1, histoire1.getTextes());
        assertNotEquals("Histoire.getTextes() 2 : ", histoire1.getTextes(), histoire2.getTextes());
        assertEquals("Histoire.getTextes() 3 : ", new ArrayList<Texte>(), histoire3.getTextes());
    }

    @Test
    public void setId() throws Exception {

        histoire1.setId(4);
        histoire2.setId(5);
        histoire3.setId(6);
        assertEquals("Histoire.setId() 1 : ", 4, histoire1.getId().intValue());
        assertNotEquals("Histoire.setId() 2 : ", histoire1.getId(), histoire2.getId());
        assertNotEquals("Histoire.setId() 3 : ", 5, histoire3.getId().intValue());
    }

    @Test
    public void setDateCreation() throws Exception {

        histoire1.setDateCreation(new Date(987654));
        histoire2.setDateCreation(new Date(987644));
        histoire3.setDateCreation(new Date(987684));
        assertEquals("Histoire.setDateCreation() 1 : ", new Date(987654), histoire1.getDateCreation());
        assertNotEquals("Histoire.setDateCreation() 2 : ", histoire1.getDateCreation(), histoire2.getDateCreation());
        assertNotEquals("Histoire.setDateCreation() 3 : ",new Date(987654), histoire3.getDateCreation());
    }

    @Test
    public void setTitre() throws Exception {

        histoire1.setTitre("Histoire ");
        histoire2.setTitre("Histware ");
        histoire3.setTitre("Histouère ");
        assertEquals("Histoire.setTitre() 1 : ", "Histoire ", histoire1.getTitre());
        assertNotEquals("Histoire.setTitre() 2 : ", histoire1.getTitre(), histoire2.getTitre());
        assertNotEquals("Histoire.setTitre() 3 : ", "Histoire ", histoire3.getTitre());
    }

    @After
    public void tearDown(){
        //TODO
    }

}