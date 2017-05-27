package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by kelenfenrisson on 24/05/17.
 */

public class HistoireBDD {

    private static final String NOM_BDD = CadavreExquisBDD.NOM_BDD;
    private static final int VERSION_BDD = CadavreExquisBDD.VERSION_BDD;

    private static final String TABLE_HISTOIRE = CadavreExquisBDD.TABLE_HISTOIRE;
    private static final int NUM_COL_ID=0;
    private static final String ID_HISTOIRE = CadavreExquisBDD.ID_HISTOIRE;
    private static final int NUM_COL_DATE=1;
    private static final String DATE_HISTOIRE = CadavreExquisBDD.DATECREATION_HISTOIRE;


    private SQLiteDatabase sqliteDatabase;

    private CadavreExquisBDD cadavreExquisBDD;

    public HistoireBDD(Context context){
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

    public long insertTexte(Histoire histoire){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(DATE_HISTOIRE, histoire.getDateCreation().getTime());
        //on insère l'objet dans la BDD via le ContentValues
        return this.sqliteDatabase.insert(TABLE_HISTOIRE, null, values);
    }

    public int updateTexte(int id, Histoire histoire){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quel utilisateur on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(DATE_HISTOIRE, histoire.getDateCreation().getTime());
    return this.sqliteDatabase.update(TABLE_HISTOIRE, values, ID_HISTOIRE + " = " +id, null);
    }

    public int removeLivreWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
    return this.sqliteDatabase.delete(TABLE_HISTOIRE, ID_HISTOIRE + " = " +id, null);
    }

    public Histoire getHistoireWithID(int id){
        //Récupère dans un Cursor les valeur correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son login)
        Cursor c = this.sqliteDatabase.query(TABLE_HISTOIRE, new String[] {ID_HISTOIRE, DATE_HISTOIRE}, ID_HISTOIRE + " = \"" + id +"\"", null, null, null, null);
        return this.cursorToHistoire(c);
    }

    //Cette méthode permet de convertir un cursor en un utilisateur
    private Histoire cursorToHistoire(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un utilisateur "vierge"
        Histoire histoire = new Histoire();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        histoire.setId(c.getInt(NUM_COL_ID));
        histoire.setDateCreation(new Date());
        histoire.getDateCreation().setTime(c.getInt(NUM_COL_DATE));
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return histoire;
    }
}
