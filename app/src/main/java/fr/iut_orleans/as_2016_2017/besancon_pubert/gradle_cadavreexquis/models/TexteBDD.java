package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by kelenfenrisson on 24/05/17.
 */

public class TexteBDD {

    private static final String NOM_BDD = CadavreExquisBDD.NOM_BDD;
    private static final int VERSION_BDD = CadavreExquisBDD.VERSION_BDD;

    private static final String TABLE_TEXTE = CadavreExquisBDD.TABLE_TEXTE;
    private static final int NUM_COL_ID=0;
    private static final String ID_TEXTE = CadavreExquisBDD.ID_TEXTE;
    private static final int NUM_COL_UTILISATEUR=1;
    private static final String ID_UTILISATEUR = CadavreExquisBDD.ID_UTILISATEUR;
    private static final int NUM_COL_DATE=2;
    private static final String DATE_TEXTE = CadavreExquisBDD.DATE_TEXTE;
    private static final int NUM_COL_CONTENU=3;
    private static final String CONTENU_TEXTE = CadavreExquisBDD.CONTENU_TEXTE;
    private static final int NUM_COL_HISTOIRE=4;
    private static final String ID_HISTOIRE = CadavreExquisBDD.ID_HISTOIRE;

    private SQLiteDatabase sqliteDatabase;

    private CadavreExquisBDD cadavreExquisBDD;

    public TexteBDD(Context context){
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

    public long insertTexte(Texte texte){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(ID_UTILISATEUR, texte.getUtilisateur().getId());
        values.put(DATE_TEXTE, texte.getDate().getTime());
        values.put(CONTENU_TEXTE, texte.getContenu());
        values.put(ID_HISTOIRE, texte.getHistoire().getId());
        //on insère l'objet dans la BDD via le ContentValues
        return this.sqliteDatabase.insert(TABLE_TEXTE, null, values);
    }

    public int updateTexte(int id, Texte texte){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quel utilisateur on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(ID_UTILISATEUR, texte.getUtilisateur().getId());
        values.put(DATE_TEXTE, texte.getDate().getTime());
        values.put(CONTENU_TEXTE, texte.getContenu());
        values.put(ID_HISTOIRE, texte.getHistoire().getId());
    return this.sqliteDatabase.update(TABLE_TEXTE, values, ID_TEXTE + " = " +id, null);
    }

    public int removeTexteWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
    return this.sqliteDatabase.delete(TABLE_TEXTE, ID_UTILISATEUR + " = " +id, null);
    }

    public Texte getTexteWithID(int id){
        //Récupère dans un Cursor les valeur correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son id)
        Cursor c = this.sqliteDatabase.query(TABLE_TEXTE, new String[] {ID_UTILISATEUR, DATE_TEXTE, CONTENU_TEXTE, ID_HISTOIRE}, ID_TEXTE + " = \"" + id +"\"", null, null, null, null);
        return this.cursorToTexte(c);
    }

    //Cette méthode permet de convertir un cursor en un utilisateur
    private Texte cursorToTexte(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un utilisateur "vierge"
        Texte texte = new Texte();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        texte.setId(c.getInt(NUM_COL_ID));
        texte.setUtilisateur(null);
        texte.setDate(new Date()); texte.getDate().setTime(c.getInt(NUM_COL_DATE));
        texte.setContenu(c.getString(NUM_COL_CONTENU));
        texte.setHistoire(null);
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return texte;
    }
}
