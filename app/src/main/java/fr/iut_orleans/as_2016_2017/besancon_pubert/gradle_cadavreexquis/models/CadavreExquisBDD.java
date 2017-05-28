package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

/* CadavreExquisBDD
*  Classe décrivant une base de données bien précise
*
*
*
*
* */

public class CadavreExquisBDD {

    private static final String NOM_BDD = ProjectSQLiteOpenHelper.NOM_BDD;
    private static final int VERSION_BDD = ProjectSQLiteOpenHelper.VERSION_BDD;

    private static final String ID_UTILISATEUR = ProjectSQLiteOpenHelper.ID_UTILISATEUR;
    private static final String LOGIN_UTILISATEUR = ProjectSQLiteOpenHelper.LOGIN_UTILISATEUR;
    private static final String MDP_UTILISATEUR = ProjectSQLiteOpenHelper.MDP_UTILISATEUR;

    private static final String ID_HISTOIRE = ProjectSQLiteOpenHelper.ID_HISTOIRE;
    private static final String DATE_HISTOIRE = ProjectSQLiteOpenHelper.DATECREATION_HISTOIRE;
    private static final String TITRE_HISTOIRE = ProjectSQLiteOpenHelper.TITRE_HISTOIRE;

    private static final String ID_TEXTE = ProjectSQLiteOpenHelper.ID_TEXTE;
    private static final String DATE_TEXTE = ProjectSQLiteOpenHelper.DATE_TEXTE;
    private static final String CONTENU_TEXTE = ProjectSQLiteOpenHelper.CONTENU_TEXTE;

    private static final String DATE_EVALUER = ProjectSQLiteOpenHelper.DATE_EVALUER;
    private static final String NOTE_EVALUER = ProjectSQLiteOpenHelper.NOTE_EVALUER;
    private static final String COMMENTAIRE_EVALUER = ProjectSQLiteOpenHelper.COMMENTAIRE_EVALUER;

    public static final String TABLE_UTILISATEUR = ProjectSQLiteOpenHelper.TABLE_UTILISATEUR;
    private static final int UTILISATEUR_COL_ID=0;
    private static final int UTILISATEUR_COL_LOGIN=1;
    private static final int UTILISATEUR_COL_MDP=2;

    public static final String TABLE_HISTOIRE = ProjectSQLiteOpenHelper.TABLE_HISTOIRE;
    private static final int HISTOIRE_COL_ID=0;
    private static final int HISTOIRE_COL_DATE=1;
    private static final int HISTOIRE_COL_TITRE=2;


    public static final String TABLE_TEXTE = ProjectSQLiteOpenHelper.TABLE_TEXTE;
    private static final int TEXTE_COL_ID=0;
    private static final int TEXTE_COL_UTILISATEUR=1;
    private static final int TEXTE_COL_DATE=2;
    private static final int TEXTE_COL_CONTENU=3;
    private static final int TEXTE_COL_HISTOIRE=4;


    public static final String TABLE_EVALUER = ProjectSQLiteOpenHelper.TABLE_EVALUER;
    private static final int EVALUER_COL_UTILISATEUR=0;
    private static final int EVALUER_COL_TEXTE=1;
    private static final int EVALUER_COL_DATE=2;
    private static final int EVALUER_COL_NOTE=3;
    private static final int EVALUER_COL_COMMENTAIRE=4;


    private Context context;
    private SQLiteDatabase sqliteDatabase;
    private ProjectSQLiteOpenHelper projectSQLiteOpenHelper;

    public CadavreExquisBDD(Context context){
        this.context = context;
        this.projectSQLiteOpenHelper = new ProjectSQLiteOpenHelper(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(){
        //on ouvre la BDD en écriture
        this.sqliteDatabase = projectSQLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        this.sqliteDatabase.close();
    }

    public SQLiteDatabase getBDD(){
        return this.sqliteDatabase;
    }



    /* TABLE UTILISATEUR
    *
    *
    *
    *
    *
    *
    *
    * */

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
        utilisateur.setId(c.getInt(UTILISATEUR_COL_ID));
        utilisateur.setLogin(c.getString(UTILISATEUR_COL_LOGIN));
        utilisateur.setMotDePasse(c.getString(UTILISATEUR_COL_MDP));
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return utilisateur;
    }

    /* TABLE HISTOIRE
    *
    *
    *
    *
    *
    *
    *
    * */

    public long insertHistoire(Histoire histoire){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(DATE_HISTOIRE, histoire.getDateCreation().getTime());
        values.put(TITRE_HISTOIRE, histoire.getTitre());
        //on insère l'objet dans la BDD via le ContentValues
        return this.sqliteDatabase.insert(TABLE_HISTOIRE, null, values);
    }

    public int updateHistoire(int id, Histoire histoire){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quelle histoire on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(DATE_HISTOIRE, histoire.getDateCreation().getTime());
        values.put(TITRE_HISTOIRE, histoire.getTitre());
        return this.sqliteDatabase.update(TABLE_HISTOIRE, values, ID_HISTOIRE + " = " +id, null);
    }

    public int removeHistoireWithID(int id){
        //Suppression d'une histoire de la BDD grâce à l'ID
        return this.sqliteDatabase.delete(TABLE_HISTOIRE, ID_HISTOIRE + " = " +id, null);
    }

    public Histoire getHistoireWithID(int id){
        //Récupère dans un Cursor les valeur correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son login)
        Cursor c = this.sqliteDatabase.query(TABLE_HISTOIRE, new String[] {ID_HISTOIRE, DATE_HISTOIRE, TITRE_HISTOIRE}, ID_HISTOIRE + " = \"" + id +"\"", null, null, null, null);
        return this.cursorToHistoire(c);
    }

    //Cette méthode permet de convertir un cursor en un histoire
    private Histoire cursorToHistoire(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé une histoire "vierge"
        Histoire histoire = new Histoire();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        histoire.setId(c.getInt(HISTOIRE_COL_ID));
        histoire.setDateCreation(new Date());
        histoire.getDateCreation().setTime(c.getInt(HISTOIRE_COL_DATE));
        histoire.setTitre(c.getString(HISTOIRE_COL_TITRE));
        histoire.setTextes(this.getTextesArrayListWithHistoireID(histoire.getId()));
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return histoire;
    }


    /* TABLE TEXTE
    *
    *
    *
    *
    *
    *
    *
    * */
    public long insertTexte(Texte texte){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        if((this.hasUtilisateur(texte)) && this.hasHistoire(texte))
        {
            values.put(ID_UTILISATEUR, texte.getUtilisateur_id());

            values.put(DATE_TEXTE, texte.getDate().getTime());

            values.put(CONTENU_TEXTE, texte.getContenu());

            values.put(ID_HISTOIRE, texte.getHistoire_id());
        }
        //on insère l'objet dans la BDD via le ContentValues
        return this.sqliteDatabase.insert(TABLE_TEXTE, null, values);
    }

    public int updateTexte(int id, Texte texte){
        //La mise à jour d'un utilisateur dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quel utilisateur on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(ID_UTILISATEUR, texte.getUtilisateur_id());
        values.put(DATE_TEXTE, texte.getDate().getTime());
        values.put(CONTENU_TEXTE, texte.getContenu());
        values.put(ID_HISTOIRE, texte.getHistoire_id());
        return this.sqliteDatabase.update(TABLE_TEXTE, values, ID_TEXTE + " = " +id, null);
    }

    public int removeTexteWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return this.sqliteDatabase.delete(TABLE_TEXTE, ID_UTILISATEUR + " = " +id, null);
    }

