package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Date;
import java.util.Objects;

/**
 * Created by kelenfenrisson on 24/05/17.
 */

public class Evaluation {

    private Utilisateur utilisateur;
    private Texte texte;
    private Date dateEvaluation;
    private Integer note;
    private String commentaire;

    public Evaluation() {
        this.utilisateur = null;
        this.texte = null;
        this.dateEvaluation = null;
        this.note = null;
        this.commentaire = null;
    }

    public Evaluation(Utilisateur utilisateur, Texte texte, Date dateEvaluation, int note,  String commentaire) {
        this.utilisateur = utilisateur;
        this.texte = texte;
        this.dateEvaluation = dateEvaluation;
        this.note = note;
        this.commentaire = commentaire;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Texte getTexte() {
        return texte;
    }

    public void setTexte(Texte texte) {
        this.texte = texte;
    }

    public Date getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(Date dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evaluation)) return false;
        Evaluation that = (Evaluation) o;
        return Objects.equals(utilisateur, that.utilisateur) &&
                Objects.equals(texte, that.texte) &&
                Objects.equals(dateEvaluation, that.dateEvaluation) &&
                Objects.equals(note, that.note) &&
                Objects.equals(commentaire, that.commentaire);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(utilisateur, texte, dateEvaluation, note, commentaire);
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "utilisateur=" + utilisateur +
                ", texte=" + texte +
                ", dateEvaluation=" + dateEvaluation +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
