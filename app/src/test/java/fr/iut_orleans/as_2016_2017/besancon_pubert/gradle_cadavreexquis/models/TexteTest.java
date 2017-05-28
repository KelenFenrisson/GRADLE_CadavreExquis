package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;
import org.junit.Test;
import java.util.Date;


import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Histoire;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Texte;
import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

import static junit.framework.Assert.assertEquals;

/**
 * Created by kelenfenrisson on 25/05/17.
 */
public class TexteTest {

    private int id = 1;
    private int nouveauId = 2;
    private Date date = new Date(123456);
    private Date nouveauDate = new Date(123457);
    private String contenu = "Il etait un foie";
    private String nouveauContenu = "Il etait une fois";
    private Integer utilisateur_id = 1;
    private Integer nouveauUtilisateur_id = 2;
    private Integer histoire_id = 1;
    private Integer nouveauHistoire_id = 1;

    @Test
    public void getId() throws Exception {

        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        assertEquals(id, t.getId().intValue());

    }

    @Test
    public void getDate() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        assertEquals(date, t.getDate());

    }

    @Test
    public void getContenu() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        assertEquals(contenu, t.getContenu());
    }

    @Test
    public void getUtilisateur() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        assertEquals(utilisateur_id, t.getUtilisateur_id());
    }

    @Test
    public void getHistoire() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        assertEquals(histoire_id, t.getHistoire_id());
    }

    @Test
    public void setId() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        t.setId(nouveauId);
        assertEquals(nouveauId, t.getId().intValue());

    }

    @Test
    public void setDate() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        t.setDate(nouveauDate);
        assertEquals(nouveauDate, t.getDate());
    }

    @Test
    public void setContenu() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        t.setContenu(nouveauContenu);
        assertEquals(nouveauContenu, t.getContenu());
    }

    @Test
    public void setUtilisateur() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        t.setUtilisateur_id(nouveauUtilisateur_id);
        assertEquals(nouveauUtilisateur_id, t.getUtilisateur_id());
    }

    @Test
    public void setHistoire() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur_id, histoire_id);
        t.setHistoire_id(nouveauHistoire_id);
        assertEquals(nouveauHistoire_id, t.getHistoire_id());
    }

}