    public Texte getTexteWithID(int id){
        //Récupère dans un Cursor les valeur correspondant à un utilisateur contenu dans la BDD (ici on sélectionne l'utilisateur grâce à son id)
        Cursor c = this.sqliteDatabase.query(TABLE_TEXTE, new String[] {ID_TEXTE, ID_UTILISATEUR, DATE_TEXTE, CONTENU_TEXTE, ID_HISTOIRE}, ID_TEXTE + " = \"" + id +"\"", null, null, null, null);
        return this.cursorToTexte(c);
    }


    public ArrayList<Texte> getTextesArrayListWithHistoireID(int id){
        Cursor c = this.sqliteDatabase.query(TABLE_TEXTE, new String[] {ID_TEXTE, ID_UTILISATEUR, DATE_TEXTE, CONTENU_TEXTE, ID_HISTOIRE}, ID_HISTOIRE + " = \"" + id +"\"", null, null, null, TEXTE_COL_DATE+" ASC");
        return this.cursorToListeTexte(c);
    }

    private ArrayList<Texte> cursorToListeTexte(Cursor c){
        ArrayList<Texte> textes = new ArrayList<>();
        Texte t;
        while(c.moveToNext()){
            t = new Texte(
                    c.getInt(TEXTE_COL_ID),
                    new Date(c.getInt(TEXTE_COL_DATE)),
                    c.getString(TEXTE_COL_CONTENU),
                    c.getInt(TEXTE_COL_UTILISATEUR),
                    c.getInt(TEXTE_COL_HISTOIRE));

            textes.add(t);
        }
        return textes;
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
        texte.setId(c.getInt(TEXTE_COL_ID));
        texte.setUtilisateur_id(c.getInt(TEXTE_COL_UTILISATEUR));
        texte.setDate(new Date()); texte.getDate().setTime(c.getInt(TEXTE_COL_DATE));
        texte.setContenu(c.getString(TEXTE_COL_CONTENU));
        texte.setHistoire_id(c.getInt(TEXTE_COL_HISTOIRE));
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return texte;
    }

    public boolean hasUtilisateur(Texte texte){

        boolean hasUtilisateur= false;

        if(texte.getUtilisateur_id() != null) {
            hasUtilisateur = this.getUtilisateurWithID(texte.getUtilisateur_id()) != null;
        }

        return hasUtilisateur;
    }

    public boolean hasHistoire(Texte texte){

        boolean hasHistoire = false;
        if(texte.getHistoire_id() != null) {
            hasHistoire = this.getHistoireWithID(texte.getHistoire_id()) != null;
        }
        return hasHistoire;
    }

    /* TABLE EVALUER
    *
    *
    *
    *
    *
    *
    *
    * */
    public long insertEvaluation(Evaluation evaluation){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(ID_UTILISATEUR, evaluation.getUtilisateur_id());
        values.put(ID_TEXTE, evaluation.getTexte_id());
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
        values.put(ID_UTILISATEUR, evaluation.getUtilisateur_id());
        values.put(ID_TEXTE, evaluation.getTexte_id());
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
        evaluation.setUtilisateur_id(c.getInt(EVALUER_COL_UTILISATEUR));
        evaluation.setTexte_id(c.getInt(EVALUER_COL_TEXTE));
        evaluation.setDateEvaluation(new Date()); evaluation.getDateEvaluation().setTime(c.getInt(EVALUER_COL_DATE));
        evaluation.setNote(c.getInt(EVALUER_COL_NOTE));
        evaluation.setCommentaire(c.getString(EVALUER_COL_COMMENTAIRE));
        //On ferme le cursor
        c.close();
        //On retourne l'utilisateur
        return evaluation;
    }
}