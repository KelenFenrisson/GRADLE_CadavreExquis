package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/* CadavreExquisBDD
*  Classe décrivant une base de données bien précise
*
*
*
*
* */

public class CadavreExquisBDD extends SQLiteOpenHelper{

    public static final String NOM_BDD = "CadavreExquis.db";
    public static final int VERSION_BDD = 1;

    public static final String TABLE_UTILISATEUR = "UTILISATEUR";
    public static final String ID_UTILISATEUR = "id_utilisateur";
    public static final String LOGIN_UTILISATEUR = "login_utilisateur";
    public static final String MDP_UTILISATEUR = "mdp_utilisateur";
    public static final String CREATE_TABLE_UTILISATEUR =
            "CREATE TABLE "+TABLE_UTILISATEUR+
                    " ( " +
                    ID_UTILISATEUR+" INTEGER PRIMARY KEY,"+
                    LOGIN_UTILISATEUR+" VARCHAR(32) UNIQUE,"+
                    MDP_UTILISATEUR+" VARCHAR(32)"+
                    ");";

    public static final String TABLE_HISTOIRE = "HISTOIRE";
    public static final String ID_HISTOIRE = "id_histoire";
    public static final String DATECREATION_HISTOIRE = "datecreation_histoire";
    public static final String CREATE_TABLE_HISTOIRE =
            "CREATE TABLE "+TABLE_HISTOIRE+
                    " ( " +
                    ID_HISTOIRE+" INTEGER PRIMARY KEY,"+
                    DATECREATION_HISTOIRE+" INTEGER"+
                    ");";


    public static final String TABLE_TEXTE = "TEXTE";
    public static final String ID_TEXTE = "id_texte";
    public static final String DATE_TEXTE = "date_texte";
    public static final String CONTENU_TEXTE = "contenu_texte";
    public static final String CREATE_TABLE_TEXTE =
            "CREATE TABLE "+TABLE_TEXTE+
                    " ( " +
                    ID_TEXTE+" INTEGER PRIMARY KEY,"+
                    ID_UTILISATEUR+" INTEGER,"+
                    DATE_TEXTE+" INTEGER,"+
                    CONTENU_TEXTE+" VARCHAR(1000),"+
                    ID_HISTOIRE+" INTEGER,"+
                    "FOREIGN KEY ("+ID_UTILISATEUR+") REFERENCES "+TABLE_UTILISATEUR+"("+ID_UTILISATEUR+")," +
                    "FOREIGN KEY ("+ID_HISTOIRE+") REFERENCES "+TABLE_HISTOIRE+"("+ID_HISTOIRE+")"+
                    ");";


    public static final String TABLE_EVALUER = "EVALUER";
    public static final String DATE_EVALUER = "date_evaluer";
    public static final String NOTE_EVALUER = "note_evaluer";
    public static final String COMMENTAIRE_EVALUER = "commentaire_evaluer";
    public static final String CREATE_TABLE_EVALUER =
            "CREATE TABLE "+TABLE_EVALUER+
                    " ( " +
                    ID_UTILISATEUR+" INTEGER,"+
                    ID_TEXTE+" INTEGER,"+
                    DATE_EVALUER+" INTEGER,"+
                    NOTE_EVALUER+" INTEGER(2),"+
                    COMMENTAIRE_EVALUER+" VARCHAR(140),"+
                    "  PRIMARY KEY ("+ID_UTILISATEUR+", "+ID_TEXTE+", "+DATE_EVALUER+")," +
                    "  FOREIGN KEY ("+ID_UTILISATEUR+") REFERENCES "+TABLE_UTILISATEUR+"("+ID_UTILISATEUR+")," +
                    "  FOREIGN KEY ("+ID_TEXTE+") REFERENCES "+TABLE_TEXTE+"("+ID_TEXTE+")"+
                    ");";

    public CadavreExquisBDD(Context context, String name, CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_UTILISATEUR);
        sqLiteDatabase.execSQL(CREATE_TABLE_HISTOIRE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TEXTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_EVALUER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_EVALUER+";");
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_TEXTE+";");
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_HISTOIRE+";");
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_UTILISATEUR+";");
        onCreate(sqLiteDatabase);
    }
}