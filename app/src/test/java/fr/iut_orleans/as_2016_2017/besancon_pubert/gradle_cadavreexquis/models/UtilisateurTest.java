package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;
import org.junit.Test;

import fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models.Utilisateur;

import static junit.framework.Assert.assertEquals;
/**
 * Created by kelenfenrisson on 25/05/17.
 */
public class UtilisateurTest{

    private int id = 1;
    private int nouveauId = 2;
    private String login = "Login";
    private String nouveauLogin = "NouveauLogin";
    private String motDePasse = "MotDePasse";
    private String nouveauMotDePasse = "NouveauMotDePasse";

    @Test
    public void getId() throws Exception {

        Utilisateur u = new Utilisateur(id, login, motDePasse);
        assertEquals(id, u.getId().intValue());
    }

    @Test
    public void getLogin() throws Exception {

        Utilisateur u = new Utilisateur(id, login, motDePasse);
        assertEquals(login, u.getLogin());
    }

    @Test
    public void getMotDePasse() throws Exception {

        Utilisateur u = new Utilisateur(id, login, motDePasse);
        assertEquals(motDePasse, u.getMotDePasse());
    }

    @Test
    public void setId() throws Exception {

        Utilisateur u = new Utilisateur(id, login, motDePasse);
        u.setId(nouveauId);
        assertEquals(nouveauId, u.getId().intValue());
    }

    @Test
    public void setLogin() throws Exception {

        Utilisateur u = new Utilisateur(id, login, motDePasse);
        u.setLogin(nouveauLogin);
        assertEquals(nouveauLogin, u.getLogin());
    }

    @Test
    public void setMotDePasse() throws Exception {

        Utilisateur u = new Utilisateur(id, login, motDePasse);
        u.setMotDePasse(nouveauMotDePasse);
        assertEquals(nouveauMotDePasse, u.getMotDePasse());
    }

}