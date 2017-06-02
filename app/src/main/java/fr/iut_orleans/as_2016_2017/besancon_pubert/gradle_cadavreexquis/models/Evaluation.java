package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Date;
import java.util.Objects;

/**
 * Created by kelenfenrisson on 24/05/17.
 */

public class Evaluation {

    private Integer utilisateur_id;
    private Integer histoire_id;
    private Date dateEvaluation;
    private Integer note;
    private String commentaire;

    public Evaluation() {
    }

    public Evaluation(Integer utilisateur_id, Integer histoire_id, Date dateEvaluation, Integer note, String commentaire) {
        this.utilisateur_id = utilisateur_id;
        this.histoire_id = histoire_id;
        this.dateEvaluation = dateEvaluation;
        this.note = note;
        this.commentaire = commentaire;
    }

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public Integer getHistoire_id() {
        return histoire_id;
    }

    public Date getDateEvaluation() {
        return dateEvaluation;
    }

    public Integer getNote() {
        return note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public void setHistoire_id(Integer histoire_id) {
        this.histoire_id = histoire_id;
    }

    public void setDateEvaluation(Date dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation that = (Evaluation) o;
        return Objects.equals(utilisateur_id, that.utilisateur_id) &&
                Objects.equals(histoire_id, that.histoire_id) &&
                Objects.equals(dateEvaluation, that.dateEvaluation) &&
                Objects.equals(note, that.note) &&
                Objects.equals(commentaire, that.commentaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilisateur_id, histoire_id, dateEvaluation, note, commentaire);
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "utilisateur_id=" + utilisateur_id +
                ", histoire_id=" + histoire_id +
                ", dateEvaluation=" + dateEvaluation +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
