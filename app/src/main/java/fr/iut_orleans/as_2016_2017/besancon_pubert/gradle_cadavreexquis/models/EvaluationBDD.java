package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by kelenfenrisson on 24/05/17.
 */

public class EvaluationBDD {

    private static final String NOM_BDD = CadavreExquisBDD.NOM_BDD;
    private static final int VERSION_BDD = CadavreExquisBDD.VERSION_BDD;

    private static final String TABLE_EVALUER = CadavreExquisBDD.TABLE_EVALUER;
    private static final int NUM_COL_UTILISATEUR=0;
    private static final String ID_UTILISATEUR = CadavreExquisBDD.ID_UTILISATEUR;
    private static final int NUM_COL_TEXTE=1;
    private static final String ID_TEXTE = CadavreExquisBDD.ID_TEXTE;
    private static final int NUM_COL_DATE=2;
    private static final String DATE_EVALUER = CadavreExquisBDD.DATE_EVALUER;
    private static final int NUM_COL_NOTE=4;
    private static final String NOTE_EVALUER = CadavreExquisBDD.NOTE_EVALUER;
    private static final int NUM_COL_COMMENTAIRE=4;
    private static final String COMMENTAIRE_EVALUER = CadavreExquisBDD.COMMENTAIRE_EVALUER;


    private SQLiteDatabase sqliteDatabase;

    private CadavreExquisBDD cadavreExquisBDD;

    public EvaluationBDD(Context context){
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

    public long insertEvaluation(Evaluation evaluation){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(ID_UTILISATEUR, evaluation.getUtilisateur().getId());
        values.put(ID_TEXTE, evaluation.getTexte().getId());
        values.put(DATE_EVALUER, evaluation.getDateEvaluation().getTime());
        values.put(NOTE_EVALUER, evaluation.getNote());
        values.put(COMMENTAIRE_EVALUER, evaluation.getCommentaire());
        //on insère l'objet dans la BDD via le ContentValues
        return this.sqliteDatabase.insert(TABLE_EVALUER, null, values);
    }

    public int updateEvaluation(int id_utilisateur, int id_texte, Evaluation evaluation){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quel utilisateur on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(ID_UTILISATEUR, evaluation.getUtilisateur().getId());
        values.put(ID_TEXTE, evaluation.getTexte().getId());
        values.put(DATE_EVALUER, evaluation.getDateEvaluation().getTime());
        values.put(NOTE_EVALUER, evaluation.getNote());
        values.put(COMMENTAIRE_EVALUER, evaluation.getCommentaire());
    return this.sqliteDatabase.update(TABLE_EVALUER, values, ID_UTILISATEUR + " = " + id_utilisateur + " AND "+ ID_TEXTE + " = "+id_texte, null);
    }

    public int removeEvaluationWithID(int id_utilisateur, int id_texte){
        //Suppression d'un livre de la BDD grâce à l'ID
    return this.sqliteDatabase.delete(TABLE_EVALUER, ID_UTILISATEUR + " = " + id_utilisateur + " AND "+ ID_TEXTE + " = "+id_texte, null);
    }

    public Evaluation getEvaluationWithID(int id_utilisateur, int id_texte){
        //Récupère dans un Cursor les valeur correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son login)
        Cursor c = this.sqliteDatabase.query(TABLE_EVALUER, new String[] {ID_UTILISATEUR, ID_TEXTE, DATE_EVALUER, NOTE_EVALUER, COMMENTAIRE_EVALUER}, ID_UTILISATEUR + " = " + id_utilisateur + " AND "+ ID_TEXTE + " = "+id_texte, null, null, null, null);
        return this.cursorToEvaluation(c);
    }

    //Cette méthode permet de convertir un cursor en un utilisateur
    private Evaluation cursorToEvaluation(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un utilisateur "vierge"
        Evaluation evaluation = new Evaluation();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        evaluation.setUtilisateur(null);
        evaluation.setDateEvaluation(new Date()); evaluation.getDateEvaluation().setTime(c.getInt(NUM_COL_DATE));
        evaluation.setNote(c.getInt(NUM_COL_NOTE));
        evaluation.setCommentaire(c.getString(NUM_COL_COMMENTAIRE));
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return evaluation;
    }
}
