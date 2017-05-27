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
    private Utilisateur utilisateur;
    private Histoire histoire;

    public Texte() {
        this.id = null;
        this.date = null;
        this.contenu = null;
        this.utilisateur = null;
        this.histoire = null;
    }

    public Texte(Date date, String contenu, Utilisateur utilisateur, Histoire histoire) {
        this.date = date;
        this.contenu = contenu;
        this.utilisateur = utilisateur;
        this.histoire = histoire;
    }

    public Texte(int id, Date date, String contenu, Utilisateur utilisateur, Histoire histoire) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
        this.utilisateur = utilisateur;
        this.histoire = histoire;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public Histoire getHistoire() {
        return histoire;
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

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setHistoire(Histoire histoire) {
        this.histoire = histoire;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Texte)) return false;
        Texte texte = (Texte) o;
        return Objects.equals(id, texte.id) &&
                Objects.equals(date, texte.date) &&
                Objects.equals(contenu, texte.contenu) &&
                Objects.equals(utilisateur, texte.utilisateur) &&
                Objects.equals(histoire, texte.histoire);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, date, contenu, utilisateur, histoire);
    }

    @Override
    public String toString() {
        return "Texte{" +
                "id=" + id +
                ", date=" + date +
                ", contenu='" + contenu + '\'' +
                ", utilisateur=" + utilisateur +
                ", histoire=" + histoire +
                '}';
    }
}
