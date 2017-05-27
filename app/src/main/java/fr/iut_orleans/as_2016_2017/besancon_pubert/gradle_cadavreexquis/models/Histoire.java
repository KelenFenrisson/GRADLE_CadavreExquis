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
    private Date dateCreation;
    private ArrayList<Texte> textes;

    public Histoire() {
        this.id = null;
        this.dateCreation = null;
        this.textes = null;
    }

    public Histoire(int id, int date)
    {
        this.id = id;
        this.dateCreation = new Date(date);
        this.textes = new ArrayList<>();
    }

    public Histoire(int date)
    {
        this.id = null;
        this.dateCreation = new Date(date);
        this.textes = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Date getDateCreation() {
        return dateCreation;
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

    public void setTextes(ArrayList<Texte> textes) {
        this.textes = textes;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Histoire)) return false;
        Histoire histoire = (Histoire) o;
        return Objects.equals(id, histoire.id) &&
                Objects.equals(dateCreation, histoire.dateCreation) &&
                Objects.equals(textes, histoire.textes);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreation, textes);
    }

    @Override
    public String toString() {
        return "Histoire{" +
                "id=" + id +
                ", dateCreation=" + dateCreation +
                ", textes=" + textes +
                '}';
    }
}
