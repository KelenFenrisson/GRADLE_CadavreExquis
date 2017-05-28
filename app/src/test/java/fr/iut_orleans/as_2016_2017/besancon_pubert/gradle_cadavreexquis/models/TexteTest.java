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
    private Utilisateur utilisateur = new Utilisateur(1,"Utilisateur", "MotDePasse");
    private Utilisateur nouveauUtilisateur = new Utilisateur(2,"AutreUtilisateur", "AutreMotDePasse");
    private Histoire histoire = new Histoire(new Date(123456), "Titre");
    private Histoire nouveauHistoire = new Histoire(new Date(123457), "Titre");

    @Test
    public void getId() throws Exception {

        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        assertEquals(id, t.getId().intValue());

    }

    @Test
    public void getDate() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        assertEquals(date, t.getDate());

    }

    @Test
    public void getContenu() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        assertEquals(contenu, t.getContenu());
    }

    @Test
    public void getUtilisateur() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        assertEquals(utilisateur, t.getUtilisateur());
    }

    @Test
    public void getHistoire() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        assertEquals(histoire, t.getHistoire());
    }

    @Test
    public void setId() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        t.setId(nouveauId);
        assertEquals(nouveauId, t.getId().intValue());

    }

    @Test
    public void setDate() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        t.setDate(nouveauDate);
        assertEquals(nouveauDate, t.getDate());
    }

    @Test
    public void setContenu() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        t.setContenu(nouveauContenu);
        assertEquals(nouveauContenu, t.getContenu());
    }

    @Test
    public void setUtilisateur() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        t.setUtilisateur(nouveauUtilisateur);
        assertEquals(nouveauUtilisateur, t.getUtilisateur());
    }

    @Test
    public void setHistoire() throws Exception {
        Texte t = new Texte(id, date, contenu, utilisateur, histoire);
        t.setHistoire(nouveauHistoire);
        assertEquals(nouveauHistoire, t.getHistoire());
    }

}