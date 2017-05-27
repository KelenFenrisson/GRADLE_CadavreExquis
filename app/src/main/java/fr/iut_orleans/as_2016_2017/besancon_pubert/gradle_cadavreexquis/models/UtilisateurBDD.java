package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kelenfenrisson on 24/05/17.
 */

public class UtilisateurBDD {

    private static final String NOM_BDD = CadavreExquisBDD.NOM_BDD;
    private static final int VERSION_BDD = CadavreExquisBDD.VERSION_BDD;

    private static final String TABLE_UTILISATEUR = CadavreExquisBDD.TABLE_UTILISATEUR;
    private static final int NUM_COL_ID=0;
    private static final String ID_UTILISATEUR = CadavreExquisBDD.ID_UTILISATEUR;
    private static final int NUM_COL_LOGIN=1;
    private static final String LOGIN_UTILISATEUR = CadavreExquisBDD.LOGIN_UTILISATEUR;
    private static final int NUM_COL_MDP=2;
    private static final String MDP_UTILISATEUR = CadavreExquisBDD.MDP_UTILISATEUR;

    private SQLiteDatabase sqliteDatabase;

    private CadavreExquisBDD cadavreExquisBDD;

    public UtilisateurBDD(Context context){
        this.cadavreExquisBDD = new CadavreExquisBDD(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(){
        //on ouvre la BDD en écriture
        this.sqliteDatabase = cadavreExquisBDD.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        this.sqliteDatabase.close();
    }

    public SQLiteDatabase getBDD(){
        return this.sqliteDatabase;
    }

    public long insertUtilisateur(Utilisateur utilisateur){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(LOGIN_UTILISATEUR, utilisateur.getLogin());
        values.put(MDP_UTILISATEUR, utilisateur.getMotDePasse());
        //on insère l'objet dans la BDD via le ContentValues
        return this.sqliteDatabase.insert(TABLE_UTILISATEUR, null, values);
    }

    public int updateUtilisateur(int id, Utilisateur utilisateur){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quel utilisateur on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(LOGIN_UTILISATEUR, utilisateur.getLogin());
        values.put(MDP_UTILISATEUR, utilisateur.getMotDePasse());
    return this.sqliteDatabase.update(TABLE_UTILISATEUR, values, ID_UTILISATEUR + " = " +id, null);
    }

    public int removeUtilisateurWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
    return this.sqliteDatabase.delete(TABLE_UTILISATEUR, ID_UTILISATEUR + " = " +id, null);
    }

    public Utilisateur getUtilisateurWithID(int id){
        //Récupère dans un Cursor les valeur correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son login)
        Cursor c = this.sqliteDatabase.query(TABLE_UTILISATEUR, new String[] {ID_UTILISATEUR, LOGIN_UTILISATEUR, MDP_UTILISATEUR}, ID_UTILISATEUR + " = \"" + id +"\"", null, null, null, null);
        return this.cursorToUtilisateur(c);
    }

    public Utilisateur getUtilisateurWithLogin(String login){
        //Récupère dans un Cursor les valeur correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son login)
        Cursor c = this.sqliteDatabase.query(TABLE_UTILISATEUR, new String[] {ID_UTILISATEUR, LOGIN_UTILISATEUR, MDP_UTILISATEUR}, LOGIN_UTILISATEUR + " LIKE \"" + login +"\"", null, null, null, null);
        return this.cursorToUtilisateur(c);
    }

    //Cette méthode permet de convertir un cursor en un utilisateur
    private Utilisateur cursorToUtilisateur(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un utilisateur "vierge"
        Utilisateur utilisateur = new Utilisateur();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        utilisateur.setId(c.getInt(NUM_COL_ID));
        utilisateur.setLogin(c.getString(NUM_COL_LOGIN));
        utilisateur.setMotDePasse(c.getString(NUM_COL_MDP));
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return utilisateur;
    }
}
