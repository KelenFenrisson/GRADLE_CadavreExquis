package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Created by pubert on 19/05/17.
 */
public class Histoire
{
    private Integer id;
    private String titre;
    private Date dateCreation;
    private ArrayList<Texte> textes;

    public Histoire() {
        this.id = null;
        this.titre=null;
        this.dateCreation = null;
        this.textes = new ArrayList<>();
    }

    public Histoire(Date date, String titre)
    {
        this.id = null;
        this.titre=titre;
        this.dateCreation = date;
        this.textes = new ArrayList<>();
    }

    public Histoire(int id, Date date, String titre)
    {
        this.id = id;
        this.titre = titre;
        this.dateCreation = date;
        this.textes = new ArrayList<>();
    }



    public Integer getId() {
        return id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public String getTitre() {
        return titre;
    }

    public ArrayList<Texte> getTextes() {
        return textes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTextes(ArrayList<Texte> textes) {
        this.textes = textes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Histoire histoire = (Histoire) o;
        return Objects.equals(id, histoire.id) &&
                Objects.equals(titre, histoire.titre) &&
                Objects.equals(dateCreation, histoire.dateCreation) &&
                Objects.equals(textes, histoire.textes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, dateCreation, textes);
    }

    @Override
    public String toString() {
        return "Histoire{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", titre="+ titre +
                ", textes=" + textes +
                '}';
    }
}
