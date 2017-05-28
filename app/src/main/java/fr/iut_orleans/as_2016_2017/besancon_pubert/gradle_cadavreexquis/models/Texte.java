package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Date;
import java.util.Objects;

/**
 * Created by kelenfenrisson on 22/05/17.
 */

public class Texte {
    private Integer id;
    private Date date;
    private String contenu;
    private Integer utilisateur_id;
    private Integer histoire_id;

    public Texte() {

        this.date = null;
        this.contenu = null;
        this.utilisateur_id = null;
        this.histoire_id = null;
    }

    public Texte(Date date, String contenu, Integer utilisateur_id, Integer histoire_id) {
        this.date = date;
        this.contenu = contenu;
        this.utilisateur_id = utilisateur_id;
        this.histoire_id = histoire_id;
    }

    public Texte(Integer id, Date date, String contenu, Integer utilisateur_id, Integer histoire_id) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
        this.utilisateur_id = utilisateur_id;
        this.histoire_id = histoire_id;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getContenu() {
        return contenu;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public Integer getHistoire_id() {
        return histoire_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public void setHistoire_id(Integer histoire_id) {
        this.histoire_id = histoire_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Texte texte = (Texte) o;
        return Objects.equals(id, texte.id) &&
                Objects.equals(date, texte.date) &&
                Objects.equals(contenu, texte.contenu) &&
                Objects.equals(utilisateur_id, texte.utilisateur_id) &&
                Objects.equals(histoire_id, texte.histoire_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, contenu, utilisateur_id, histoire_id);
    }

    @Override
    public String toString() {
        return "Texte{" +
                "id=" + id +
                ", date=" + date +
                ", contenu='" + contenu + '\'' +
                ", utilisateur_id=" + utilisateur_id +
                ", histoire_id=" + histoire_id +
                '}';
    }
}
