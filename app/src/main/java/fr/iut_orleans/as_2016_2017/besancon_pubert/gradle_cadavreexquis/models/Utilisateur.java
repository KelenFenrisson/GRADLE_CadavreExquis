package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Objects;

/** Classe modèle d'un utilisateur de l'application
 * Created by pubert on 19/05/17.
 */
public class Utilisateur
{

    private Integer id;
    private String login;
    private String motDePasse;

    public Utilisateur(){
        this.id = null;
        this.login = null;
        this.motDePasse = null;
    }

    public Utilisateur(String login, String motDePasse){
        this.id = null;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public Utilisateur(Integer id, String login, String motDePasse) {
        this.id = id;
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setId(int id){this.id=id;}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(motDePasse, that.motDePasse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, motDePasse);
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
